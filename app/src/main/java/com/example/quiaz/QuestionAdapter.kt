package com.example.quiaz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter(private val context: Context, private var questions: List<Question>) :
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_question, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemCount(): Int {
        return questions.size
    }
    fun updateQuestions(newQuestions: List<Question>) {
        questions = newQuestions
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id: TextView = itemView.findViewById(R.id.question_id_text_view)
        private val questionTextView: TextView = itemView.findViewById(R.id.question_text_view)
        private val optionsRadioGroup: RadioGroup = itemView.findViewById(R.id.options_radio_group)

        fun bind(question: Question) {
            id.text = "ID: $queston.size"
            questionTextView.text = question.question
            optionsRadioGroup.removeAllViews()
            question.options.forEachIndexed { index, option ->
                val radioButton = RadioButton(context)
                radioButton.text = option
                radioButton.id = View.generateViewId()
                optionsRadioGroup.addView(radioButton)
            }
        }
    }
}