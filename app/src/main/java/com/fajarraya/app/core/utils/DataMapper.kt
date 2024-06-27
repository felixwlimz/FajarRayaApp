package com.fajarraya.app.core.utils

interface DataMapper<F, T> {
    fun mapFrom(data : F) : T

    fun mapLists(data : List<F>) : List<T> {
        return listOf()
    }

}