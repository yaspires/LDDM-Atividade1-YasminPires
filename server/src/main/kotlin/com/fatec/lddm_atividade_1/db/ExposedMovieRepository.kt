package com.fatec.lddm_atividade_1.db

import com.fatec.lddm_atividade_1.model.Movie
import com.fatec.lddm_atividade_1.repository.MovieRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class ExposedMovieRepository : MovieRepository {

    private fun ResultRow.toMovie() = Movie(
        id = this[Movies.id].value,
        title = this[Movies.title],
        description = this[Movies.description],
        year = this[Movies.year],
        directorId = this[Movies.directorId].value
    )

    override suspend fun getAll(): List<Movie> = newSuspendedTransaction {
        Movies.selectAll().map { it.toMovie() }
    }

    override suspend fun getById(id: Int): Movie? = newSuspendedTransaction {
        Movies.selectAll()
            .where { Movies.id eq id }
            .map { it.toMovie() }
            .singleOrNull()
    }

    override suspend fun getByDirectorId(directorId: Int): List<Movie> = newSuspendedTransaction {
        Movies.selectAll()
            .where { Movies.directorId eq directorId }
            .map { it.toMovie() }
    }

    override suspend fun create(movie: Movie): Movie = newSuspendedTransaction {
        val insert = Movies.insert {
            it[title] = movie.title
            it[description] = movie.description
            it[year] = movie.year
            it[directorId] = movie.directorId
        }

        insert.resultedValues!!.first().toMovie()
    }

    override suspend fun update(id: Int, movie: Movie): Movie = newSuspendedTransaction {
        Movies.update({ Movies.id eq id }) {
            it[title] = movie.title
            it[description] = movie.description
            it[year] = movie.year
            it[directorId] = movie.directorId
        }

        Movies.selectAll()
            .where { Movies.id eq id }
            .map { it.toMovie() }
            .single()
    }

    override suspend fun delete(id: Int): Unit = newSuspendedTransaction {
        Movies.deleteWhere { Movies.id eq id }
    }
}