package main

import (
	"math/rand"
	"time"
)

func simCallExternalGateWay(payment Payment, ch chan<- string) {
	var seconds int = 2
	time.Sleep(time.Duration(rand.Intn(seconds)+1) * time.Second)

	if payment.Amount < 500 {
		ch <- "APPROVED"
	} else {
		ch <- "REJECTED"
	}
}
