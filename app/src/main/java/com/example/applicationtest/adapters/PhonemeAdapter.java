package com.example.applicationtest.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationtest.R;
import com.example.applicationtest.models.Verb;

import java.util.List;

public class PhonemeAdapter extends RecyclerView.Adapter<PhonemeAdapter.PhonemeViewHolder> {

    private final List<Verb.CountryWord> countriesWord;
    private final Context context;

    public PhonemeAdapter(Context context, List<Verb.CountryWord> countriesWord) {
        this.context = context;
        this.countriesWord = countriesWord;
    }

    @NonNull
    @Override
    public PhonemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phoneme, parent, false);
        return new PhonemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhonemeViewHolder holder, int position) {
        Verb.CountryWord countryWord = countriesWord.get(position);

        // Mostrar encabezado del país con bandera
        holder.countryHeader.setText(String.format("%s %s %s", countryWord.getCountry(), countryWord.getPhonetic(), countryWord.getWordName()));
        holder.countryIcon.setImageResource(countryWord.getCountry().equals("UK") ? R.drawable.ic_uk_flag : R.drawable.ic_us_flag);

        // Limpiar contenedor de fonemas
        holder.phonemeContainer.removeAllViews();

        // Agregar fonemas dinámicamente
        for (Verb.Phoeneme phoeneme : countryWord.getCharacters()) {
            LinearLayout phonemeRow = new LinearLayout(context);
            phonemeRow.setOrientation(LinearLayout.HORIZONTAL);
            phonemeRow.setPadding(0, 8, 0, 8);

            // Ícono de audio
            ImageView audioIcon = new ImageView(context);
            audioIcon.setLayoutParams(new LinearLayout.LayoutParams(48, 48));
            audioIcon.setImageResource(R.drawable.ic_audio);
            audioIcon.setOnClickListener(v -> {
                Log.d("AudioClick", "URL del audio: " + phoeneme.getAudioPhoenemeUrl());
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(phoeneme.getAudioPhoenemeUrl());
                    mediaPlayer.setOnPreparedListener(MediaPlayer::start);
                    mediaPlayer.setOnCompletionListener(mp -> {
                        mp.release(); // Liberar el MediaPlayer después de la reproducción
                    });
                    mediaPlayer.prepareAsync(); // Preparar de forma asíncrona
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Texto del fonema
            TextView phonemeText = new TextView(context);
            phonemeText.setText(String.format("%s as in %s", phoeneme.getPhoenemeName(), phoeneme.getWordExample()));
            phonemeText.setTextSize(14);
            phonemeText.setPadding(16, 0, 0, 0);

            phonemeRow.addView(audioIcon);
            phonemeRow.addView(phonemeText);

            holder.phonemeContainer.addView(phonemeRow);
        }
    }

    @Override
    public int getItemCount() {
        return countriesWord.size();
    }

    public static class PhonemeViewHolder extends RecyclerView.ViewHolder {
        TextView countryHeader;
        ImageView countryIcon; // Declarar el ImageView
        LinearLayout phonemeContainer;

        public PhonemeViewHolder(@NonNull View itemView) {
            super(itemView);
            countryHeader = itemView.findViewById(R.id.countryHeader);
            countryIcon = itemView.findViewById(R.id.countryIcon); // Inicializar el ImageView
            phonemeContainer = itemView.findViewById(R.id.phonemeContainer);
        }
    }
}