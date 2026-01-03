package com.demo.bank.account.api;

import com.demo.bank.account.application.AccountOps;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final AccountOps ops;

  public AccountController(AccountOps ops) {
    this.ops = ops;
  }

  @PostMapping
  public void create(@RequestParam @NotBlank String id,
                     @RequestParam long openingBalanceMinor,
                     @RequestParam @NotBlank String currency) {
    ops.createAccount(id, openingBalanceMinor, currency);
  }

  @GetMapping("/{id}")
  public Map<String, Object> get(@PathVariable String id) {
    return Map.of("id", id, "balanceMinor", ops.getBalance(id));
  }

  @PostMapping("/{id}/debit")
  public void debit(@PathVariable String id, @RequestParam @Min(1) long amountMinor) {
    ops.debit(id, amountMinor);
  }

  @PostMapping("/{id}/credit")
  public void credit(@PathVariable String id, @RequestParam @Min(1) long amountMinor) {
    ops.credit(id, amountMinor);
  }

  @PostMapping("/{id}/holds")
  public UUID placeHold(@PathVariable String id,
                        @RequestParam @Min(1) long amountMinor,
                        @RequestParam String reason) {
    return ops.placeHold(id, amountMinor, reason);
  }

  @PostMapping("/holds/{holdId}/release")
  public void releaseHold(@PathVariable UUID holdId) {
    ops.releaseHold(holdId);
  }
}
