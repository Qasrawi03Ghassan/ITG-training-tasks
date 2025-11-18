package main

import (
	"fmt"
	"net/http"

	"github.com/Qasrawi03Ghassan/rssagg/internal/auth"
	"github.com/Qasrawi03Ghassan/rssagg/internal/database"
)

type authHandler func(http.ResponseWriter, *http.Request, database.User)

func (cfg *ApiConfig) middlewareAuth(handler authHandler) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {

		ApiKey, err := auth.GetApiKey(r.Header)
		if err != nil {
			respondWithError(w, 403, fmt.Sprintf("Authentication error: %s", err))
			return
		}

		user, err := cfg.DB.GetUserByApiKey(r.Context(), ApiKey)
		if err != nil {
			respondWithError(w, 400, fmt.Sprintf("Couldn't get user: %s", err))
			return
		}

		handler(w, r, user)
	}
}
