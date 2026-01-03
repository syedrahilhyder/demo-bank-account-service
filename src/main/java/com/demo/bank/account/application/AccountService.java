package com.demo.bank.account.application;

import com.demo.bank.account.domain.*;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private final AccountRepository repo;

  public AccountService(AccountRepository repo) {
    this.repo = repo;
  }

  public void debit(String accountId, long amount) {
    Account acc = repo.findById(accountId)
        .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    acc.debit(amount);
    repo.save(acc);
  }

  public void credit(String accountId, long amount) {
    Account acc = repo.findById(accountId)
        .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    acc.credit(amount);
    repo.save(acc);
  }
}
