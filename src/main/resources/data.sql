INSERT INTO online_bank.account (id, sort_code, account_number, current_balance, bank_name, owner_name)
VALUES (1, '53-68-92', '73084635', 1071.78, 'Challenger Bank', 'Paul Dragoslav');
INSERT INTO online_bank.account (id, sort_code, account_number, current_balance, bank_name, owner_name)
VALUES (2, '65-93-37', '21956204', 67051.01, 'High Street Bank', 'Scrooge McDuck');

INSERT INTO online_bank.transaction (id, source_account_id, target_account_id, target_owner_name, amount, initiation_date, completion_date, reference)
VALUES (1, 1, 2, 'Scrooge McDuck', 100.00, '2019-04-01 10:30', '2019-04-01 10:54', 'Protection charge Apr');
INSERT INTO online_bank.transaction (id, source_account_id, target_account_id, target_owner_name, amount, initiation_date, completion_date, reference)
VALUES (2, 1, 2, 'Scrooge McDuck', 100.00, '2019-05-01 10:30', '2019-05-01 11:21', 'Protection charge May');

INSERT INTO online_bank.transaction (id, source_account_id, target_account_id, target_owner_name, amount, initiation_date, completion_date, reference)
VALUES (3, 2, 1, 'Paul Dragoslav', 10000.00, '2019-05-27 17:21', null, 'Ha Ha I am rich');
