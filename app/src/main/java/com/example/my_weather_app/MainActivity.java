package com.example.my_weather_app;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.text.DecimalFormat;

public class MainActivity<StringRequest> extends AppCompatActivity {
    EditText etCity, etCountry;
    TextView tvResult;
    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "6b2ad537e924d3e223aa239491199f21";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);
        tvResult = findViewById(R.id.tvResult);

    }

    public void getWeatherDetail(View view) {
        String tempurl = "";
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();
        if (city.equals("")) {
            tvResult.setText("city field cannot be empty");
        } else {
            if (!country.equals("")) {
                tempurl = url + "?q=" + city + "," + country + "&appid=" + appid;
            } else {
                tempurl = url + "?q=" + city + "&appid=" + appid;
            }
           StringRequest = new StringRequest(Request.Method.POST,tempurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("response",response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();
                }
            }){

            };
        }
    }
}