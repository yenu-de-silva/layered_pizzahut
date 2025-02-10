CREATE TABLE Customer (
                          customer_id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255) NOT NULL,
                          contact VARCHAR(255),
                          email VARCHAR(255),
                          address VARCHAR(255)
);

CREATE TABLE Orders (
                        order_id INT PRIMARY KEY AUTO_INCREMENT,
                        order_date DATETIME NOT NULL,
                        status VARCHAR(255) NOT NULL,
                        total_price DECIMAL(10, 2) NOT NULL,
                        customer_id INT,
                        FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Employee (
                          employee_id INT PRIMARY KEY AUTO_INCREMENT,
                          first_name VARCHAR(50),
                          last_name VARCHAR(50),
                          position VARCHAR(50),
                          salary DECIMAL(10, 2),
                          hire_date DATE,
                          department_id INT,
                          FOREIGN KEY (department_id) REFERENCES Department(department_id)
);

CREATE TABLE Department (
                            department_id INT PRIMARY KEY AUTO_INCREMENT,
                            department_name VARCHAR(50)
);

CREATE TABLE Product (
                         product_id INT PRIMARY KEY AUTO_INCREMENT,
                         product_name VARCHAR(100),
                         price DECIMAL(10, 2),
                         description TEXT,
                         category VARCHAR(50),
                         inventory_count INT
);

CREATE TABLE OrderDetails (
                              order_details_id INT PRIMARY KEY AUTO_INCREMENT,
                              order_id INT,
                              product_id INT,
                              quantity INT NOT NULL,
                              price DECIMAL(10, 2) NOT NULL,
                              FOREIGN KEY (order_id) REFERENCES Orders(order_id),
                              FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

CREATE TABLE Inventory (
                           inventory_id INT PRIMARY KEY AUTO_INCREMENT,
                           product_id INT,
                           supplier_id INT,
                           quantity INT,
                           last_updated DATE,
                           FOREIGN KEY (product_id) REFERENCES Product(product_id),
                           FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id)
);

CREATE TABLE Supplier (
                          supplier_id INT PRIMARY KEY AUTO_INCREMENT,
                          supplier_name VARCHAR(100),
                          contact_name VARCHAR(50),
                          phone_number VARCHAR(15),
                          address VARCHAR(255)
);

CREATE TABLE CustomerFeedback (
                                  feedback_id INT PRIMARY KEY AUTO_INCREMENT,
                                  customer_id INT,
                                  feedback_text TEXT,
                                  rating INT,
                                  feedback_date DATE,
                                  FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);
CREATE TABLE Salary (
                        salary_id INT PRIMARY KEY AUTO_INCREMENT,
                        employee_id INT,
                        basic_salary DECIMAL(10, 2),
                        bonus DECIMAL(10, 2),
                        deductions DECIMAL(10, 2),
                        net_salary DECIMAL(10, 2),
                        salary_date DATE,
                        FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

CREATE TABLE Payment (
                         payment_id INT PRIMARY KEY AUTO_INCREMENT,
                         order_id INT,
                         payment_method VARCHAR(50) NOT NULL,
                         payment_date DATETIME NOT NULL,
                         amount DECIMAL(10, 2) NOT NULL,
                         FOREIGN KEY (order_id) REFERENCES Orders(order_id)
);

CREATE TABLE Delivery (
                          delivery_id INT PRIMARY KEY AUTO_INCREMENT,
                          order_id INT,
                          delivery_address VARCHAR(255) NOT NULL,
                          delivery_date DATETIME NOT NULL,
                          delivery_status VARCHAR(50) NOT NULL,
                          employee_id INT,
                          FOREIGN KEY (order_id) REFERENCES Orders(order_id),
                          FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);


CREATE TABLE User (
                      user_id INT PRIMARY KEY AUTO_INCREMENT,
                      username VARCHAR(50) UNIQUE,
                      password VARCHAR(255),
                      email VARCHAR(100) UNIQUE,
                      role VARCHAR(50),

);
CREATE TABLE Manage (
                        supplier_id INT,
                        inventory_id INT,
                        PRIMARY KEY (supplier_id, inventory_id),
                        FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id),
                        FOREIGN KEY (inventory_id) REFERENCES Inventory(inventory_id)
);
CREATE TABLE Rating (
                        ratingId VARCHAR(50) PRIMARY KEY,
                        customerId VARCHAR(50) NOT NULL,
                        orderId VARCHAR(50) NOT NULL,
                        ratingValue INT NOT NULL,
                        comments TEXT
);


