package main

import (
	"fmt"
	"log"
	"os"

	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"github.com/joho/godotenv"
)

func main() {
	godotenv.Load(".env")

	port := os.Getenv("PORT")
	if port == "" {
		log.Fatal("Couldn't find port in environment")
	}

	router := gin.Default()
	router.Use(cors.New(cors.Config{
		AllowOrigins:     []string{"https://*", "http://*"},
		AllowMethods:     []string{"GET", "POST"},
		AllowHeaders:     []string{"*"},
		ExposeHeaders:    []string{"Link"},
		AllowCredentials: false,
		MaxAge:           300,
	}))

	router.GET("/", handleRoot)
	router.GET("/healthz", handleHealth)
	router.GET("/err", handleErr)
	router.POST("/payment", handlePayment)

	portBuffer := fmt.Sprintf("localhost:%v", port)
	err := router.Run(portBuffer)
	if err != nil {
		log.Fatal("Couldn't start service")
	}
}
