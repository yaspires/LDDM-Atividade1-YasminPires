package com.fatec.lddm_atividade_1.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object Directors : IntIdTable("directors") {
    val name = text("name")
    val age = integer("age").nullable()
}

object Movies : IntIdTable("movies") {
    val title = text("title")
    val description = text("description").nullable()
    val year = integer("year").nullable()

    val directorId = reference(
        "director_id",
        Directors,
        onDelete = ReferenceOption.CASCADE
    )
}