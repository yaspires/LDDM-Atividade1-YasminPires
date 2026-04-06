package com.fatec.lddm_atividade_1.db.migration

import com.fatec.lddm_atividade_1.db.Directors
import com.fatec.lddm_atividade_1.db.Movies
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class V3__Seed_Data : BaseJavaMigration() {

    override fun migrate(context: Context) {
        val safeConnection = FlywayConnection(context.connection)
        val database = Database.connect({ safeConnection })

        transaction(database) {
            seedDirectors()
            seedMovies()
        }
    }

    private fun seedDirectors() {
        data class DirectorData(
            val name: String,
            val age: Int?
        )

        val directors = listOf(
            DirectorData("Christopher Nolan", 53),
            DirectorData("Quentin Tarantino", 61),
            DirectorData("Steven Spielberg", 77)
        )

        directors.forEach { d ->
            Directors.insert {
                it[name] = d.name
                it[age] = d.age
            }
        }
    }

    private fun seedMovies() {
        data class MovieData(
            val title: String,
            val description: String,
            val year: Int,
            val directorId: Int
        )

        val movies = listOf(
            MovieData("Inception", "Filme sobre sonhos dentro de sonhos", 2010, 1),
            MovieData("Interstellar", "Exploração espacial e buracos negros", 2014, 1),
            MovieData("Pulp Fiction", "Histórias interligadas do submundo", 1994, 2),
            MovieData("Kill Bill", "Vingança de uma ex-assassina", 2003, 2),
            MovieData("Jurassic Park", "Dinossauros recriados geneticamente", 1993, 3)
        )

        movies.forEach { m ->
            Movies.insert {
                it[title] = m.title
                it[description] = m.description
                it[year] = m.year
                it[directorId] = m.directorId
            }
        }
    }
}