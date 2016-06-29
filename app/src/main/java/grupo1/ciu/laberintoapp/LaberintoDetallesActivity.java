package grupo1.ciu.laberintoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import grupo1.ciu.laberintoapp.Adapter.InventarioAdapter;
import grupo1.ciu.laberintoapp.dominio.ElementoMin;
import grupo1.ciu.laberintoapp.dominio.LaberintoMin;
import grupo1.ciu.laberintoapp.laberinto.Servicios.LaberintosService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LaberintoDetallesActivity extends AppCompatActivity {
    LaberintosService laberintoService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalleslaberinto);
        String BASE_URL = "http://10.9.0.179:7000/";
        Long idLab = getIntent().getExtras().getLong("idLaberinto",1);
        //Long idLong = getIntent().getLongExtra("idLong", 22);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        laberintoService = retrofit.create(LaberintosService.class);
        Log.i("Cual me llega del inten",Long.toString(idLab));
        Call<LaberintoMin> laberintoCall = laberintoService.getLaberinto(idLab.toString());
        Log.v("","");
        laberintoCall.enqueue(new Callback<LaberintoMin>(){
            @Override
            public void onResponse(Response<LaberintoMin> response, Retrofit retrofit) {
               final LaberintoMin lab = response.body();
                //InventarioAdapter adapter = new InventarioAdapter(getBaseContext(),inventario);
                //inventarioList=(ListView)findViewById(R.id.inventarioView);
                //inventarioList.setAdapter(adapter);

                Log.e("pase","por aca");
                final TextView nombreLaberinto = (TextView) findViewById(R.id.detallesnombrelab);
                nombreLaberinto.setText(lab.getDescripcion());


            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("LabApp ", t.getMessage());
            }
            });
        }
}
