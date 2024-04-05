package com.chunkingz.mdpquiz
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
class SurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        val layoutSurvey = findViewById<LinearLayout>(R.id.layoutSurvey)
        val surveyType = intent.getStringExtra("surveyType")

        supportActionBar?.title = surveyType

        // Sample questions and options for Food Preferences survey
        val foodPreferencesQuestions = listOf(
            "What is your favorite cuisine?" to listOf("Chinese", "French", "Italian", "Indian", "Japanese", "Thai", "Turkish"),
            "How often do you eat out?" to listOf("Never", "Rarely", "Sometimes", "Frequently"),
            "Do you prefer spicy food?" to listOf("Yes", "No"),
            "Do you prefer vegetarian food?" to listOf("Yes", "No"),
            "Do you like seafood?" to listOf("Yes", "No")
        )

        val dietaryHabitsQuestions = listOf(
            "Are you vegetarian?" to listOf("Yes", "No"),
            "Do you prefer organic food?" to listOf("Yes", "No"),
            "Do you consume dairy products?" to listOf("Yes", "No"),
            "Do you eat fast food?" to listOf("Yes", "No"),
            "Do you have any food allergies?" to listOf("Yes", "No")
        )

        val surveyQuestions = when (surveyType) {
            "Food Preferences" -> foodPreferencesQuestions
            "Dietary Habits" -> dietaryHabitsQuestions
            else -> emptyList()
        }

        surveyQuestions.forEach { (question, options) ->
            val questionTextView = TextView(this)
            questionTextView.text = question
            layoutSurvey.addView(questionTextView)

            val radioGroup = RadioGroup(this)
            options.forEachIndexed { index, option ->
                val radioButton = RadioButton(this)
                radioButton.text = option
                radioButton.id = index
                radioGroup.addView(radioButton)
            }
            layoutSurvey.addView(radioGroup)
        }

        val submitBtn = Button(this)
        submitBtn.text = "Submit Survey"
        submitBtn.setOnClickListener {
            submitSurvey()
        }
        layoutSurvey.addView(submitBtn)
    }

    private fun submitSurvey() {
        val layoutSurvey = findViewById<LinearLayout>(R.id.layoutSurvey)
        val responses = StringBuilder()

        for (i in 0 until layoutSurvey.childCount) {
            val view = layoutSurvey.getChildAt(i)

            if (view is TextView) {
                // Assuming question TextViews are directly followed by RadioGroups
                val question = view.text.toString()
                responses.append("$question: ")
            } else if (view is RadioGroup) {
                val radioButtonId = view.checkedRadioButtonId
                val radioButton = view.findViewById<RadioButton>(radioButtonId)
                val answer = radioButton?.text ?: "No answer selected"
                responses.append("$answer\n")
            }
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Survey Responses")
        alertDialogBuilder.setMessage(responses.toString())
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}
