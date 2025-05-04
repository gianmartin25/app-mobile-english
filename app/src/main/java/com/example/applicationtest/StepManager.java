package com.example.applicationtest;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class StepManager {
    private final TextView stepTitle;
    private final TextView step1Indicator, step2Indicator, step3Indicator;
    private final View step1, step2, step3;

    public StepManager(TextView stepTitle, TextView step1Indicator, TextView step2Indicator, TextView step3Indicator,
                       View step1, View step2, View step3) {
        this.stepTitle = stepTitle;
        this.step1Indicator = step1Indicator;
        this.step2Indicator = step2Indicator;
        this.step3Indicator = step3Indicator;
        this.step1 = step1;
        this.step2 = step2;
        this.step3 = step3;
    }

    public void updateStepIndicators(int currentStep, Context context) {
        resetIndicators(context);
        switch (currentStep) {
            case 1:
                activateStep(step1Indicator, "Información Personal", context);
                break;
            case 2:
                activateStep(step2Indicator, "Nivel de Inglés", context);
                break;
            case 3:
                activateStep(step3Indicator, "Objetivos de Aprendizaje", context);
                break;
        }
    }

    public void updateStepViews(int currentStep) {
        step1.setVisibility(currentStep == 1 ? View.VISIBLE : View.GONE);
        step2.setVisibility(currentStep == 2 ? View.VISIBLE : View.GONE);
        step3.setVisibility(currentStep == 3 ? View.VISIBLE : View.GONE);
    }

    private void resetIndicators(Context context) {
        step1Indicator.setBackgroundResource(R.drawable.step_indicator_inactive);
        step2Indicator.setBackgroundResource(R.drawable.step_indicator_inactive);
        step3Indicator.setBackgroundResource(R.drawable.step_indicator_inactive);
        step1Indicator.setTextColor(context.getResources().getColor(R.color.gray_dark));
        step2Indicator.setTextColor(context.getResources().getColor(R.color.gray_dark));
        step3Indicator.setTextColor(context.getResources().getColor(R.color.gray_dark));
    }

    private void activateStep(TextView stepIndicator, String title, Context context) {
        stepIndicator.setBackgroundResource(R.drawable.step_indicator_active);
        stepIndicator.setTextColor(context.getResources().getColor(R.color.white));
        stepTitle.setText(title);
    }
}