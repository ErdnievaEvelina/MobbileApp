package com.example.mobileappeducation.quiz

data class Question(
    val text:String,
    val answers:List<String>,
    val correctAnswerIndex:Int
)
