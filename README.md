# WorkerApp
WorkerApp is a Spring MVC application I've created while learning Spring and its components. It implements Spring MVC, 
Spring Security, Hibernate Validation and Hibernate for data persistence. I've created a customized user validation system rather than
using the out of the box Spring Security solution because I wanted a better database structure for the user and roles tables and relations.

The purpose of the application is to keep track of worker and companies as well as connecting the worker to the companies. To access data
you need to have a user account. There are 3 different user roles, employee, manager and administrator. Employees can only view worker
and company lists. So read only access and even that is limited to basic data and no details. Then there are the manager users. They have
read and write access. They can see the worker and company lists and worker/company details. Write access lets them add new entries and
update existing entries, but they don't have the option to delete entries. Lastly there are the admin user. They have full access to the system.
Read and write. Besides working with the worker and company data they also have access to deleting entries as well as adding new
users to the system and giving them roles.

### Instruction
Install, set up and run a MySql database. Create DB based on the DB-script.sql file located in the top folder.
Your database is now setup. Login credentials are username: 'admin', password: 'admin'. You can add other users through the admin user.

You can run the script found in the DB_test_data.sql file to fill up the database with some generated test data of 100 addresses, 
30 companies, 100 worker and 2 user.

Tomcat is required to run this application.

### Spring MVC
Implementing the Spring frameworks into the project. Using the Spring MVC for the backend. Hibernate validation for validating user input.

### Spring Security
Added Spring Security to the project for user log in and registration, as well as authorizing access to resources.