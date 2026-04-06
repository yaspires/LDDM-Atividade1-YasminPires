package com.fatec.lddm_atividade_1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val description: String? = null,
    val year: Int? = null,

    @SerialName("director_id")
    val directorId: Int,   // FK

    val director: Director? = null // opcional (pra retorno com join)
)