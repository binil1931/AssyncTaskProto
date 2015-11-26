package com.example.binil.assynctaskproto;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends ActionBarActivity {


    private ListView lv;
    ArrayList<HashMap<String, String>> productList;
    ArrayList<ItemListPogo> arraylist = new ArrayList<ItemListPogo>();
    ListViewAdapter adapter;
    JSONArray contacts = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.list_view);



                AsyncHttpClient client = new AsyncHttpClient();
                client.get("http://api.androidhive.info/contacts/", new AsyncHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.e("Tag ","onSuccess " + responseBody );
                        try {
                            String jsonStr = new String(responseBody, "UTF-8");
                            Log.e("Tag ","on Result " + jsonStr );

                            Log.d("Response: ", "> " + jsonStr);

                            if (jsonStr != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject(jsonStr);

                                    // Getting JSON Array node
                                    contacts = jsonObj.getJSONArray("contacts");

                                    // looping through All Contacts
                                    for (int i = 0; i < contacts.length(); i++) {
                                        JSONObject c = contacts.getJSONObject(i);

                                        String id = c.getString("id");
                                        String name = c.getString("name");
                                        String email = c.getString("email");
                                        String address = c.getString("address");
                                        String gender = c.getString("gender");

                                        // Phone node is JSON Object
                                        JSONObject phone = c.getJSONObject("phone");
                                        String mobile = phone.getString("mobile");
                                        String home = phone.getString("home");
                                        String office = phone.getString("office");

                                        ItemListPogo wp = new ItemListPogo(email, name );
                                        arraylist.add(wp);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Log.e("Tag", "Couldn't get any data from the url");
                            }
                            adapter = new ListViewAdapter(MainActivity.this, arraylist);
                            lv.setAdapter(adapter);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        try {
                            String str = new String(responseBody, "UTF-8");
                            Log.e("Tag ","onFailure " +str);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }
                });





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
