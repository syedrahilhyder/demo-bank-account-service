create table accounts (
  id varchar(64) primary key,
  balance_minor bigint not null,
  currency varchar(3) not null,
  status varchar(16) not null,
  updated_at timestamptz
);

create table account_holds (
  hold_id uuid primary key,
  account_id varchar(64) not null,
  amount_minor bigint not null,
  reason varchar(128) not null,
  status varchar(16) not null,
  created_at timestamptz
);

create index idx_holds_account on account_holds(account_id);
