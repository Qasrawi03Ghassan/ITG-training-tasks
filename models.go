package main

import (
	"errors"
	"fmt"
)

type Payment struct {
	TransactionID string  `json:"transactionId"`
	Amount        float64 `json:"amount"`
	Currency      string  `json:"currency"`
	CustomerID    string  `json:"customerId"`
}

type validationResult struct {
	Valid   bool
	Message string
}

type Validation interface {
	Validate(payment Payment) validationResult
}

type paymentValidator struct {
}

func (pValidator *paymentValidator) Validate(payment Payment) (validationResult, error) {
	var res validationResult
	res.Valid = false

	err := validateTransactionID(payment.TransactionID)
	if err != nil {
		res.Message = "Invalid transactionID"
		fmt.Printf("Validation error: %v", err)
		return res, err
	}
	err = validateAmount(payment.Amount)
	if err != nil {
		res.Message = "Invalid amount"
		fmt.Printf("Validation error: %v", err)
		return res, err
	}
	err = validateCurrency(payment.Currency)
	if err != nil {
		res.Message = "Invalid currency"
		fmt.Printf("Validation error: %v", err)
		return res, err
	}
	err = validateCustomerID(payment.CustomerID)
	if err != nil {
		res.Message = "Invalid customerID"
		fmt.Printf("Validation error: %v", err)
		return res, err
	}

	res.Valid = true
	return res, err
}

func validateTransactionID(TransactionID string) error {
	if TransactionID == "" {
		return errors.New("invalid transaction ID, must be not empty")
	}
	return nil
}
func validateAmount(amount float64) error {
	if amount <= 0 {
		return errors.New("invalid amount, must be bigger than 0")
	}
	return nil
}
func validateCurrency(currency string) error {
	if currency != "USD" && currency != "EUR" && currency != "ILS" {
		return errors.New("invalid currency, must be USD, EUR or ILS")
	}
	return nil
}
func validateCustomerID(CustomerID string) error {
	if CustomerID == "" {
		return errors.New("invalid customer ID, must be not empty")
	}
	return nil
}
