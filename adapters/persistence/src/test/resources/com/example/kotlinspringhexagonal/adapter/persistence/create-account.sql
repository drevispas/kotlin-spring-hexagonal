-- TODO: 테스트에서 testFixtures의 create-account.sql 을 못 읽어서 중복으로 생성. 원인 찾아야 함
insert into account(account_number, account_name, balance_amount) values (1001, 'account1', 100);
insert into account(account_number, account_name, balance_amount) values (1002, 'account1', 300);
insert into account(account_number, account_name, balance_amount) values (1003, 'account1', 500);
