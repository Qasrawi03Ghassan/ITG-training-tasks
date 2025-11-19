package main

import (
	"net/http"

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
	ctx.Status(http.StatusOK)
}
