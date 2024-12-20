package com.example.mobileappeducation.quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuizApp(){
    val question=listOf(
        Question("Что выведет код t = (1, 2) print(2 * t) ?",listOf("(1,2,1,2)","[1, 2, 1, 2]","(1, 1, 2, 2)"),0),
        Question("Обязательно ли в Python объявлять тип переменной?", listOf("по желанию программиста","обязательно","объявлять тип не нужно"),2),
        Question("Оператор % позволяет вычислить...", listOf("частное от деления","остаток от деления","целую часть от деления"),1),
        Question("Назначение функции str() ?",listOf("преобразовать строку текста,состоящую из цифр,в целое число","преобразовать строку текста,состоящую из цифр,в дробное число","преобразовать число в строку текста"),2),
        Question("Что выведет код a='2'+'2' ?",listOf("4","22","2"),1),
        Question("Что выведет код а=str(2*2)*2 ?",listOf("44","22","2222"),0),
        Question("Что выведет код b='str'; print(b[3])?",listOf("t","r","Ошибка"),2),
        Question("Что выведет код а='string'; print(a[2:])?",listOf("ing","ring","st"),1),
        Question("Что выведет код a=[1]+[3,6] ?",listOf("[136]","[1,3,6]","10"),1),
        Question("Что выведет код b=[9,3,11,4]; print(max(b)) ?",listOf("4","3","11"),2)
        )
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    val progress = (currentQuestionIndex.toFloat() / question.size)
    Column {
        LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth().padding(top=10.dp))
        if (currentQuestionIndex < question.size) {
            QuestionScreen(question = question[currentQuestionIndex],
                onAnswerSelected = { isCorrect ->
                    if (isCorrect) score++
                },
                onNextQuestion = {
                    currentQuestionIndex++
                })
        } else {
            ResultScreen(score = score, total = question.size)
        }
    }
}


