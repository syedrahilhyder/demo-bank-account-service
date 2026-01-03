package com.demo.bank.account.application.impl;

import com.demo.bank.account.application.AccountOps;
import com.demo.bank.account.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountOpsImpl implements AccountOps {

  private final AccountRepository accounts;
  private final AccountHoldRepository holds;
  private final AccountRules rules;

  public AccountOpsImpl(AccountRepository accounts, AccountHoldRepository holds, AccountRules rules) {
    this.accounts = accounts;
    this.holds = holds;
    this.rules = rules;
  }

  @Override
  public void createAccount(String id, long openingBalanceMinor, String currency) {
    rules.validateCurrency(currency);
    if (accounts.existsById(id)) throw new IllegalArgumentException("account already exists");
    accounts.save(new Account(id, openingBalanceMinor, currency));
  }

  @Override
  public long getBalance(String id) {
    return accounts.findById(id).orElseThrow(() -> new IllegalArgumentException("Account not found")).getBalanceMinor();
  }

  @Override
  public void debit(String id, long amountMinor) {
    rules.validateAmount(amountMinor);
    Account a = loadActive(id);
    a.debit(amountMinor);
    accounts.save(a);
  }

  @Override
  public void credit(String id, long amountMinor) {
    rules.validateAmount(amountMinor);
    Account a = loadActive(id);
    a.credit(amountMinor);
    accounts.save(a);
  }

  @Override
  public UUID placeHold(String id, long amountMinor, String reason) {
    rules.validateAmount(amountMinor);
    Account a = loadActive(id);
    UUID holdId = UUID.randomUUID();
    holds.save(new AccountHold(holdId, a.getId(), amountMinor, reason));
    return holdId;
  }

  @Override
  public void releaseHold(UUID holdId) {
    AccountHold h = holds.findById(holdId).orElseThrow(() -> new IllegalArgumentException("hold not found"));
    h.release();
    holds.save(h);
  }

  private Account loadActive(String id) {
    Account a = accounts.findById(id).orElseThrow(() -> new IllegalArgumentException("Account not found"));
    rules.validateAccountActive(a.getStatus());
    return a;
  }
}
