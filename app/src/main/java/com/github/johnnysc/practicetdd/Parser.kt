package com.github.johnnysc.practicetdd

interface Parser {

    fun parse(raw: String): List<Any>

    class Base(private val delimiter: String) : Parser {

        init {
            check(delimiter.isNotEmpty()) {
                "Delimiter must not be empty!"
            }
        }

        override fun parse(raw: String): List<Any> {
            val parts = raw.split(delimiter)
            return parts
                .filter { string -> string.isNotEmpty() }
                .map {
                    it.toByteOrNull() ?: it.toShortOrNull() ?: it.toIntOrNull() ?: it.toLongOrNull()
                    ?: it.toFloatOrNull() ?: it.toDoubleOrNull() ?: it.toBooleanStrictOrNull()
                    ?: if (it.length == 1) it[0] else it
                }
        }
    }
}