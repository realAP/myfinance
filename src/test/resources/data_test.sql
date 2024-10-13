INSERT INTO public."bank"
SELECT *
FROM CSVREAD('classpath:bank.csv');
COMMIT;
INSERT INTO public."space"
SELECT *
FROM CSVREAD('classpath:space.csv');
COMMIT;
INSERT INTO public."category"
SELECT *
FROM CSVREAD('classpath:category.csv');
COMMIT;
INSERT INTO public."rule"
SELECT *
FROM CSVREAD('classpath:rule.csv');
COMMIT;
INSERT INTO public."transfer"
SELECT *
FROM CSVREAD('classpath:transfer.csv');
COMMIT;
INSERT INTO public."spending"
SELECT *
FROM CSVREAD('classpath:spending.csv');
COMMIT;
