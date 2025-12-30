package com.nadyoga.day4homework.ui.theme.todo

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nadyoga.day4homework.ui.theme.components.TodoItemRow

@Composable
fun TodoScreen(viewModel: TodoViewModel = viewModel()) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()  // Отступ от статус-бара сверху
            .navigationBarsPadding()  // Отступ от навигационной панели снизу (на некоторых устройствах)
    ) {
        Spacer(modifier = Modifier.height(180.dp))
        // === Верхняя часть: ввод новой задачи ===
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("New todo") },
                    label = { Text("Enter task") },
                    singleLine = true
                )

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = {
                        viewModel.addTodo(text.trim())
                        text = ""
                    },
                    enabled = text.isNotBlank()
                ) {
                    Text("Add")
                }
            }
        }

        // === Список задач — занимает всё оставшееся место ===
        LazyColumn(
            modifier = Modifier
                .weight(1f)  // КЛЮЧЕВОЕ: заставляет список занять всё оставшееся пространство
                .animateContentSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = viewModel.todos,
                key = { it.id }
            ) { todo ->
                TodoItemRow(
                    todo = todo,
                    onToggle = { viewModel.toggleTodo(todo.id) },
                    onDelete = { viewModel.deleteTodo(todo.id)}
                )
            }
        }
    }
}