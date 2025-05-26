package com.example.applicationtest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationtest.api.ApiService;
import com.example.applicationtest.api.RetrofitClient;
import com.example.applicationtest.auth.models.RegisterRequest;
import com.example.applicationtest.auth.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
//                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
//                    intent.putExtra("fullName", fullName);
//                    intent.putExtra("email", email);
//                    intent.putExtra("password", password);
//                    intent.putExtra("englishLevel", englishLevel);
//                    intent.putExtra("learningGoals", learningGoals.toString());
//                    startActivity(intent);
                    registerUser(fullName, email, password, englishLevel, learningGoals.toString());
                }
            }
        });
    }


    private void registerUser(String fullName, String email, String password, String englishLevel, String learningGoals) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

        // Dividir el nombre completo en nombre y apellido
        String[] nameParts = fullName.split(" ", 2);
        String name = nameParts[0];
        String lastname = nameParts.length > 1 ? nameParts[1] : "";

        RegisterRequest registerRequest = new RegisterRequest(name, lastname, email, englishLevel, learningGoals, password);

        // Mostrar el loader
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registrando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        apiService.registerUser(registerRequest).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                progressDialog.dismiss(); // Ocultar el loader

                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Register", "Respuesta exitosa: " + response.body().toString());

                    // Mostrar alerta de éxito
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Registro exitoso")
                            .setMessage("El usuario se ha registrado correctamente.")
                            .setPositiveButton("Aceptar", (dialog, which) -> {
                                // Navegar a DashboardActivity
                                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                                intent.putExtra("fullName", fullName);
                                intent.putExtra("email", email);
                                intent.putExtra("englishLevel", englishLevel);
                                intent.putExtra("learningGoals", learningGoals);
                                startActivity(intent);
                                finish();
                            })
                            .show();
                } else {
                    Log.e("Register", "Error en la respuesta: " + response.code());
                    Toast.makeText(MainActivity.this, "Error al registrar usuario: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressDialog.dismiss(); // Ocultar el loader
                Log.e("Register", "Error en la solicitud: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}