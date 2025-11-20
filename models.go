package main

type Payment struct {
	TransactionID string  `json:"transactionId"`
	Amount        float64 `json:"amount"`
	Currency      string  `json:"currency"`
	CustomerID    string  `json:"customerId"`
}

type validationResult struct {
	TransactionID string
	Valid         bool
	Message       string
}

type Validation interface {
	Validate(payment Payment) validationResult
}

type paymentValidator struct {
}
