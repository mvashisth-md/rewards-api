DROP TABLE IF EXISTS Transaction;

CREATE TABLE Transaction (
  id INT  auto_increment PRIMARY KEY, 
  amount FLOAT NOT NULL,
  customer_id VARCHAR(50) NOT NULL,
  transaction_ts TIMESTAMP NOT NULL
);

INSERT INTO Transaction(amount, customer_id, transaction_ts) VALUES
(40,'C1', '2020-02-22 11:08:45'),
(160.5,'C2','2020-02-24 11:08:45'),
(200.0,'C3','2020-02-24 11:08:45'),
(20.0,'C3','2020-02-24 11:08:45'),
(180.0,'C3','2020-04-24 11:08:45'),
(120.0,'C1','2020-03-25 11:08:45'),
(300.0,'C1','2020-03-26 11:08:45'),
(100.0,'C1','2020-03-15 11:08:45'),
(80.50,'C2','2020-04-18 11:01:00'),
(190.0,'C3','2020-04-17 08:12:23'),
(140.8,'C4','2020-04-18 11:45:20');