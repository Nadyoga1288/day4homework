package com.nadyoga.day4homework.ui.theme.todo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.nadyoga.day4homework.todo.Todo

class TodoViewModel : ViewModel() {
    private val _todos = mutableStateListOf<Todo>()
    val todos: List<Todo> = _todos

    fun addTodo(text: String) {
        if (text.isBlank()) return
        _todos.add(
            Todo(
                id = if (_todos.isEmpty()) 1 else _todos.maxOf { it.id } + 1,
                text = text.trim()
            )
        )
    }

    fun toggleTodo(id: Int) {
        val index = _todos.indexOfFirst { it.id == id }
        if (index != -1) {
            _todos[index] = _todos[index].copy(isDone = !_todos[index].isDone)
        }
    }

    // НОВАЯ ФУНКЦИЯ: удаление задачи по id
    fun deleteTodo(id: Int) {
        _todos.removeAll { it.id == id }
    }
}