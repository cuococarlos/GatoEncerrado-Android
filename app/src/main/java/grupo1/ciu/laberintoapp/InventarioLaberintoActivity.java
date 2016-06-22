package grupo1.ciu.laberintoapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import grupo1.ciu.laberintoapp.Adapter.InventarioAdapter;
import grupo1.ciu.laberintoapp.Adapter.LaberintoAdapter;
import grupo1.ciu.laberintoapp.dominio.ElementoMin;
import grupo1.ciu.laberintoapp.dominio.LaberintoMin;
import grupo1.ciu.laberintoapp.laberinto.Servicios.LaberintosService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class InventarioLaberintoActivity extends AppCompatActivity {
    private LaberintosService inventarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_laberinto);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        String BASE_URL = "http://192.168.1.7:7000/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        inventarioService = retrofit.create(LaberintosService.class);

    }

        private void buscarInventario(int idLaberinto){
        String idLab=  Integer.toString(idLaberinto);
        Call<List<ElementoMin>> InventarioCall = inventarioService.getInventario(idLab);
        InventarioCall.enqueue(new Callback<List<ElementoMin>>() {
            @Override
            public void onResponse(Response<List<ElementoMin>> response, Retrofit retrofit) {
                List<ElementoMin> inventario = response.body();
                InventarioAdapter adapter = new InventarioAdapter(getBaseContext(),inventario);


            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("PeliculasApp ", t.getMessage());
            }
        });

    }



    /*    // Construct the data source
        ArrayList<LaberintoMin> laberintos = LaberintoMin.fromJson();
        // Create the adapter to convert the array to views
        LaberintoAdapter adapter = new LaberintoAdapter(this, laberintos);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
*/
        /*InventarioCall.enqueue(new Callback<List<LaberintoMin>>() {
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





    }*/
    }
