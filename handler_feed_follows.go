package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"time"

	"github.com/Qasrawi03Ghassan/rssagg/internal/database"
	"github.com/google/uuid"
)

func (ApiConfig *ApiConfig) handlerCreateFeedFollow(w http.ResponseWriter, r *http.Request, user database.User) {
	type parameters struct {
		FeedID uuid.UUID `json:"feed_id"`
	}
	decoder := json.NewDecoder(r.Body)

	params := parameters{}
	err := decoder.Decode(&params)

	if err != nil {
		respondWithError(w, 400, fmt.Sprintf("Error parsing JSON: %v", err))
		return
	}

	newFeedFollow, err := ApiConfig.DB.CreateFeedFollow(r.Context(), database.CreateFeedFollowParams{
		ID:        uuid.New(),
		CreatedAt: time.Now().UTC(),
		UpdatedAt: time.Now().UTC(),
		UserID:    user.ID,
		FeedID:    params.FeedID,
	})
	if err != nil {
		respondWithError(w, 400, fmt.Sprintf("Couldn't create feed follow: %v", err))
		return
	}

	respondWithJSON(w, 201, dbFeedFToFeedF(newFeedFollow))
}

func (ApiConfig *ApiConfig) handleGetFeedFollows(w http.ResponseWriter, r *http.Request, user database.User) {

	feedFollows, err := ApiConfig.DB.GetFeedFollows(r.Context(), user.ID)
	if err != nil {
		respondWithError(w, 400, fmt.Sprintf("Couldn't get feeds: %v", err))
	}

	respondWithJSON(w, 200, dbFeedFsToFeedFs(feedFollows))
}
