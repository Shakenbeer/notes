package com.shakenbeer.notes.domain

data class NodeContent(
    val title: String,
    val body: String
) {
    companion object {
        val EMPTY = NodeContent("", "")
    }
}
