package com.example.quiaz

class QuestionRepository {
    private val questions = listOf(
        Question("What is the capital of France?", listOf("Paris", "London", "Berlin", "Rome"), 0),
        Question("What is the largest planet in our solar system?", listOf("Earth", "Saturn", "Jupiter", "Uranus"), 2),
        Question("What is the smallest country in the world?", listOf("Vatican City", "Monaco", "Nauru", "Tuvalu"), 0)
    )

    fun getQuestions(): List<Question> {
        return questions
    }
}