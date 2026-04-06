package com.fatec.lddm_atividade_1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform