package com.fatec.lddm_atividade_1.routes

import com.fatec.lddm_atividade_1.model.Movie
import com.fatec.lddm_atividade_1.repository.MovieRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.movieRoute(repository: MovieRepository) {

    route("/movies") {

        // GET
        get {
            call.respond(repository.getAll())
        }

        // GET by ID
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val movie = repository.getById(id)
            if (movie == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(movie)
            }
        }

        // POST
        post {
            val movie = call.receive<Movie>()
            val created = repository.create(movie)
            call.respond(HttpStatusCode.Created, created)
        }

        // PUT
        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@put
            }

            val movie = call.receive<Movie>()
            val updated = repository.update(id, movie)
            call.respond(updated)
        }

        // DELETE
        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }

            repository.delete(id)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}