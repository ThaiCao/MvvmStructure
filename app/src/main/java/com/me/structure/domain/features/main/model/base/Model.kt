package com.me.structure.domain.features.main.model.base

interface Model {
    fun toLocalDto(): Dto
    fun toRemoteDto(): Dto
}