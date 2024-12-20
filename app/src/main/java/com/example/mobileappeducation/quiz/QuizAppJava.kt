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
fun QuizAppJava(){
    val question=listOf(
        Question("Что такое Java?",listOf("Язык программирования","Машина","Чай"),0),
        Question("Какой вариант инициализации массива правильный?",listOf("int[] array={1,2,3}","int[] array=int[]","int array=[]"),0),
        Question("Какой тип данных не является примитивным?",listOf("boolean","string","float"),1),
        Question("Какого модификатора класса не существует?",listOf("foreign","strictfp","synchronized"),0),
        Question("Допустимы ли в Java вложенные циклы?",listOf("да","нет","зависит от случая"),0),
        Question("Какой класс позволяет делать консольный ввод с клавиатуры?",listOf("Scanner","Reader","Writer"),0),
        Question("Чему равно значение переменной byte b = 7;?",listOf("7.000","000 0111","0111"),1),
        Question("Какие основные принципы ООП Java?",listOf("Абстракция, наследование, полиморфизм и инкапсуляция","Наследование, полиморфизм и инкапсуляция","Только наследование"),0),
        Question("Где правильно присвоено новое значение к многомерному массиву?",listOf("a{0}{0} = 1;","a[0 0] = 1;","a[0][0] = 1;"),2),
        Question("Каждый файл должен называется…",listOf("по имени класса в нём","по имени названия пакета","как вам захочется"),0)
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