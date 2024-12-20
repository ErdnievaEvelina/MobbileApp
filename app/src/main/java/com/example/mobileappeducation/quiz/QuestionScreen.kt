package com.example.mobileappeducation.quiz
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobileappeducation.ui.theme.BlueJC


@Composable
fun QuestionScreen(question:Question,onAnswerSelected:(Boolean)->Unit,onNextQuestion: () -> Unit){
     val selectedAnswer =remember { mutableStateOf("") }
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = question.text, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            question.answers.forEachIndexed { index, answer ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (selectedAnswer.value == answer),
                        onClick = {
                            selectedAnswer.value = answer
                            onAnswerSelected(index == question.correctAnswerIndex)
                        },
                        colors = RadioButtonDefaults.colors(BlueJC)
                    )
                    Text(text = answer)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Button(
                onClick = {
                    onNextQuestion()
                    selectedAnswer.value = "" // Сброс выбора после перехода
                },
                enabled = selectedAnswer.value.isNotEmpty() // Кнопка активна только при выборе ответа
            ) {
                Text("Следующий вопрос")
            }


        }


}

