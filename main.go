package main

import (
	"database/sql"
	"log"
	"net/http"
	"os"

	"github.com/Qasrawi03Ghassan/rssagg/internal/database"
	"github.com/go-chi/chi"
	"github.com/go-chi/cors"
	"github.com/joho/godotenv"
	_ "github.com/lib/pq"
)

type ApiConfig struct {
	DB *database.Queries
}

func main() {

	godotenv.Load(".env")
	portS := os.Getenv("PORT")
	dbUrl := os.Getenv("DB_URL")

	if portS == "" {
		log.Fatal("Port is not found in the environment.")
	}
	if dbUrl == "" {
		log.Fatal("Database URL is not found in the environment.")
	}

	conn, err := sql.Open("postgres", dbUrl)
	if err != nil {
		log.Fatal("Can't conenct to database")
	}

	queries := database.New(conn)

	ApiCfg := ApiConfig{
		DB: queries,
	}

	router := chi.NewRouter()

	router.Use(cors.Handler(cors.Options{
		AllowedOrigins:   []string{"https://*", "http://*"},
		AllowedMethods:   []string{"GET", "POST", "DELETE", "PUT", "OPTIONS"},
		AllowedHeaders:   []string{"*"},
		ExposedHeaders:   []string{"Link"},
		AllowCredentials: false,
		MaxAge:           300,
	}))

	v1Router := chi.NewRouter()

	v1Router.Get("/healthz", handleReadiness)
	v1Router.Get("/err", handlerError)
	v1Router.Post("/users", ApiCfg.handlerCreateUser)
	v1Router.Get("/users", ApiCfg.middlewareAuth(ApiCfg.handlerGetUsers))
	v1Router.Post("/feeds", ApiCfg.middlewareAuth(ApiCfg.handlerCreateFeed))
	v1Router.Get("/feeds", ApiCfg.handlerGetFeeds)
	v1Router.Post("/feedFollows", ApiCfg.middlewareAuth(ApiCfg.handlerCreateFeedFollow))

	router.Mount("/v1", v1Router)

	srv := &http.Server{
		Handler: router,
		Addr:    ":" + portS,
	}

	log.Printf("Server started on port %v", portS)

	err = srv.ListenAndServe()
	if err != nil {
		log.Fatal(err)
	}

}
