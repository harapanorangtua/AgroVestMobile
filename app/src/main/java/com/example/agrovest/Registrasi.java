package com.example.agrovest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Registrasi extends AppCompatActivity {

    private EditText etnama,etusia,etuser,etpass;
    private TextView etlog;
    private RadioGroup rgjekel;
    private RequestQueue requestQueue;
    private SharedPreferences sharedPreferences;
    private String URL = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-jmkmr/endpoint/insertPengguna";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        etnama = findViewById(R.id.etnama);
        rgjekel = findViewById(R.id.rgjekel);
        etusia = findViewById(R.id.etusia);
        etuser = findViewById(R.id.etuser);
        etpass = findViewById(R.id.etpass);
        etlog = findViewById(R.id.etlog);

        etlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registrasi.this, Login.class);
                startActivity(intent);
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        Button btn_reg = findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftar();
            }
        });
    }

    private void daftar() {
        final String nama = etnama.getText().toString().trim();
        final Integer usia = Integer.parseInt(etusia.getText().toString().trim());
        final String user = etuser.getText().toString().trim();
        final String pass = etpass.getText().toString().trim();
        final String hashedpassword = getMD5(pass);

        int selectedId = rgjekel.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        final String jenis_kelamin = radioButton.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nama", nama);
        editor.putString("jenis_kelamin", jenis_kelamin);
        editor.putInt("usia", usia);
        editor.putString("username", user);
        editor.putString("password", hashedpassword);
        editor.apply();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Registrasi.this, "Selamat Anda Berhasil registrasi", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Registrasi.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registrasi.this, "Error: " + error.toString(),Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(Registrasi.this);
                builder.setMessage("Registrasi Gagal. Coba lagi nanti")
                        .setNegativeButton("OK", null)
                        .create()
                        .show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nama", sharedPreferences.getString("nama", ""));
                params.put("jenis_kelamin", sharedPreferences.getString("jenis_kelamin", ""));
                params.put("usia", String.valueOf(sharedPreferences.getInt("usia", 0)));
                params.put("username", sharedPreferences.getString("username", ""));
                params.put("password", hashedpassword);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private String getMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}