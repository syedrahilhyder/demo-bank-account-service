package com.demo.bank.account.application;

import java.util.UUID;

public interface AccountOps {
  void createAccount(String id, long openingBalanceMinor, String currency);
  long getBalance(String id);
  void debit(String id, long amountMinor);
  void credit(String id, long amountMinor);
  UUID placeHold(String id, long amountMinor, String reason);
  void releaseHold(UUID holdId);
}
