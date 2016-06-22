package grupo1.ciu.laberintoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import grupo1.ciu.laberintoapp.Adapter.LaberintoAdapter;
import grupo1.ciu.laberintoapp.dominio.ElementoMin;
import grupo1.ciu.laberintoapp.dominio.LaberintoMin;
import grupo1.ciu.laberintoapp.laberinto.Servicios.LaberintosService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ListaLaberintosActivity extends FragmentActivity {
    private LaberintosService laberintosService;
    private boolean pantallaTablet;
    LaberintoMin seleccionado;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_laberintos);
       /* Todavia no utilizamos la toolbar para nada
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar)
        setSupportActionBar(toolbar);*/

        String BASE_URL = "http://192.168.1.7:7000/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        laberintosService = retrofit.create(LaberintosService.class);
        Call<List<LaberintoMin>> LaberintoCall = laberintosService.getLaberintos();


        LaberintoCall.enqueue(new Callback<List<LaberintoMin>>() {
            @Override
            public void onResponse(Response<List<LaberintoMin>> response, Retrofit retrofit) {
                List<LaberintoMin> laberintos = response.body();
                LaberintoAdapter adapter = new LaberintoAdapter(getBaseContext(), laberintos);
                listView = (ListView) findViewById(R.id.listView);
                // para mostrar el text view seleccionado=(TextView)findViewById(R.id.seleccionado);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                        seleccionado = (LaberintoMin) pariente.getItemAtPosition(posicion);

                        Log.i("",seleccionado.getNombreLaberinto());

                    };


                });

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("PeliculasApp ", t.getMessage());
            }



        });
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                seleccionado = (LaberintoMin) pariente.getItemAtPosition(posicion);

                CharSequence texto = "Seleccionado: " + seleccionado.getNombreLaberinto();
                Toast toast = Toast.makeText(ListaLaberintosActivity.this, texto, Toast.LENGTH_LONG);
                toast.show();
            }*/
        };


        //Button entry = (Button) findViewById(R.id.button2);

        /*entry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent InventarioActivity = new Intent(getApplicationContext(), InventarioLaberintoActivity.class);
                startActivity(InventarioActivity);
            }
        });*/




    };

