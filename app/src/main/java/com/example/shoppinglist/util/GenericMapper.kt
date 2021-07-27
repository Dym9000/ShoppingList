package com.example.shoppinglist.util

abstract class GenericMapper<I, O> {
    fun mapFromList(input: List<I>): List<O> {
        return input.map {
            mapFrom(it)
        }
    }

    abstract fun mapFrom(input: I): O

    fun mapToList(input: List<O>): List<I> {
        return input.map {
            mapTo(it)
        }
    }

    abstract fun mapTo(input: O): I
}