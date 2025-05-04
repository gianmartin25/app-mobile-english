package com.example.applicationtest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int currentStep = 1;
    private StepManager stepManager;
    private FormValidator formValidator;
    private FormSubmitter formSubmitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        TextView stepTitle = findViewById(R.id.stepTitle);
        TextView step1Indicator = findViewById(R.id.step1Indicator);
        TextView step2Indicator = findViewById(R.id.step2Indicator);
        TextView step3Indicator = findViewById(R.id.step3Indicator);

        LinearLayout step1 = findViewById(R.id.step1);
        LinearLayout step2 = findViewById(R.id.step2);
        LinearLayout step3 = findViewById(R.id.step3);

        Button btnBack = findViewById(R.id.btnBack);
        Button btnNext = findViewById(R.id.btnNext);

        // Inicializar clases
        stepManager = new StepManager(stepTitle, step1Indicator, step2Indicator, step3Indicator, step1, step2, step3);
        formValidator = new FormValidator();
        formSubmitter = new FormSubmitter();

        // Configurar estado inicial
        stepManager.updateStepIndicators(currentStep, this);
        stepManager.updateStepViews(currentStep);

        // Listeners de botones
        btnBack.setOnClickListener(v -> {
            if (currentStep > 1) {
                currentStep--;
                stepManager.updateStepIndicators(currentStep, this);
                stepManager.updateStepViews(currentStep);
            }
        });

        btnNext.setOnClickListener(v -> {
            if (formValidator.validateStep(currentStep)) {
                if (currentStep < 3) {
                    currentStep++;
                    stepManager.updateStepIndicators(currentStep, this);
                    stepManager.updateStepViews(currentStep);
                } else {
                    // Capturar valores del formulario
                    String fullName = ((TextView) findViewById(R.id.fullName)).getText().toString();
                    String email = ((TextView) findViewById(R.id.email)).getText().toString();
                    String password = ((TextView) findViewById(R.id.password)).getText().toString();

                    String englishLevel = "";
                    if (((RadioButton) findViewById(R.id.beginner)).isChecked()) {
                        englishLevel = "Principiante (A1)";
                    } else if (((RadioButton) findViewById(R.id.elementary)).isChecked()) {
                        englishLevel = "Básico (A2)";
                    } else if (((RadioButton) findViewById(R.id.intermediate)).isChecked()) {
                        englishLevel = "Intermedio (B1)";
                    } else if (((RadioButton) findViewById(R.id.upperIntermediate)).isChecked()) {
                        englishLevel = "Intermedio Alto (B2)";
                    } else if (((RadioButton) findViewById(R.id.advanced)).isChecked()) {
                        englishLevel = "Avanzado (C1-C2)";
                    }

                    StringBuilder learningGoals = new StringBuilder();
                    if (((CheckBox) findViewById(R.id.goalGrammar)).isChecked()) {
                        learningGoals.append("Gramática, ");
                    }
                    if (((CheckBox) findViewById(R.id.goalVocabulary)).isChecked()) {
                        learningGoals.append("Vocabulario, ");
                    }
                    if (((CheckBox) findViewById(R.id.goalListening)).isChecked()) {
                        learningGoals.append("Comprensión auditiva, ");
                    }
                    if (((CheckBox) findViewById(R.id.goalSpeaking)).isChecked()) {
                        learningGoals.append("Expresión oral, ");
                    }
                    if (((CheckBox) findViewById(R.id.goalWriting)).isChecked()) {
                        learningGoals.append("Expresión escrita, ");
                    }

                    // Eliminar la última coma y espacio
                    if (learningGoals.length() > 0) {
                        learningGoals.setLength(learningGoals.length() - 2);
                    }

                    // Pasar los valores a DashboardActivity
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    intent.putExtra("fullName", fullName);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    intent.putExtra("englishLevel", englishLevel);
                    intent.putExtra("learningGoals", learningGoals.toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}