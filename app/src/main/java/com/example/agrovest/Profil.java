package com.example.agrovest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Profil extends AppCompatActivity {
    private TextView profil,profilnama,profiljeniskelamin,profilusia,profilusername;
    private SharedPreferences sharedPreferences;
    private Button btnedit;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        CardView cardView = findViewById(R.id.cardView);
        profil = findViewById(R.id.profil);
        profilnama = findViewById(R.id.profilnama);
        profilusia = findViewById(R.id.profilusia);
        profiljeniskelamin = findViewById(R.id.profiljeniskelamin);
        profilusername = findViewById(R.id.profilusername);
        btnedit = findViewById(R.id.edit);
        btnback = findViewById(R.id.back);

        int backgroundColor = cardView.getCardBackgroundColor().getDefaultColor();
        float alphaValue = 0.5f;
        int alphaColor = (int) (alphaValue * 255) << 24 | (backgroundColor & 0x00FFFFFF);
        cardView.setCardBackgroundColor(alphaColor);

        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String prof = sharedPreferences.getString("nama", null);
        int usia = sharedPreferences.getInt("usia", 0);
        String jeniskelamin = sharedPreferences.getString("jenis_kelamin",null);
        String username = sharedPreferences.getString("username",null);
        profil.setText(prof);
        profilnama.setText(prof);
        profilusia.setText(String.valueOf(usia));
        profiljeniskelamin.setText(jeniskelamin);
        profilusername.setText(username);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profil.this, edit.class);
                startActivity(intent);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profil.this, Homepage.class);
                startActivity(intent);
            }
        });
    }
}
