package com.fatec.lddm_atividade_1.repository

import com.fatec.lddm_atividade_1.model.Movie

interface MovieRepository {

    suspend fun getAll(): List<Movie>
    suspend fun getById(id: Int): Movie?
    suspend fun getByDirectorId(directorId: Int): List<Movie>
    suspend fun create(movie: Movie): Movie
    suspend fun update(id: Int, movie: Movie): Movie
    suspend fun delete(id: Int)
}