package grupo1.ciu.laberintoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.List;

import grupo1.ciu.laberintoapp.Adapter.LaberintoAdapter;
import grupo1.ciu.laberintoapp.dominio.LaberintoMin;
import grupo1.ciu.laberintoapp.laberinto.Servicios.LaberintosService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ListaLaberintosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private LaberintosService laberintosService;
    ListView listViewLaberintos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_laberintos);

        listViewLaberintos = (ListView) findViewById(R.id.listView2);
        listViewLaberintos.setOnItemClickListener(this);

        //String BASE_URL = "http://192.168.0.18:7000/";
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

                listViewLaberintos.setAdapter(adapter);
            };


            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("LaberintoApp", t.getMessage());
            }


        });


    };




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Numero de id",Long.toString(id));
        Intent laberintoDetallesActivity = new Intent(this, LaberintoDetallesActivity.class);
        laberintoDetallesActivity.putExtra("idLaberinto", id);
        startActivity(laberintoDetallesActivity);
    }
};

