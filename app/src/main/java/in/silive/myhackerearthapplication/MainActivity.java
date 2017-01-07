package in.silive.myhackerearthapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String> Kings_name = new ArrayList<>();
    public ArrayList<Integer> Kings_img = new ArrayList<>();
    public ArrayList<String> Kings_battle = new ArrayList<>();
    public ArrayList<String> Kings_rating = new ArrayList<>();
    public String jsonStr;
    //EditText search_king;
    ListView list_of_kings;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = getApplicationContext();
        //search_king = (EditText) findViewById(R.id.search_king);
        list_of_kings = (ListView) findViewById(R.id.list_of_kings);

        new KingRating(this).execute();
    }

    public class KingRating extends AsyncTask<Void, Void, String> {
        public Context context;
        public URL H_url;
        public HttpURLConnection H_connection;
        public BufferedReader H_bufferedReader;
        public StringBuilder H_response;
        public ProgressDialog progressDialog;

        public KingRating(Context c) {
            context = c;
            this.progressDialog = new ProgressDialog(c);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                H_url = new URL("http://starlord.hackerearth.com/gotjson");
                H_connection = (HttpURLConnection) H_url.openConnection();
                Log.d("TAG", "url : " + H_url);
                Log.d("TAG", "connection");
                H_connection.setRequestMethod("GET");
                H_connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                Log.d("TAG", "connection req property get");
                H_connection.connect();
                Log.d("TAG", "connection estb");
                H_bufferedReader = new BufferedReader(new InputStreamReader(H_connection.getInputStream()));
                Log.d("TAG", "buff readr");
                HttpHandler sh = new HttpHandler();
                 jsonStr = sh.makeServiceCall("http://starlord.hackerearth.com/gotjson");
            } catch (Exception e) {
                Log.d("TAG", "NO connection");
                e.printStackTrace();
            }
                return jsonStr;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Fetching Data ");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s)

        {
            // super.onPostExecute(s);

            parsing_Kings(s);
            //notify data set changed;
            progressDialog.dismiss();
            ListAdapterKing listAdapterKing = new ListAdapterKing(c, Kings_img, Kings_name, Kings_battle, Kings_rating);
            list_of_kings.setAdapter(listAdapterKing);


        }

        public void parsing_Kings(String s) {
            try {
                Log.d("TAG", "try parsing");
                JSONArray List_of_kings = new JSONArray(jsonStr);
                //Log.d("TAG", "JSON obj created");
                /*JSONArray List_of_kings = King_object.getJSONArray();*/
                Log.d("TAG", "JSON array fetched");
                for (int i = 0; i < List_of_kings.length(); i++) {
                    JSONObject k_list = List_of_kings.getJSONObject(i);
                    Kings_name.add(k_list.getString("attacker_king"));
                    Kings_battle.add(k_list.getString("battle_type"));
                    Kings_rating.add("400");
                    if (i % 2 == 0) {
                        Kings_img.add(R.mipmap.ic_launcher);
                    } else if (i % 2 != 0 && i % 5 == 0) {
                        Kings_img.add(R.mipmap.ic_launcher);
                    } else {
                        Kings_img.add(R.mipmap.ic_launcher);
                    }
                    Log.d("TAG", "Item added");

                }
            } catch (Exception e) {
                Log.d("TAG", "Printing stack trace : "+"\n\n");
                e.printStackTrace();
                Log.d("TAG", "Parsing not working");
            }

        }
    }
}
