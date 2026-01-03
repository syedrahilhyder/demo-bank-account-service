package com.demo.bank.account.api;

import com.demo.bank.account.application.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final AccountService service;

  public AccountController(AccountService service) {
    this.service = service;
  }

  @PostMapping("/{id}/debit")
  public void debit(@PathVariable String id, @RequestParam long amountMinor) {
    service.debit(id, amountMinor);
  }

  @PostMapping("/{id}/credit")
  public void credit(@PathVariable String id, @RequestParam long amountMinor) {
    service.credit(id, amountMinor);
  }
}
