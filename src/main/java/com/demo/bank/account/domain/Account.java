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
  private String status; // ACTIVE | FROZEN
  private Instant updatedAt;

  protected Account() {}

  public Account(String id, long openingBalanceMinor, String currency) {
    this.id = id;
    this.balanceMinor = openingBalanceMinor;
    this.currency = currency;
    this.status = "ACTIVE";
    this.updatedAt = Instant.now();
  }

  public String getId() { return id; }
  public long getBalanceMinor() { return balanceMinor; }
  public String getCurrency() { return currency; }
  public String getStatus() { return status; }

  public void freeze() { this.status = "FROZEN"; touch(); }
  public void unfreeze() { this.status = "ACTIVE"; touch(); }

  public void debit(long amount) { this.balanceMinor -= amount; touch(); }
  public void credit(long amount) { this.balanceMinor += amount; touch(); }

  private void touch() { this.updatedAt = Instant.now(); }
}
