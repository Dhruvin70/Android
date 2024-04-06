package com.webapp.jokes;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Joke> jokeList;

    private TextView textView;

    private TextView message;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jokeList = new ArrayList<>();
        recyclerView = findViewById(R.id.joke);
        message = findViewById(R.id.msg);
       recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new JokeAdapter(getApplicationContext(),jokeList));

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        EditText number = findViewById(R.id.noOfJokes);

        findViewById(R.id.btnFetchJokes).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String text = number.getText().toString();
                showTost("Entered text: " + text);
                if ( Integer.parseInt(text) > 30){
                    message.setText("PLEASE ENTER LESS THEN 30");
                    message.setTextColor(Color.parseColor("#FFDD1111"));

                }else {
                    message.setText(String.format("LAUGH AT %s JOKES", text));
                    message.setTextColor(Color.parseColor("#83adb5"));
                fetchJokes(text,textView);}
            }
        });
        ImageButton backButton = findViewById(R.id.imageButton2);

        // Set OnClickListener for the ImageButton
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity when the back button is clicked
                finish();
            }
        });
    }

    private void fetchJokes(String limit, TextView textView) {
        String apiUrl = "https://api.api-ninjas.com/v1/jokes";
        String apikey = "neu2EnJKSuBnbemD9DZQbQ==TKOATko7FhSP4gFP";

        String requestURl = apiUrl + "?limit=" + limit;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, requestURl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                        Log.d(TAG,"Response: "+ response.toString());
                        showTost("API called Success");
                        JSONObject jsonObject = null;
                    if(jokeList.size() != 0){
                        jokeList.clear();
                    }
                for (int i = 0 ; i< response.length(); i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        String joke = jsonObject.getString("joke");


                        Joke jokes = new Joke(joke);
                        jokeList.add(jokes);


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    recyclerView.setAdapter(new JokeAdapter(getApplicationContext(),jokeList));

                    }
                }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(String.format("Error: %s", error.getMessage()));
                        Log.e(TAG, "Error: " + error.getMessage(), error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Api-Key", apikey);
                return headers;
            }
        };

        requestQueue.add(jsonArrayRequest);
    }


    private void showTost(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
