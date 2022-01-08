# Expense Reimbursement System

## Project Description

The Expense Reimbursement System(ERS) will manage the process of reimbursing employees for expenses incurred while on company time.All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests.Finance managers can log in and view all reimbursement requests and past history for all employees in the company.Finance managers are authorized to approve and deny requests for expense reimbursement.

## Technologies Used

* Java core  - version 8.0
* Postgressql - version 42.3.1
* Javalin - version 4.1.1
* log4j -version 1.2.17
* Junit -version Jupiter 5.8.1
* html -version 5
* css -version 3
* javascript 

## Features


* All employees can login , make new reimbursment requests.
* All employees can view their requests ,and filter the request by all , approved and denied .
* All managers can login, view all the employees requests and can deny or approve a request .

To-do list:
* New user register functionality.
* Reset password by sending a new password to user email functionality.
* Encrypt the password information in database.

## Getting Started
   

git clone
```git clone https://github.com/aizizifulati/Expense-Reimbursement-App.git```
To set up enviornment variables:
* update .bashrc file to include aws credentials
```
export AWS_RDS_ENDPOINT='[location of aws ec2 domain]'
export RDS_USERNAME='[ec2 username]'
export RDS_PASSWORD='[ec2 password]'
```

* Run javalin server using IntelliJ
* use web browser to navigate to client side
```http://localhost:9000```

## Usage

Employees and Financial Managers will be able to login and access their account dashboard.

Once logged in, an employee will be able to create a reimbursement request and view the status of current
and past requests. 

Managers will be able to view employee requests, approve or deny them, and filter requests
by status.

## Contributors

Aizizi Fulati

## License



