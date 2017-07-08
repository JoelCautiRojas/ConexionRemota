package com.example.luis.conexionremota;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {
    TextView texto1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto1 = (TextView) findViewById(R.id.textView16);
        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.post("http://www.aktechnologysolutions.pe.hu", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200)
                {
                    String cadena = new String(responseBody);
                    try {
                        JSONArray matriz = new JSONArray(cadena);
                        texto1.setText(matriz.getJSONObject(2).getString("nombre"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
