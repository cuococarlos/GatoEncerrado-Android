package grupo1.ciu.laberintoapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import grupo1.ciu.laberintoapp.Adapter.LaberintoAdapter;
import grupo1.ciu.laberintoapp.dominio.LaberintoMin;
import grupo1.ciu.laberintoapp.laberinto.Servicios.LaberintosService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ListaLaberintosActivity extends AppCompatActivity {
    private LaberintosService laberintosService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_laberintos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Esta URL apunta al proyecto con ORM de Grails
        // 		val API_URL = "http://10.0.2.2:8080/videoclub-ui-orm-grails"
        // Esta URL apunta a la solución en Grails con Homes hechos en Xtend
        //String SERVER_IP = "10.0.2.2";

        // IMPORTANTE
        // Por un bug de retrofit 2.0, la BASE_URL debe tener una / al final
        // y la dirección del service debe comenzar sin /, como un path relativo
        String BASE_URL = "http://192.168.0.108:7000/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        laberintosService = retrofit.create(LaberintosService.class);
        Call<List<LaberintoMin>> LaberintoCall = laberintosService.getLaberintos();

    /*    // Construct the data source
        ArrayList<LaberintoMin> laberintos = LaberintoMin.fromJson();
        // Create the adapter to convert the array to views
        LaberintoAdapter adapter = new LaberintoAdapter(this, laberintos);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
*/
        LaberintoCall.enqueue(new Callback<List<LaberintoMin>>() {
            @Override
            public void onResponse(Response<List<LaberintoMin>> response, Retrofit retrofit) {
                List<LaberintoMin> laberintos = response.body();
                LaberintoAdapter adapter = new LaberintoAdapter(getBaseContext(),laberintos);
                ListView listView=(ListView) findViewById(R.id.listView);
                listView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("PeliculasApp ", t.getMessage());
            }
        });


    }
}