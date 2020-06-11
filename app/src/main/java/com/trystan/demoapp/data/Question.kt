package com.trystan.demoapp.data

data class Question(
    val question: String,
    val answers: List<String>,
    val correctAnswer: String
)