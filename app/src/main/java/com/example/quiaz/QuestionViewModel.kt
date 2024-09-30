package com.example.quiaz

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuestionViewModel(private val questionRepository:
                        QuestionRepository = QuestionRepository()) : ViewModel() {
    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex

    private val _question = MutableStateFlow<Question?>(null)
    val question: StateFlow<Question?> = _question

    private val _isBackButtonEnabled = MutableStateFlow(false)
    val isBackButtonEnabled: StateFlow<Boolean> = _isBackButtonEnabled

    private val _isNextButtonEnabled = MutableStateFlow(true)
    val isNextButtonEnabled: StateFlow<Boolean> = _isNextButtonEnabled

    init {
        loadQuestion()
    }

    fun loadQuestion() {
        val questions = questionRepository.getQuestions()
        if (questions.isNotEmpty()) {
            _question.value = questions[_currentQuestionIndex.value]
            _isBackButtonEnabled.value = _currentQuestionIndex.value > 0
            _isNextButtonEnabled.value = _currentQuestionIndex.value < questions.size - 1
        }
    }

    fun previousQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value--
            loadQuestion()
        } else {
            _currentQuestionIndex.value = questionRepository.getQuestions().size - 1
            loadQuestion()
        }
    }

    fun nextQuestion() {
        if (_currentQuestionIndex.value < questionRepository.getQuestions().size - 1) {
            _currentQuestionIndex.value++
            loadQuestion()
        } else {
            _currentQuestionIndex.value = 0
            loadQuestion()
        }
    }
}