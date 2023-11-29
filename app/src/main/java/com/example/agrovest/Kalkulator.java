package com.example.agrovest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Kalkulator extends AppCompatActivity {

    private EditText etNamaDaerah, etLuasLahan, etN, etP, etK;

    private Spinner etNamaTanaman, etSatuanLuas;
    private Button btnHitung;
    private TextView etHasilKalkulasi;
    private ImageButton btbackkalku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);

        etNamaDaerah = findViewById(R.id.etnamadaerah);
        etNamaTanaman = findViewById(R.id.etnamatanaman);
        etLuasLahan = findViewById(R.id.etluaslahan);
        etSatuanLuas = findViewById(R.id.etsatuanluas);
        etN = findViewById(R.id.etn);
        etP = findViewById(R.id.etp);
        etK = findViewById(R.id.etk);
        btnHitung = findViewById(R.id.btnhitung);
        etHasilKalkulasi = findViewById(R.id.ethasilkalku);
        btbackkalku = findViewById(R.id.backalku);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitungKalkulasi();
            }
        });

        btbackkalku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kalkulator.this, Homepage.class);
                startActivity(intent);
            }
        });
    }

    private void hitungKalkulasi() {
        String namaDaerah = etNamaDaerah.getText().toString();
        String namaTanaman = etNamaTanaman.getSelectedItem().toString();
        if (namaDaerah.isEmpty() || namaTanaman.isEmpty() || etLuasLahan.getText().toString().isEmpty() || etSatuanLuas.getTouchables().isEmpty()
                || etN.getText().toString().isEmpty() || etP.getText().toString().isEmpty()
                || etK.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        double luasLahan;
        try {
            luasLahan = Double.parseDouble(etLuasLahan.getText().toString());
        } catch (NumberFormatException e) {
            etLuasLahan.setError("Masukkan angka yang valid");
            return;
        }
        double n, p, k;
        try {
            n = Double.parseDouble(etN.getText().toString());
            p = Double.parseDouble(etP.getText().toString());
            k = Double.parseDouble(etK.getText().toString());
        } catch (NumberFormatException e) {
            etN.setError("Masukkan angka yang valid");
            etP.setError("Masukkan angka yang valid");
            etK.setError("Masukkan angka yang valid");
            return;
        }

        double faktorKonversi = 1.0;
        if (etSatuanLuas.getSelectedItemPosition() != Spinner.INVALID_POSITION) {
            switch (etSatuanLuas.getSelectedItem().toString()) {
                case "Hektar":
                    faktorKonversi = 120.0;
                    break;
                case "m²":
                    faktorKonversi = 150.0;
                    break;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Pilih satuan luas terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        double luasLahanSetelahKonversi = luasLahan * faktorKonversi;

        double totalN = n * luasLahanSetelahKonversi;
        double totalP = p * luasLahanSetelahKonversi;
        double totalK = k * luasLahanSetelahKonversi;

        double totalPupuk = totalN + totalP + totalK;

        String hasilKalkulasi = "Hasil perhitungan untuk " + namaDaerah + " \n" +
                "Dengan Tanaman (" + namaTanaman + "):\n" +
                "Luas Lahan: " + luasLahanSetelahKonversi + "" + etSatuanLuas.getSelectedItem().toString() + "\n" +
                "N: " + totalN + "\n" +
                "P: " + totalP + "\n" +
                "K: " + totalK + "\n" +
                "Jumlah Pupuk yang Diperlukan:\n" +
                "Total Pupuk: " + totalPupuk;

        etHasilKalkulasi.setText(hasilKalkulasi);
    }
}
