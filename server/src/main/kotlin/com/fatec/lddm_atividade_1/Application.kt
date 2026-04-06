package com.fatec.lddm_atividade_1

import com.fatec.lddm_atividade_1.db.DatabaseFactory
import com.fatec.lddm_atividade_1.db.ExposedDirectorRepository
import com.fatec.lddm_atividade_1.db.ExposedMovieRepository
import com.fatec.lddm_atividade_1.routes.directorRoute
import com.fatec.lddm_atividade_1.routes.movieRoute
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.respondText
import io.ktor.server.response.*
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

import io.ktor.server.engine.*
import io.ktor.server.netty.*


fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) { json() }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                mapOf("error" to (cause.message ?: "Erro interno"))
            )
        }
    }

    DatabaseFactory.init()

    val directorRepository = ExposedDirectorRepository()
    val movieRepository = ExposedMovieRepository()

    routing {
        get("/") { call.respondText("Ktor: ${Greeting().greet()}") }
        get("/health") { call.respondText("OK") }

        //  Swagger
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")

        directorRoute(directorRepository)
        movieRoute(movieRepository)
    }
}