package com.example.applicationtest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Recuperar datos del Intent
        String fullName = getIntent().getStringExtra("fullName");
        String email = getIntent().getStringExtra("email");
        String englishLevel = getIntent().getStringExtra("englishLevel");
        String learningGoals = getIntent().getStringExtra("learningGoals");

        // Crear un Bundle con los datos
        Bundle profileData = new Bundle();
        profileData.putString("fullName", fullName);
        profileData.putString("email", email);
        profileData.putString("englishLevel", englishLevel);
        profileData.putString("learningGoals", learningGoals);

        // Cargar el fragmento inicial con datos
        if (savedInstanceState == null) {
            ProfileFragment profileFragment = new ProfileFragment();
            profileFragment.setArguments(profileData);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, profileFragment)
                    .commit();
        }

        // Manejar la navegación
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_profile) {
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(profileData); // Pasar datos al fragmento
                selectedFragment = profileFragment;
            } else if (item.getItemId() == R.id.nav_settings) {
                selectedFragment = new SettingsFragment();
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });
    }
}