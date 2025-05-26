package com.example.applicationtest;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;


public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        try {
            // Configuración del BottomNavigationView
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            if (bottomNavigationView == null) {
                throw new NullPointerException("BottomNavigationView no encontrado");
            }



            String fullName = getIntent().getStringExtra("fullName");
            String email = getIntent().getStringExtra("email");
            String englishLevel = getIntent().getStringExtra("englishLevel");
            String learningGoals = getIntent().getStringExtra("learningGoals");

            Bundle profileData = new Bundle();
            profileData.putString("fullName", fullName);
            profileData.putString("email", email);
            profileData.putString("englishLevel", englishLevel);
            profileData.putString("learningGoals", learningGoals);

            if (savedInstanceState == null) {
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(profileData);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, profileFragment)
                        .commit();
            }

            bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.nav_profile) {
                    ProfileFragment profileFragment = new ProfileFragment();
                    profileFragment.setArguments(profileData);
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

            // Configuración del Navigation Drawer
            drawerLayout = findViewById(R.id.drawer_layout);
            if (drawerLayout == null) {
                throw new NullPointerException("DrawerLayout no encontrado");
            }

            NavigationView navigationView = findViewById(R.id.navigation_view);
            if (navigationView == null) {
                throw new NullPointerException("NavigationView no encontrado");
            }

            toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            navigationView.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_verbs) {
                    // Lógica para verbos
                } else if (id == R.id.nav_nouns) {
                    // Lógica para sustantivos
                } else if (id == R.id.nav_adjectives) {
                    // Lógica para adjetivos
                } else if (id == R.id.nav_adverbs) {
                    // Lógica para adverbios
                }
                drawerLayout.closeDrawers();
                return true;
            });

        } catch (Exception e) {
            Log.e("DashboardActivity", "Error en onCreate: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}