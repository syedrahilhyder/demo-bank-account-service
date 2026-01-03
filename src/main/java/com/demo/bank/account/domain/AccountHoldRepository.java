package com.demo.bank.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountHoldRepository extends JpaRepository<AccountHold, UUID> {}
