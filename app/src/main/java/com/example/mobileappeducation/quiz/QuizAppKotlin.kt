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
fun QuizAppKotlin(){
    val question=listOf(
        Question("Чего не предлагает dataclass?",listOf("Авто-генерируемый метод toString()","Метод copy(...), для создания копии экземпляров.","Автоматическое преобразование из/в JSON"),2),
        Question("В чем разница между a и b? var a: String? = \"KotlinQuiz\"\n" +
                "var b: String = \"KotlinQuiz\"",listOf("b никогда не сможет стать null","a является final и не может быть изменено","a является volatile, как в Java"),0),
        Question("Какой тип у arr?",listOf("Int[]","IntArray","Array<Int>"),2),
        Question("Для чего нужен оператор !! ?",listOf("Это оператор модуля, аналог % в Java","Он сравнивает два значения на тождественность","Он преобразует любое значение в ненулевой тип и выбрасывает исключение, если значение равно null"),2),
        Question("Укажите правильный синтаксис для преобразования строки “42” в long",listOf("val l: Long = <Long>\"42\"","val l: Long = \"42\".toLong()","val l: Long = Long.parseLong(\"42\")"),1),
        Question("Что такое корутины (coroutines)?",listOf("Автоматически сгенерированные методы hashCode() и equals() в data classes."," способ асинхронного выполнения кода без обычной блокировки потока.","Термин Kotlin, используемый в методах класса."),1),
        Question("Что делает этот код foo()()?",listOf("Вызывает функцию, которая вернется после вызова foo","Не скомпилируется","Создает двумерный массив"),0),
        Question("Совместим ли Kotlin с Java?",listOf("Kotlin может легко вызвать Java код и наоборот","Несовместим","Пока Kotlin запущен в JVM, он не может взаимодействовать с Java"),0),
        Question("Что из этого в настоящее время не поддерживается в Kotlin ?",listOf("NET CLR","JVM","JavaScript"),0),
        Question("Какое расширение полезно для сохранения файлов Kotlin?",listOf(".src",".kt",".kot"),1)
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