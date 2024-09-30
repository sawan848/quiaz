package com.example.quiaz

import android.os.Bundle
import android.widget.RadioButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiaz.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var questionViewModel: QuestionViewModel
    private lateinit var questionRepository: QuestionRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        questionRepository = QuestionRepository()
        questionViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).
        get(QuestionViewModel::class.java)


        questionAdapter = QuestionAdapter(this, questionViewModel.question.value?.let { listOf(it) } ?: listOf())
        var backButton = findViewById<Button>(R.id.back_button)
        var nextButton = findViewById<Button>(R.id.next_button)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = questionAdapter

        lifecycleScope.launch {
            questionViewModel.currentQuestionIndex.collect { index ->
                val question = questionViewModel.question.value
                if (question != null) {
                    questionAdapter.updateQuestions(listOf(question))
                    questionAdapter.notifyDataSetChanged()
                }
            }
        }

        backButton.setOnClickListener {
            questionViewModel.previousQuestion()
        }

        nextButton.setOnClickListener {
            questionViewModel.nextQuestion()
        }
    }

}