package main

import (
	"math/rand"
	"time"
)

func simCallExternalGateWay(payment Payment, ch chan<- string) {
	time.Sleep(time.Duration(rand.Intn(2)+1) * time.Second)

	if payment.Amount < 500 {
		ch <- "APPROVED"
	} else {
		ch <- "REJECTED"
	}
}
