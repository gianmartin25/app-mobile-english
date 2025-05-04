package com.example.applicationtest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.applicationtest.adapters.ImageAdapter;
import com.example.applicationtest.adapters.PhonemeAdapter;
import com.example.applicationtest.api.ApiService;
import com.example.applicationtest.api.RetrofitClient;
import com.example.applicationtest.models.Verb;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class HomeFragment extends Fragment {

    private ViewPager2 viewPager; // Declarar como variable de instancia

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        apiService.getVerbs().enqueue(new Callback<List<Verb>>() {
            @Override
            public void onResponse(Call<List<Verb>> call, Response<List<Verb>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<String> images = response.body().get(0).getImages();
                    List<Verb.CountryVerb> countriesVerb = response.body().get(0).getCountriesVerb();

                    // Configurar el carrusel
                    ImageAdapter imageAdapter = new ImageAdapter(getContext(), images);
                    if (viewPager != null) {
                        viewPager.setAdapter(imageAdapter);
                    }

                    // Configurar el RecyclerView
                    RecyclerView phonemeRecyclerView = view.findViewById(R.id.phonemeRecyclerView);
                    phonemeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    PhonemeAdapter phonemeAdapter = new PhonemeAdapter(getContext(), countriesVerb);
                    phonemeRecyclerView.setAdapter(phonemeAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Verb>> call, Throwable t) {
                Log.e("API_ERROR", "Error en la petición: " + t.getMessage());
            }
        });
    }

    private View view; // Declarar como variable de instancia

    private int currentIndex = 0; // Índice actual

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.viewPager); // Inicializar viewPager
        Button btnPrevious = view.findViewById(R.id.btnPrevious);
        Button btnNext = view.findViewById(R.id.btnNext);

        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        apiService.getVerbs().enqueue(new Callback<List<Verb>>() {
            @Override
            public void onResponse(Call<List<Verb>> call, Response<List<Verb>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Verb> verbs = response.body();
                    updateContent(verbs, currentIndex);

                    btnPrevious.setOnClickListener(v -> {
                        if (currentIndex > 0) {
                            currentIndex--;
                            updateContent(verbs, currentIndex);
                        }
                    });

                    btnNext.setOnClickListener(v -> {
                        if (currentIndex < verbs.size() - 1) {
                            currentIndex++;
                            updateContent(verbs, currentIndex);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Verb>> call, Throwable t) {
                Log.e("API_ERROR", "Error en la petición: " + t.getMessage());
            }
        });

        return view;
    }

    private void updateContent(List<Verb> verbs, int index) {
        Verb currentVerb = verbs.get(index);

        // Actualizar el carrusel
        List<String> images = currentVerb.getImages();
        ImageAdapter imageAdapter = new ImageAdapter(getContext(), images);
        viewPager.setAdapter(imageAdapter);

        // Actualizar el RecyclerView
        List<Verb.CountryVerb> countriesVerb = currentVerb.getCountriesVerb();
        RecyclerView phonemeRecyclerView = view.findViewById(R.id.phonemeRecyclerView);
        phonemeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PhonemeAdapter phonemeAdapter = new PhonemeAdapter(getContext(), countriesVerb);
        phonemeRecyclerView.setAdapter(phonemeAdapter);
    }
}