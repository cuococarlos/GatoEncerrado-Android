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

public class ListaLaberintosActivity extends AppCompatActivity {
    private LaberintosService laberintosService;
    private boolean pantallaTablet;
    LaberintoMin seleccionado;
    ListView listViewLaberintos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_laberintos);
       /* Todavia no utilizamos la toolbar para nada
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar)
        setSupportActionBar(toolbar);*/

        //String BASE_URL = "http://192.168.0.18:7000/";
        String BASE_URL = "http:192.168.122.1:7000/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        laberintosService = retrofit.create(LaberintosService.class);
        Call<List<LaberintoMin>> LaberintoCall = laberintosService.getLaberintos();
        //Call<JSONArray> LaberintoCall = laberintosService.getLaberintos();

        LaberintoCall.enqueue(new Callback<List<LaberintoMin>>() {
        //LaberintoCall.enqueue(new Callback<JSONArray>(){
            @Override
            public void onResponse(Response<List<LaberintoMin>> response, Retrofit retrofit) {
            //public void onResponse(Response<JSONArray> response, Retrofit retrofit) {
                List<LaberintoMin> laberintos = response.body();
                //JSONArray laberintos = response.body();
                //List<LaberintoMin> laberinto2=LaberintoMin.fromJson(laberintos);
                LaberintoAdapter adapter = new LaberintoAdapter(getBaseContext(), laberintos);
                listViewLaberintos = (ListView) findViewById(R.id.listView2);

                // para mostrar el text view seleccionado=(TextView)findViewById(R.id.seleccionado);
                listViewLaberintos.setAdapter(adapter);

                /*listViewLaberintos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> list, View view, int position, long id) {


                    }*/

                    ;


                };


            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("LaberintoApp", t.getMessage());
            }


        });


    };

    public void onItemSelected(LaberintoMin labMin){
        Log.i("anda el click?","Si anda");
        Intent inventarioActivity = new Intent(this, InventarioLaberintoActivity.class);
        inventarioActivity.putExtra("laberintoParametro",labMin);
        startActivity(inventarioActivity);

    }












    };

