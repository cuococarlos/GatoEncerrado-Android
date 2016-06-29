package grupo1.ciu.laberintoapp;

import android.content.Intent;
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
    ListView inventarioList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_laberinto);
        String BASE_URL = "http://192.168.122.1:7000/";

        Long idLong = getIntent().getLongExtra("id", 22);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        inventarioService = retrofit.create(LaberintosService.class);
        Call<List<ElementoMin>> InventarioCall = inventarioService.getInventario(Long.toString(idLong));
        InventarioCall.enqueue(new Callback<List<ElementoMin>>() {
            @Override
            public void onResponse(Response<List<ElementoMin>> response, Retrofit retrofit) {
                List<ElementoMin> inventario = response.body();
                InventarioAdapter adapter = new InventarioAdapter(getBaseContext(),inventario);
                inventarioList=(ListView)findViewById(R.id.inventarioView);
                inventarioList.setAdapter(adapter);

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("LabApp ", t.getMessage());
            }
        });

    }

    public void onItemSelected(LaberintoMin labMin){
        Log.i("anda el click?","Si anda");
        Intent inventarioActivity = new Intent(this, InventarioLaberintoActivity.class);
        inventarioActivity.putExtra("laberintoParametro",labMin);
        startActivity(inventarioActivity);

    }

}
