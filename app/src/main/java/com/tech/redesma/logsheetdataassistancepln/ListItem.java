package com.tech.redesma.logsheetdataassistancepln;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListItem extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        listView = (ListView) findViewById(R.id.lv_items);

        listView.setOnItemClickListener(this);

        editTextSearchItem = (EditText) findViewById(R.id.et_search);

        getItems();

        getSupportActionBar().setTitle("Data List View");

    }


    private void getItems() {

        loading = ProgressDialog.show(this, "Loading", "please wait", false, true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbxKZXx9CoIBh9KNNyQ71R3I_xWEB28L7OG2Mr7U5ZU1Qw1NA1uZb-gS/exec?action=getItems",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseItems(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }

    private void parseItems(String jsonResposnce) {

        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try {
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("items");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);


                String pressure = jo.getString("pressure");
                String temperature = jo.getString("temperature");
                String humidity = jo.getString("humidity");
                String itemId = jo.getString("itemId");
                String date = jo.getString("date");


                HashMap<String, String> item = new HashMap<>();
                item.put("pressure", pressure);
                item.put("temperature", temperature);
                item.put("humidity", humidity);
                item.put("itemId", itemId);
                item.put("date", date);

                list.add(item);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter = new SimpleAdapter(this, list, R.layout.list_item_row,
                new String[]{"pressure", "temperature", "humidity", "itemId", "date"}, new int[]{R.id.tv_item_name, R.id.tv_brand, R.id.tv_price, R.id.tv_id, R.id.tv_date});


        listView.setAdapter(adapter);
        loading.dismiss();

        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                ListItem.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ItemDetails.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String date = map.get("date").toString();
        String itemId = map.get("itemId").toString();
        String pressure = map.get("pressure").toString();
        String temperature = map.get("temperature").toString();
        String humidity = map.get("humidity").toString();


        // String sno = map.get("sno").toString();

        // Log.e("SNO test",sno);

        intent.putExtra("date",date);
        intent.putExtra("itemId",itemId);
        intent.putExtra("pressure",pressure);
        intent.putExtra("temperature",temperature);
        intent.putExtra("humidity",humidity);


        startActivity(intent);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}