package com.fatec.lddm_atividade_1.routes

import com.fatec.lddm_atividade_1.model.Director
import com.fatec.lddm_atividade_1.repository.DirectorRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.directorRoute(repository: DirectorRepository) {

    route("/directors") {

        // GET
        get {
            call.respond(repository.getAll())
        }

        // GET by ID
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to "ID inválido"))
                return@get
            }

            val director = repository.getById(id)
            if (director == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(director)
            }
        }

        // POST
        post {
            val director = call.receive<Director>()
            val created = repository.create(director)
            call.respond(HttpStatusCode.Created, created)
        }

        // PUT
        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@put
            }

            val director = call.receive<Director>()
            val updated = repository.update(id, director)
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