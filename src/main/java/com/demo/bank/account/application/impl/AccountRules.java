package com.demo.bank.account.application.impl;

import org.springframework.stereotype.Component;

@Component
public class AccountRules {

  public void validateCurrency(String currency) {
    if (currency == null || currency.length() != 3) throw new IllegalArgumentException("currency must be ISO3");
  }

  public void validateAmount(long amountMinor) {
    if (amountMinor <= 0) throw new IllegalArgumentException("amountMinor must be > 0");
  }

  public void validateAccountActive(String status) {
    if (!"ACTIVE".equals(status)) throw new IllegalStateException("account not ACTIVE: " + status);
  }
}
