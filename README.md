# Task: Build a Mini Payment Validation Service using GoLang
Create a small Go application that:  
**1. Reads a JSON request (payment info)**
    Example input JSON:  
    ```
    {
        "transactionId": "TX12345",
        "amount": 150.75,
        "currency": "USD",
        "customerId": "CUST9001" 
    }
    ```  

---
**2. Validates the request using a service**  
Create an interface:
```go
type Validator interface {
    Validate(payment Payment) ValidationResult
}
```
Create a struct `PaymentValidator` that implements the interface and checks:  
- Amount must be > 0  
- Currency must be one of: ["USD", "EUR", "ILS"]  
- CustomerId must not be empty  

Return:
```go
type ValidationResult struct {
    Valid   bool    Message string
}
```

---  

**3. Simulate an external API call using goroutines + channels**  
create a function:  
```go
func callExternalGateway(payment Payment, ch chan<- string)
```

inside it, simulate a 1–2 second delay (`time.Sleep`) and return:  
- `"APPROVED"` if amount < 500  
- `"REJECTED"` otherwise
---
**4. Combine the validator + external gateway**  
Main flows:  
1. Parse JSON  
2. Validate payment  
3. If valid → call external gateway concurrently  
4. Print the final result:   
**`Transaction TX12345 → APPROVED`**  
---
**5. Add error handling**  
- Invalid JSON → print error  
- Invalid fields → print validation messages
- Simulated gateway timeout after 3 seconds  

Use:  
```go
select {case result := <-ch:
    fmt.Println("Gateway Result:", result)case <-time.After(3 * time.Second):
    fmt.Println("Gateway Timeout")
}
```