create table accounts (
  id varchar(64) primary key,
  balance_minor bigint not null,
  currency varchar(3) not null,
  updated_at timestamptz
);
