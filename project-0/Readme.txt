Your first project should demonstrate your knowledge of the following topics:

Java
SQL
JDBC
Basic Data Structures
Basic File I/O
You will be building a console application that persists data in a database with at least 3 tables which adhere to 3rd normal form (3NF). The basic form of this project should be that of a console-based banking app. If you want to mix it up a bit and can come up with a project idea that meets all the same requirements, contact your trainer to discuss and develop user stories. If you decide to go with the bank app, it should implement all of the required user stories listed below.

You will be expected to complete the minimum viable product by the deadline and give a brief 5 minute presentation demonstrating your project and answering questions from the QC team.

Minimum Requirements
Basic validation (no negative deposits/withdrawals, malformed emails, names with numbers, etc.)
All exceptions are properly caught and handled
Proper use of OOP principles
Documentation (all classes and methods have basic inline documentation)
Use of custom data structures (do not use java.util Collection types! Implement your own List)
SQL Data Persistance (at least 3 tables; all 3NF (normal form))
Bonus Features
Unit tests for service-layer classes
Logging messages and exceptions to a file
Banking App User Stories
These are user stories to describe the banking app. If you are not building the banking app for your project, you will need to discuss with your trainer to establish proper user stories.

Minimum Viable Product
[x] As a user, I can register for an account.
[x] As a user, I can login to my account.
[x] As a user, I can create one or more bank accounts.
[x] As a user, I can deposit funds into my account(s).
[x] As a user, I can withdraw funds from my account(s).
[x] As a user, I can display all of my accounts in a list which includes current balance.
[x] All monetary amounts should be displayed in a proper currency format ($1,234.56).
Bonus Stories
[] As a user, I can view the transaction history for an account.
[] As a user, I can share an account with another user.
[] As a user, I can transfer funds between accounts.
Tech Stack
You should be employing the following technologies in your project.

Java 8
Apache Maven
MariaDB deployed on AWS RDS
