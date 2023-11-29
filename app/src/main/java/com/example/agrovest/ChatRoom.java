package com.example.agrovest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatRoom extends AppCompatActivity {

    private RequestQueue requestQueue;

    private List<ChatModel> chatList;
    private SharedPreferences sharedPreferences;
    private ChatAdapter chatAdapter;

    private RecyclerView recyclerView;
    private EditText editTextMessage;
    private ImageButton btback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        recyclerView = findViewById(R.id.recyclerViewChat);
        btback = findViewById(R.id.btnback);
        editTextMessage = findViewById(R.id.editTextMessage);
        Button buttonSend = findViewById(R.id.buttonSend);
        requestQueue = Volley.newRequestQueue(this);
        getAllObrolan();

        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatRoom.this, Homepage.class);
                startActivity(intent);
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editTextMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    insertObrolan(message);
                    editTextMessage.setText("");
                }
            }
        });

    }

    private String getUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("username", "");
    }

    private void getAllObrolan() {
        String url = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-jmkmr/endpoint/getAllObrolan";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String responseBody = response.toString();

                            JSONArray jsonArray = new JSONArray(responseBody);
                            System.out.println(jsonArray);
                            chatList = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ChatModel chat = new ChatModel();
                                chat.setPengirim(jsonObject.getString("pengirim"));
                                System.out.println(jsonObject.getString("pengirim"));
                                chat.setWaktu(jsonObject.getString("waktu"));
                                System.out.println(jsonObject.getString("waktu"));
                                chat.setObrolan(jsonObject.getString("obrolan"));
                                System.out.println(jsonObject.getString("obrolan"));
                                chatList.add(chat);
                            }

                            runOnUiThread(()->{
                                chatAdapter = new ChatAdapter(getApplicationContext(), chatList);
                                recyclerView.setAdapter(chatAdapter);
                                chatAdapter.notifyDataSetChanged();
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error: " + error.toString());
                    }
                });
        requestQueue.add(stringRequest);
    }


    private void insertObrolan(String message) {
        String url = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-jmkmr/endpoint/insertObrolan";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        runOnUiThread(()-> {
                            getAllObrolan();
                        });
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error: " + error.toString());
                        Toast.makeText(ChatRoom.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("pengirim", getUsername());
                params.put("penerima", "all");
                params.put("waktu", String.valueOf(new Date()));
                params.put("obrolan", message);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
