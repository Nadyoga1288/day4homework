package com.nadyoga.day4homework.ui.theme.todo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodoViewModel : ViewModel() {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    private var nextId = 0

    fun addTodo(text: String) {
        if (text.isBlank()) return

        val newTodo = Todo(
            id = nextId++,
            text = text,
            isDone = false
        )

        _todos.value = _todos.value + newTodo
    }

    fun toggleTodo(id: Int) {
        _todos.value = _todos.value.map { todo ->
            if (todo.id == id) {
                todo.copy(isDone = !todo.isDone)
            } else {
                todo
            }
        }
    }
}
