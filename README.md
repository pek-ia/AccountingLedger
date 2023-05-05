# Accounting Ledger

## Description

This project implements a simple single-entry accounting ledger.

A ledger holds a list of 0 or more transactions, which may be *credits* (amounts payed into the account) or *debits* (amounts paid OUT)

Each transaction contains the following data:

- a date/time stamp
- a description
- the name of a payee or creditor
- an amount, in US dollars
  - Credits are positive values
  - Debits are negative values

Transactions are saved to a file named:

    ./src/main/resources/transactions.csv

relative to the project folder.

## Architecture

The application is centered around three classes:

- Transaction is a data object that represents a transaction
- Ledger represents the ledger and its file
  - Transactions may be added to the ledger
  - The ledger may be searched for transactions matching certain criteria
  - The ledger file is always synchronized with the in-memory representation
- CliScreen represents a data input/output screen
  - Presents a title, a list of selectable options, and a prompt
  - Subclasses of CliScreen represent specific screens


## Operation


## Planned enhancements
