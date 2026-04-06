package com.fatec.lddm_atividade_1.repository

import com.fatec.lddm_atividade_1.model.Director

interface DirectorRepository {

    suspend fun getAll(): List<Director>
    suspend fun getById(id: Int): Director?
    suspend fun create(director: Director): Director
    suspend fun update(id: Int, director: Director): Director
    suspend fun delete(id: Int)
}