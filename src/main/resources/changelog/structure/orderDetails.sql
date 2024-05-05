CREATE TABLE ORDER_DETAILS (
  ORDER_ID int NOT NULL AUTO_INCREMENT,
  USER_ID int NOT null,
  GROCERY_LIST text DEFAULT NULL,
  TOTAL_PRICE DOUBLE NOT NULL,
  PRIMARY KEY (ORDER_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;