package kolaric.parametri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListe.ItemClickListener {

    private AdapterListe adapter;
    private static final String REST_URL = "https://oziz.ffos.hr/nastava20192020/mkolaric_19/parametri";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterListe(this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        RESTTask ayncTask = new RESTTask();
        ayncTask.execute(REST_URL);
    }

    @Override
    public void onItemClick(View view, int position) {
        Parametar o = adapter.getItem(position);
        Intent i = new Intent(this, Detalji.class);
        i.putExtra("parametar", o);
        startActivity(i);
        //Toast.makeText(this, "Odabrali ste Parametar s šifrom " + o.getSifra(), Toast.LENGTH_LONG).show();
    }


    //Zadatak
    //implementirati različite animacije između dviju aktivnosti
    //https://stackoverflow.com/questions/10243557/how-to-apply-slide-animation-between-two-activities-in-android


    // kao inner klasa, čitati kako napraviti u odvojenoj datoteci: https://stackoverflow.com/questions/9963691/android-asynctask-sending-callbacks-to-ui
    private class RESTTask extends AsyncTask<String, Void, List<Parametar>> {
        protected List<Parametar> doInBackground(String... adresa) {
            String stringUrl = adresa[0];
            List<Parametar> vrati=null;
            try {
                URL myUrl = new URL(stringUrl);
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);
                connection.connect();
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                Type listType = new TypeToken<ArrayList<Parametar>>(){}.getType();
                vrati = new Gson().fromJson(reader, listType);
                reader.close();
                streamReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return vrati;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(List<Parametar> podaci) {
            adapter.setPodaci(podaci);
            adapter.notifyDataSetChanged();
        }
    }

}
