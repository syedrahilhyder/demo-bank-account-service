package com.demo.bank.account.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "accounts")
public class Account {

  @Id
  private String id;
  private long balanceMinor;
  private String currency;
  private Instant updatedAt;

  protected Account() {}

  public Account(String id, long balanceMinor, String currency) {
    this.id = id;
    this.balanceMinor = balanceMinor;
    this.currency = currency;
    this.updatedAt = Instant.now();
  }

  public String getId() { return id; }
  public long getBalanceMinor() { return balanceMinor; }

  public void debit(long amount) {
    this.balanceMinor -= amount;
    this.updatedAt = Instant.now();
  }

  public void credit(long amount) {
    this.balanceMinor += amount;
    this.updatedAt = Instant.now();
  }
}
