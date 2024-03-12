package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var option1_btn: Button
    private lateinit var option2_btn: Button
    private lateinit var option3_btn: Button
    private lateinit var option4_btn: Button
    private lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private var score = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView= findViewById(R.id.questionTextView)
        option1_btn= findViewById(R.id.btnOption1)
        option2_btn= findViewById(R.id.btnOption2)
        option3_btn= findViewById(R.id.btnOption3)
        option4_btn= findViewById(R.id.btnOption4)


        // Load questions from JSON file
        loadQuestionsFromJson()

        // Display the first question
        showQuestion(currentQuestionIndex)



        // Set click listener for answers
        option1_btn.setOnClickListener {
            checkAnswer(option1_btn.text.toString())
        }
        option2_btn.setOnClickListener {
            checkAnswer(option2_btn.text.toString())
        }
        option3_btn.setOnClickListener {
            checkAnswer(option3_btn.text.toString())
        }
        option4_btn.setOnClickListener {
            checkAnswer(option4_btn.text.toString())
        }

        // Display the first question
        showQuestion(currentQuestionIndex)

    }

    private fun loadQuestionsFromJson() {
        // Load questions from the JSON file
        val json = assets.open("questions.json").bufferedReader().use { it.readText() }
        questions = Gson().fromJson(json, object : TypeToken<List<Question>>() {}.type)
    }

    private fun showQuestion(index: Int) {
        // Display question and options
        val currentQuestion = questions[index]
        questionTextView.text = currentQuestion.question
        option1_btn.text = currentQuestion.options[0]
        option2_btn.text = currentQuestion.options[1]
        option3_btn.text = currentQuestion.options[2]
        option4_btn.text = currentQuestion.options[3]

    }

    private fun checkAnswer(selectedOption: String) {
        val currentQuestion = questions[currentQuestionIndex]

        if (selectedOption == currentQuestion.correctAnswer) {
            // Correct answer
            score++
        }

        // Move to the next question or display the final score
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            showQuestion(currentQuestionIndex)
        } else {
            showFinalScore()
        }
    }

    private fun showFinalScore() {
        // Display the final score in a new activity
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("SCORE", score)
        startActivity(intent)
        finish()
    }


}