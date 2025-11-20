package main

import (
	"fmt"
	"log"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
)

func handleRoot(ctx *gin.Context) {
	ctx.String(200, "Welcome to mini payment validation RESTful API server.")
}
func handleHealth(ctx *gin.Context) {
	ctx.JSON(http.StatusOK, gin.H{
		"message": "Service is up",
	})
}
func handleErr(ctx *gin.Context) {
	ctx.JSON(http.StatusBadRequest, gin.H{
		"error": "Something wrong happened",
	})
}

func handlePayment(ctx *gin.Context) {
	var newPayment Payment
	var newValidationRes validationResult
	err := ctx.ShouldBindJSON(&newPayment)
	if err != nil {
		if err.Error() == "EOF" {
			ctx.JSON(http.StatusBadRequest, gin.H{
				"error": "Invalid empty request",
			})
		} else if err.Error() == "json: cannot unmarshal string into Go struct field Payment.amount of type float64" {
			ctx.JSON(http.StatusBadRequest, gin.H{
				"error": "Invalid amount type entered",
			})
		} else {
			ctx.JSON(http.StatusBadRequest, gin.H{
				"error": fmt.Sprintf("Invalid parameters: %v", err.Error()),
			})
		}
		return
	}

	pValidator := paymentValidator{}
	newValidationRes, err = pValidator.Validate(newPayment)
	if err != nil {
		ctx.JSON(http.StatusBadRequest, gin.H{
			"error": err.Error(),
		})
		return
	}
	log.Printf("Got a new payment with transactionId: %v, amount %v, currency: %v and customerId: %v", newPayment.TransactionID, newPayment.Amount, newPayment.Currency, newPayment.CustomerID)

	fmt.Println()
	fmt.Println("===========================================")

	gateWayChannelRes := make(chan string)
	go simCallExternalGateWay(newPayment, gateWayChannelRes)

	var finalRes string
	select {
	case finalRes = <-gateWayChannelRes:

		fmt.Printf("Gateway result: %v ==> %v\n", newPayment.TransactionID, finalRes)
	case <-time.After(3 * time.Second):
		fmt.Println("Gateway timeout")
	}
	fmt.Println("===========================================")
	fmt.Println()

	var response PaymentResponse
	response.TransactionID = newPayment.TransactionID

	if newValidationRes.Valid {
		if finalRes == "APPROVED" {
			response.Status = "APPROVED"
		} else if finalRes == "REJECTED" {
			response.Status = "REJECTED"
			response.Message = "Amount limit exceeded (must be less than 500)"
		} else {
			ctx.JSON(http.StatusGatewayTimeout, gin.H{
				"error": "Request timed out",
			})
			return
		}
		ctx.JSON(http.StatusOK, response)
	}
}
