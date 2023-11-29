package com.example.agrovest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class Homepage extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

                data = findViewById(R.id.data);
                ImageView profil = findViewById(R.id.btnprofil);
                CardView about = findViewById(R.id.btnabout);
                CardView pupuk = findViewById(R.id.btnpupuk);
                CardView tanaman = findViewById(R.id.btntanaman);
                CardView kalkulator = findViewById(R.id.btnkalkulator);
                ImageButton logout = findViewById(R.id.logout);
                CardView chat = findViewById(R.id.btnchat);


        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                String nama = sharedPreferences.getString("nama", null);
                data.setText(nama);

                profil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Homepage.this, Profil.class);
                        startActivity(intent);
                    }
                });

                about.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Homepage.this, About.class);
                        startActivity(intent);
                    }
                });

                pupuk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Homepage.this, Pupuk.class);
                        startActivity(intent);
                    }
                });

                tanaman.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Homepage.this, Tanaman.class);
                        startActivity(intent);
                    }
                });

                kalkulator.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Homepage.this, Kalkulator.class);
                        startActivity(intent);
                    }
                });

                chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Homepage.this, ChatRoom.class);
                        startActivity(intent);
                    }
                });

                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Homepage.this, Login.class);
                        startActivity(intent);
                    }
                });
            }

            public void onCardViewClick(View view) {
                AlertDialog.Builder soon = new AlertDialog.Builder(this);
                soon.setMessage("Fitur ini masih dalam pengembangan. Coming soon!");
                    soon.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = soon.create();
                    dialog.show();
                }
        }

