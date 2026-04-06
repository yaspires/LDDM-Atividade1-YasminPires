package com.fatec.lddm_atividade_1.db

import com.fatec.lddm_atividade_1.model.Director
import com.fatec.lddm_atividade_1.repository.DirectorRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class ExposedDirectorRepository : DirectorRepository {

    private fun ResultRow.toDirector() = Director(
        id = this[Directors.id].value,
        name = this[Directors.name],
        age = this[Directors.age]
    )

    override suspend fun getAll(): List<Director> = newSuspendedTransaction {
        Directors.selectAll().map { it.toDirector() }
    }

    override suspend fun getById(id: Int): Director? = newSuspendedTransaction {
        Directors.selectAll()
            .where { Directors.id eq id }
            .map { it.toDirector() }
            .singleOrNull()
    }

    override suspend fun create(director: Director): Director = newSuspendedTransaction {
        val insert = Directors.insert {
            it[name] = director.name
            it[age] = director.age
        }

        insert.resultedValues!!.first().toDirector()
    }

    override suspend fun update(id: Int, director: Director): Director = newSuspendedTransaction {
        Directors.update({ Directors.id eq id }) {
            it[name] = director.name
            it[age] = director.age
        }

        Directors.selectAll()
            .where { Directors.id eq id }
            .map { it.toDirector() }
            .single()
    }
    override suspend fun delete(id: Int): Unit = newSuspendedTransaction {
        Directors.deleteWhere { Directors.id eq id }
    }
}