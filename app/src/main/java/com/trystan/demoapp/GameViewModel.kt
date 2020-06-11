package com.trystan.demoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trystan.demoapp.data.Question

class GameViewModel: ViewModel() {

    private val _question = MutableLiveData<Question>()
    private val _currentQuestion = MutableLiveData<Int>()
    private val _score = MutableLiveData<Int>()
    private val _amountOfQuestions = MutableLiveData<Int>()
    private val _categoryQuestions = MutableLiveData<List<Question>>()
    private val _currentCategory = MutableLiveData<String>()

    fun setupGame(categoryID: Int) {
        val newQuestions = questions.filter { q -> q.categoryID == categoryID }
        _categoryQuestions.postValue(newQuestions)
        _question.value = newQuestions[0]
        _currentQuestion.value = 0
        _score.value = 0
        _amountOfQuestions.value = 4
        _currentCategory.value = categories[categoryID-1].text
    }

    val question: LiveData<Question> = _question
    val currentQuestion: LiveData<Int> = _currentQuestion
    val score: LiveData<Int> = _score
    var amountOfQuestion: LiveData<Int> = _amountOfQuestions
    var currentCategory: LiveData<String> = _currentCategory

    fun updateQuestion(index: Int) {
        _question.value = _categoryQuestions.value?.get(index.plus(1))
        _currentQuestion.value = index.plus(1)
    }

    fun checkQuestion(answer: Int) {
        val validAnswer: String? = question.value?.correctAnswer
        val submittedAnswer: String? = question.value?.answers?.get(answer)
        if (submittedAnswer == validAnswer) {
            _score.value = score.value?.plus(1)
        }
    }

    fun generateDummyList(size: Int):List<CategoryItem> {
        val list = ArrayList<CategoryItem>()
        for (i in 1 until size) {
            val item = CategoryItem(i, "Category $i", "noicon.jpg")
            list += item
        }
        return list
    }

    val categories = listOf(
        CategoryItem(1, "Premier League", "preml.png"),
        CategoryItem(2, "LaLiga Santander","lliga.png"),
        CategoryItem(3, "Bundesliga","bundes.png"),
        CategoryItem(4, "Ligue 1 Conforama","ligueune.png")
    )

    private val questions = listOf<Question>(
        Question("The Belge wonderpasser", 1, "kevindb.jpg", listOf("Thierry Henry", "Eden Hazard", "Kevin DeBruyne", "Atsel Witsel"), "Kevin DeBruyne"),
        Question("The tricky Frenchman", 1, "pogback.jpg", listOf("Roberto Carlos", "Luis Suarez", "Bruno Fernandes", "Paul Pogba"), "Paul Pogba"),
        Question("The clinical Senegal", 1, "smane.webp", listOf("Emi Buendíac", "Pepe Reina", "Sadio Mane", "Fernando Torres"), "Sadio Mane"),
        Question("The Gabonese finisher", 1, "auba.jpg", listOf("Pierre-Emerick Aubameyang", "Kun Agüero", "Dele Alli", "Ethan Ampadu"), "Pierre-Emerick Aubameyang"),

        Question("The best player to grace the Earth", 2, "messi.jpg", listOf("Adrián Marín", "Lionel Messi", "Aleix Vidal", "Ibrahim Amadou"), "Lionel Messi"),
        Question("The Portuguese talent", 2, "jfelix.jpg", listOf("João Félix", "Ronald Araujo", "Arthur", "Rony Lopes"), "João Félix"),
        Question("The best backswing in Wales", 2, "gbale.jpg", listOf("Juan Pérez", "Ivan Rakitić", "Neyder Lozano", "Gareth Bale"), "Gareth Bale"),
        Question("The Norwegian youngster", 2, "martino.jpg", listOf("Martin Ødegaard", "Raúl Navas", "Nemanja Maksimović", "Nabil Fekir"), "Martin Ødegaard"),

        Question("The groundbreaking finishing Pole", 3, "lewan.jpg", listOf("Julian Albrecht", "Robert Lewandowski", "Shinta Appelkamp", "Santiago Ascacíbar"), "Robert Lewandowski"),
        Question("The space analyst from Germany", 3, "muller.webp", listOf("David Alaba", "Tyler Adams", "Simon Asta", "Thomas Müller"), "Thomas Müller"),
        Question("The young English blitzkrieg", 3, "sancho.webp", listOf("Florian Baak", "Philipp Bargfrede", "Jadon Sancho", "Ayman Azhil"), "Jadon Sancho"),
        Question("The no nonsense German goalscorer", 3, "werner.jpg", listOf("Timo Becker", "Timo Werner", "Julian Baumgartlinger", "Ludwig Augustinsson"), "Timo Werner"),

        Question("The tricky Brazilian", 4, "neymar.jpg", listOf("Jordan Amavi", "Neymar da Silva Santos Júnior", "Anthony Lopes", "Dennis Appiah"), "Neymar da Silva Santos Júnior"),
        Question("The sublime French finisher", 4, "mbappe.jpg", listOf("Jordan Amavi", "Ander Herrera", "Denys Bain", "Kylian Mbappé"), "Kylian Mbappé"),
        Question("The retired Spanish superstar", 4, "cesc.jpg", listOf("Cesc Fàbregas", "Benjamin André", "Haitam Aleesami", "Youssef Aït Bennasser"), "Cesc Fàbregas"),
        Question("The Dutch musician", 4, "memphis.jpg", listOf("Yacine Adli", "Nayef Aguerd", "Memphis Depay", "Angel Di Maria"), "Memphis Depay")
    )



}