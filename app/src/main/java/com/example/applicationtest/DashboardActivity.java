package com.example.applicationtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.applicationtest.api.LoginResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
            // Configuración de la Toolbar como ActionBar
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            // Configuración del Navigation Drawer
            drawerLayout = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.navigation_view);

            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // Obtener datos del usuario
            LoginResponse.User user = (LoginResponse.User) getIntent().getSerializableExtra("user");

            // Actualizar el encabezado del menú
            View headerView = navigationView.getHeaderView(0);
            TextView headerName = headerView.findViewById(R.id.header_name);
            ImageView headerImage = headerView.findViewById(R.id.header_image);

            headerName.setText(user.getName() + " " + user.getLastname());

            // Cargar la imagen del usuario (si existe)
            Glide.with(this)
                    .load(user.getProfileImage() != null && !user.getProfileImage().isEmpty() ? user.getProfileImage() : R.drawable.ic_us_flag) // Imagen por defecto
                    .placeholder(R.drawable.ic_us_flag) // Placeholder
                    .circleCrop() // Redondear la imagen
                    .into(headerImage);

            navigationView.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_verbs) {
                    navigateToHomeFragment("verbs");
                } else if (id == R.id.nav_nouns) {
                    navigateToHomeFragment("nouns");
                } else if (id == R.id.nav_adjectives) {
                    navigateToHomeFragment("adjectives");
                } else if (id == R.id.nav_articles) {
                    navigateToHomeFragment("articles");
                } else if (id == R.id.nav_logout) {
                    logout();
                }
                drawerLayout.closeDrawers();
                return true;
            });

            // Navegar al fragmento por defecto (verbs)
            if (savedInstanceState == null) {
                navigateToHomeFragment("verbs");
            }

        } catch (Exception e) {
            Log.e("DashboardActivity", "Error en onCreate: " + e.getMessage(), e);
        }
    }
    private void logout() {
        // Eliminar el token de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("access_token");
        editor.apply();

        // Verificar si el usuario está autenticado con Google
        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this,
                    new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build());
            googleSignInClient.signOut().addOnCompleteListener(task -> {
                // Redirigir al usuario a LoginActivity
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Limpiar el stack de actividades
                startActivity(intent);
                finish();
            });
        } else {
            // Redirigir al usuario a LoginActivity directamente
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Limpiar el stack de actividades
            startActivity(intent);
            finish();
        }
    }

    private void navigateToHomeFragment(String requestType) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("requestType", requestType);
        homeFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}