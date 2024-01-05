
INSERT INTO public."rule" SELECT * FROM CSVREAD('classpath:rule.csv');
COMMIT;
INSERT INTO public."transfer" SELECT * FROM CSVREAD('classpath:transfer.csv');
COMMIT;
INSERT INTO public."spending" SELECT * FROM CSVREAD('classpath:spending.csv');
COMMIT;
