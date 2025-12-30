package com.nadyoga.day4homework.todo

data class Todo(
    val id: Int,
    val text: String,
    val isDone: Boolean = false
)