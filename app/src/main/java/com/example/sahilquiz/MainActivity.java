package com.example.sahilquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView questionTextView;
    private RadioGroup answerOptions;
    private RadioButton option1RadioButton;
    private RadioButton option2RadioButton;
    private RadioButton option3RadioButton;
    private RadioButton option4RadioButton;
    private Button nextButton;

    private String[] questions = {
            "What is Billie Eilish's full name?",
            "Which of these songs is not by Billie Eilish?",
            "In which year did Billie Eilish release her debut album 'When We All Fall Asleep, Where Do We Go?'"
    };

    private String[][] options = {
            {"Billie Jean King", "Billie Ray Cyrus", "Billie Joel Armstrong", "Billie Eilish Pirate Baird O'Connell"},
            {"Bad Guy", "Ocean Eyes", "Shape of You", "Lovely"},
            {"2017", "2018", "2019", "2020"}
    };

    private int[] correctAnswers = {3, 2, 2}; // Index of correct answers

    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answerOptions = findViewById(R.id.answerOptions);
        option1RadioButton = findViewById(R.id.option1RadioButton);
        option2RadioButton = findViewById(R.id.option2RadioButton);
        option3RadioButton = findViewById(R.id.option3RadioButton);
        option4RadioButton = findViewById(R.id.option4RadioButton);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (answerOptions.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Please select an answer.", Toast.LENGTH_SHORT).show();
                } else {
                    int selectedAnswerIndex = getSelectedAnswerIndex();
                    if (selectedAnswerIndex == correctAnswers[currentQuestionIndex]) {
                        Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                    }

                    // Move to the next question or finish the quiz
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.length) {
                        displayQuestion(currentQuestionIndex);
                    } else {
                        finishQuiz();
                    }
                }
            }
        });

        // Display the first question initially
        displayQuestion(currentQuestionIndex);
    }

    private void displayQuestion(int questionIndex) {
        // Display the current question and answer options
        questionTextView.setText(questions[questionIndex]);
        option1RadioButton.setText(options[questionIndex][0]);
        option2RadioButton.setText(options[questionIndex][1]);
        option3RadioButton.setText(options[questionIndex][2]);
        option4RadioButton.setText(options[questionIndex][3]);

        // Clear the previous selection
        answerOptions.clearCheck();
    }


    private int getSelectedAnswerIndex() {
        // Get the index of the selected answer option
        int selectedId = answerOptions.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String selectedAnswer = selectedRadioButton.getText().toString();
        for (int i = 0; i < options[currentQuestionIndex].length; i++) {
            if (selectedAnswer.equals(options[currentQuestionIndex][i])) {
                return i;
            }
        }
        return -1;
    }

    private void finishQuiz() {
        questionTextView.setText("Quiz completed!");
        answerOptions.setVisibility(View.INVISIBLE);
        nextButton.setEnabled(false);
    }
}
