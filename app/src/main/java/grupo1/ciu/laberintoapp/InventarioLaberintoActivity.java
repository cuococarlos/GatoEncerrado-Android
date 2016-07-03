package grupo1.ciu.laberintoapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

public class InventarioLaberintoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private LaberintosService inventarioService;
    ListView inventarioList;
    Long idSelec = Long.valueOf(0);
    String BASE_URL = "http://192.168.0.18:7000/";
    Long idLong=Long.valueOf(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_laberinto);
        inventarioList=(ListView)findViewById(R.id.inventarioView);
        inventarioList.setOnItemClickListener(this);
        idLong = getIntent().getLongExtra("id", 22);
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

                inventarioList.setAdapter(adapter);

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("LabApp ", t.getMessage());
            }
        });

    }

    public void refrescarInventario(View v) {

        inventarioService.getInventarioNuevo(Long.toString(idSelec));
        finish();
        startActivity(getIntent());

        //final Button quitarItem = (Button) findViewById(R.id.botonQuitar);
        //quitarItem.setVisibility(View.INVISIBLE);

        /* Intent inventarioActivity = new Intent(this, InventarioLaberintoActivity.class);
        inventarioActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        inventarioActivity.putExtra("id", idLong);
        startActivity(inventarioActivity);*/


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //parent.setBackgroundColor(getColor(R.color.colorAccent));
       //final int color = getColor(R.color.colorAccent);
        final Button quitarItem = (Button) findViewById(R.id.botonQuitar);
        quitarItem.setVisibility(View.VISIBLE);
        boolean changeColor = true;
        if(idSelec== id){idSelec = Long.valueOf(0);}
        else {idSelec = id;}

       //if( color == getColor(R.color.colorAccent) ) {
       if( changeColor ) {
               view.setBackgroundColor(getColor(R.color.colorPrimary));
             //  changeColor = !changeColor;
       }
       else{
           view.setBackgroundColor(getColor(R.color.colorAccent));
           changeColor = !changeColor;
           }

    }



}


