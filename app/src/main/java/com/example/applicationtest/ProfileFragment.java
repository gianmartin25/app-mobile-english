package com.example.applicationtest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Recuperar datos del Bundle
        Bundle args = getArguments();
        if (args != null) {
            String fullName = args.getString("fullName");
            String email = args.getString("email");
            String englishLevel = args.getString("englishLevel");
            String learningGoals = args.getString("learningGoals");

            // Mostrar los datos en la interfaz
            TextView profileInfo = view.findViewById(R.id.profileInfo);
            profileInfo.setText("Nombre: " + fullName + "\nCorreo: " + email +
                    "\nNivel de inglés: " + englishLevel +
                    "\nObjetivos: " + learningGoals);
        }

        return view;
    }
}