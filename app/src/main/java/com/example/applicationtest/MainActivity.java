package com.example.applicationtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int currentStep = 1;
    private TextView stepTitle;
    private TextView step1Indicator, step2Indicator, step3Indicator;
    private LinearLayout step1, step2, step3;
    private Button btnBack, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        stepTitle = findViewById(R.id.stepTitle);
        step1Indicator = findViewById(R.id.step1Indicator);
        step2Indicator = findViewById(R.id.step2Indicator);
        step3Indicator = findViewById(R.id.step3Indicator);

        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);

        // Set initial state
        updateStepIndicators();
        updateStepViews();

        // Button listeners
        btnBack.setOnClickListener(v -> {
            if (currentStep > 1) {
                currentStep--;
                updateStepIndicators();
                updateStepViews();
                updateButtons();
            }
        });

        btnNext.setOnClickListener(v -> {
            if (validateCurrentStep()) {
                if (currentStep < 3) {
                    currentStep++;
                    updateStepIndicators();
                    updateStepViews();
                    updateButtons();
                } else {
                    // Final step - Submit form
                    submitForm();
                }
            }
        });
    }

    private void updateStepIndicators() {
        // Reset all indicators
        step1Indicator.setBackgroundResource(R.drawable.step_indicator_inactive);
        step2Indicator.setBackgroundResource(R.drawable.step_indicator_inactive);
        step3Indicator.setBackgroundResource(R.drawable.step_indicator_inactive);
        step1Indicator.setTextColor(getResources().getColor(R.color.gray_dark));
        step2Indicator.setTextColor(getResources().getColor(R.color.gray_dark));
        step3Indicator.setTextColor(getResources().getColor(R.color.gray_dark));

        // Activate current step
        switch (currentStep) {
            case 1:
                step1Indicator.setBackgroundResource(R.drawable.step_indicator_active);
                step1Indicator.setTextColor(getResources().getColor(R.color.white));
                stepTitle.setText("Información Personal");
                break;
            case 2:
                step2Indicator.setBackgroundResource(R.drawable.step_indicator_active);
                step2Indicator.setTextColor(getResources().getColor(R.color.white));
                stepTitle.setText("Nivel de Inglés");
                break;
            case 3:
                step3Indicator.setBackgroundResource(R.drawable.step_indicator_active);
                step3Indicator.setTextColor(getResources().getColor(R.color.white));
                stepTitle.setText("Objetivos de Aprendizaje");
                break;
        }
    }

    private void updateStepViews() {
        step1.setVisibility(currentStep == 1 ? View.VISIBLE : View.GONE);
        step2.setVisibility(currentStep == 2 ? View.VISIBLE : View.GONE);
        step3.setVisibility(currentStep == 3 ? View.VISIBLE : View.GONE);
    }

    private void updateButtons() {
        btnBack.setVisibility(currentStep > 1 ? View.VISIBLE : View.INVISIBLE);

        if (currentStep < 3) {
            btnNext.setText("Siguiente");
        } else {
            btnNext.setText("Registrarse");
        }
    }

    private boolean validateCurrentStep() {
        // Add validation logic for each step
        // Return true if validation passes, false otherwise
        return true;
    }

    private void submitForm() {
        // Handle form submission
        // Collect all data and send to server
    }
}