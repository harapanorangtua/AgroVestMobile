package com.example.agrovest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class edit extends AppCompatActivity {

    private EditText etnamaprofil,etusiaprofil,etuserprofil;

    private RadioGroup etjekelprofil;
    private String url = "";
    private String id = "";
    private String nama = "";
    private String jenisKelamin = "";
    private int usia;
    private String username = "";
    private Button btnedit;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etnamaprofil = findViewById(R.id.etnamaprofil);
        etusiaprofil = findViewById(R.id.etusiaprofil);
        etjekelprofil = findViewById(R.id.etjekelprofil);
        etuserprofil = findViewById(R.id.etuserprofil);
        btnedit = findViewById(R.id.btnedit);

        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        etnamaprofil.setText(sharedPreferences.getString("nama", null));
        etusiaprofil.setText(String.valueOf(sharedPreferences.getInt("usia", 0)));
        etjekelprofil.check(sharedPreferences.getInt("jenis_kelamin_id", R.id.rblaki));
        etuserprofil.setText(sharedPreferences.getString("username", null));

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = sharedPreferences.getString("id", null);
                nama = etnamaprofil.getText().toString();
                jenisKelamin = getSelectedRadioButtonText(etjekelprofil);
                usia = Integer.parseInt(etusiaprofil.getText().toString());
                username = etuserprofil.getText().toString();

                url = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-jmkmr/endpoint/updatePenggunaById?id=" + id;

                sendDataToServer();
            }
        });
    }

    private String getSelectedRadioButtonText(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            return selectedRadioButton.getText().toString();
        } else {
            return "";
        }
    }

    private void sendDataToServer() {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("id", id);
                        editor.putString("username", username);
                        editor.putString("nama", nama);
                        editor.putInt("usia", usia);
                        editor.putString("jenis_kelamin", jenisKelamin);
                        editor.apply();

                        Intent intent = new Intent(edit.this, Profil.class);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(edit.this, "Edit data anda gagal", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("nama", nama);
                params.put("jenis_kelamin", jenisKelamin);
                params.put("usia", String.valueOf(usia));
                params.put("username", username);
                params.put("password", sharedPreferences.getString("password",null));
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}