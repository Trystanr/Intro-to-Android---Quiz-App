package com.trystan.demoapp.data

data class Question(
    val question: String,
    val categoryID: Int,
    val image: String,
    val answers: List<String>,
    val correctAnswer: String
)