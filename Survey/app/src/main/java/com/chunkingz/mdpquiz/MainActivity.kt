package com.chunkingz.mdpquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

// MainActivity.kt
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnFoodPreferences = findViewById<Button>(R.id.btnFoodPreferences)
        val btnDietaryHabits = findViewById<Button>(R.id.btnDietaryHabits)

        btnFoodPreferences.setOnClickListener {
            startActivity(Intent(this, SurveyActivity::class.java).apply {
                putExtra("surveyType", "Food Preferences")
            })
        }

        btnDietaryHabits.setOnClickListener {
            startActivity(Intent(this, SurveyActivity::class.java).apply {
                putExtra("surveyType", "Dietary Habits")
            })
        }
    }
}
