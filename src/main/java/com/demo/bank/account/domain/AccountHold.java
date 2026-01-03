package com.demo.bank.account.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "account_holds")
public class AccountHold {

  @Id
  private UUID holdId;

  private String accountId;
  private long amountMinor;
  private String reason;
  private String status; // PLACED | RELEASED
  private Instant createdAt;

  protected AccountHold() {}

  public AccountHold(UUID holdId, String accountId, long amountMinor, String reason) {
    this.holdId = holdId;
    this.accountId = accountId;
    this.amountMinor = amountMinor;
    this.reason = reason;
    this.status = "PLACED";
    this.createdAt = Instant.now();
  }

  public UUID getHoldId() { return holdId; }
  public String getAccountId() { return accountId; }
  public long getAmountMinor() { return amountMinor; }
  public String getStatus() { return status; }

  public void release() { this.status = "RELEASED"; }
}
