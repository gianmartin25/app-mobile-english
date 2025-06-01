package com.example.applicationtest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.applicationtest.adapters.ImageAdapter;
import com.example.applicationtest.adapters.PhonemeAdapter;
import com.example.applicationtest.api.ApiService;
import com.example.applicationtest.api.RetrofitClient;
import com.example.applicationtest.models.Verb;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager;
    private View view;
    private int currentIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        RecyclerView phonemeRecyclerView = view.findViewById(R.id.phonemeRecyclerView);
        phonemeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        apiService.getVerbs().enqueue(new Callback<List<Verb>>() {
            @Override
            public void onResponse(Call<List<Verb>> call, Response<List<Verb>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Verb> verbs = response.body();
                    updateContent(verbs, currentIndex);

                    // Configurar botones de navegación
                    view.findViewById(R.id.btnPrevious).setOnClickListener(v -> {
                        if (currentIndex > 0) {
                            currentIndex--;
                            updateContent(verbs, currentIndex);
                        }
                    });

                    view.findViewById(R.id.btnNext).setOnClickListener(v -> {
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

        // Actualizar el carrusel de imágenes
        ImageAdapter imageAdapter = new ImageAdapter(getContext(), currentVerb.getImages());
        viewPager.setAdapter(imageAdapter);

        // Actualizar el RecyclerView de fonemas
        PhonemeAdapter phonemeAdapter = new PhonemeAdapter(getContext(), currentVerb.getCountriesWord());
        RecyclerView phonemeRecyclerView = view.findViewById(R.id.phonemeRecyclerView);
        phonemeRecyclerView.setAdapter(phonemeAdapter);
    }
}