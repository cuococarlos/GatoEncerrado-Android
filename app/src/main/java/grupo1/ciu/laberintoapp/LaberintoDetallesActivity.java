package grupo1.ciu.laberintoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
    private LaberintosService laberintoService;
    Long idLab=Long.valueOf(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalleslaberinto);
        String BASE_URL = "http://192.168.1.7:7000/";
         idLab = getIntent().getExtras().getLong("idLaberinto", 1);
        //Long idLong = getIntent().getLongExtra("idLong", 22);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        laberintoService = retrofit.create(LaberintosService.class);
        Log.i("Cual me llega del inten", Long.toString(idLab));
        Call<LaberintoMin> laberintoCall = laberintoService.getLaberinto(Long.toString(idLab));
        laberintoCall.enqueue(new Callback<LaberintoMin>() {
            @Override
            public void onResponse(Response<LaberintoMin> response, Retrofit retrofit) {
                //final LaberintoMin  laberinto
                 LaberintoMin lab = response.body();
                //InventarioAdapter adapter = new InventarioAdapter(getBaseContext(),inventario);
                //inventarioList=(ListView)findViewById(R.id.inventarioView);
                //inventarioList.setAdapter(adapter);

                Log.e("pase", "por aca");
                final TextView nombreLaberinto = (TextView) findViewById(R.id.detallesnombrelab);
                nombreLaberinto.setText(lab.getNombreLaberinto());

                final TextView descripcionLaberinto = (TextView) findViewById(R.id.detallesnombrelab);
                descripcionLaberinto.setText(lab.getDescripcion());

                String URL_PHOTOS = "http://imagizer.imageshack.us/a/";
                ImageView imageView = (ImageView) findViewById(R.id.detallesimagen);
                Picasso.with(getBaseContext())
                        .load(URL_PHOTOS + lab.getPathImage())
                        .resize(500, 500)
                        .centerInside()
                        .into(imageView);

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("LabApp ", t.getMessage());
            }
        });




            ;

    }
   public void irAInventario(View v){


       Intent inventarioActivity = new Intent(this, InventarioLaberintoActivity.class);
       inventarioActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       inventarioActivity.putExtra("id", idLab);
       startActivity(inventarioActivity);
   }
   }

