package com.fatec.lddm_atividade_1.model

import kotlinx.serialization.Serializable

@Serializable
data class Director(
    val id: Int,
    val name: String,
    val age: Int? = null
)