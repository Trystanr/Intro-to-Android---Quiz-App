package com.trystan.demoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trystan.demoapp.data.Question

class GameViewModel: ViewModel() {
    private val questions = listOf<Question>(
        Question("Our First Question", listOf("answerOne", "answerTwo","answerThree"), "answerOne"),
        Question("Our Second Question", listOf("answerOne", "answerTwo","answerThree"), "answerOne"),
        Question("Our Third Question", listOf("answerOne", "answerTwo","answerThree"), "answerOne"),
        Question("Our Fourth Question", listOf("answerOne", "answerTwo","answerThree"), "answerOne"),
        Question("Our Fifth Question", listOf("answerOne", "answerTwo","answerThree"), "answerOne"),
        Question("Our Sixth Question", listOf("answerOne", "answerTwo","answerThree"), "answerOne")
    )

    private val _question = MutableLiveData<Question>()
    private val _currentQuestion = MutableLiveData<Int>()
    private val _score = MutableLiveData<Int>()

    init {
        _score.value = 0
        _question.value = questions[0]
        _currentQuestion.value = 0
    }

    val question: LiveData<Question> = _question
    val currentQuestion: LiveData<Int> = _currentQuestion

    fun updateQuestion(index: Int) {
        _question.value = questions[index.plus(1)]
        _currentQuestion.value = index.plus(1)
    }
}