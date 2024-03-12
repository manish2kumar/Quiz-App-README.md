package com.example.quizapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ScoreActivity : AppCompatActivity() {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)


        // Display the score
        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)
        val score = intent.getIntExtra("SCORE", 0)
        scoreTextView.text = "Your Score: $score"

    }
}