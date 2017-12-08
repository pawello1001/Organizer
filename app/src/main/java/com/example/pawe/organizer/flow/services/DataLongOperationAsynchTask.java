package com.example.pawe.organizer.flow.services;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.models.Address;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DataLongOperationAsynchTask extends AsyncTask<String, Void, String> {

    private String address;
    private String name;
    private Activity mContext;

    public DataLongOperationAsynchTask(Activity context, String address, String name) {
        this.mContext = context;
        this.address = address;
        this.name = name;
        Log.d("address", address);
    }

    @Override
    protected String doInBackground(String... strings) {
        String response;
        try {
            URI uri = new URI("https://maps.google.com/maps/api/geocode/json?address=", address, "&key=AIzaSyBVA-JvoluTi90wi5Jl5G_qih_CIrYcydU");
            URL url = uri.toURL();
            Log.d("uri", String.valueOf(uri));
            Log.d("url", String.valueOf(url));
            response = getLatLongByURL(url);
            Log.d("response",""+response);
            return new String(response);
        } catch (Exception e) {
            return new String("error");
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d("result", result);
        try {
            JSONObject jsonObject = new JSONObject(result);

            double lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lng");

            double lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lat");

            String address = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                    .getString("formatted_address");

            Log.d("address", address);
            Log.d("latitude", "" + lat);
            Log.d("longitude", "" + lng);
            Address addr = new Address(name, address, lat, lng);
            addr.save();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(mContext, R.string.address_error, Toast.LENGTH_SHORT).show();
        }
    }


    public String getLatLongByURL(URL url) {
        String response = "";
        try {

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
