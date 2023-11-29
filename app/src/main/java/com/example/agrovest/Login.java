package com.example.agrovest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {

    private EditText etuser,etpass;
    private TextView etreg;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etuser = findViewById(R.id.etuser);
        etpass = findViewById(R.id.etpass);

        findViewById(R.id.btnlog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etuser.getText().toString();
                String password = etpass.getText().toString();
                loginUser(username,password);
            }
        });

        etreg = findViewById(R.id.etreg);

        etreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registrasi.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser(final String username, final String password) {
        final String md5password = getMD5(password);
        String loginurl = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-jmkmr/endpoint/getPenggunaByUsernamePassword"
                          + "?username=" + username + "&password=" + md5password;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    if (jsonArray.length() > 0) {
                        JSONObject row = jsonArray.getJSONObject(0);

                        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("id", row.getString("_id"));
                        editor.putString("username", row.getString("username"));
                        editor.putString("password", row.getString("password"));
                        editor.putString("nama", row.getString("nama"));
                        editor.putInt("usia", row.getInt("usia"));
                        editor.putString("jenis_kelamin", row.getString("jenis_kelamin"));
                        editor.apply();

                        Intent intent = new Intent(Login.this, Homepage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Login Gagal, Silahkan Cek kembali Username dan Password Anda", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Login.this, "Terjadi Error saat Login. Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Log.e("LoginActivity", "Error:" + error.getMessage());
                    Toast.makeText(Login.this, "Terjadi Error Saat Login. Silakan Coba Lagi",Toast.LENGTH_SHORT).show();
                }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
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