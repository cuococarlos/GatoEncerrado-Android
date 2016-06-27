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
import android.widget.Toolbar;

import java.util.List;

import grupo1.ciu.laberintoapp.Adapter.LaberintoAdapter;
import grupo1.ciu.laberintoapp.dominio.LaberintoMin;
import grupo1.ciu.laberintoapp.laberinto.Servicios.LaberintosService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ListaLaberintosActivity extends AppCompatActivity implements ListaLaberintosFragment.Callbacks {
    private LaberintosService laberintosService;
    private boolean pantallaTablet;
    LaberintoMin seleccionado;
    ListView listViewLaberintos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_laberintos);

    }
    @Override
    public void onItemSelected(LaberintoMin laberintoMin){
        Intent inventarioActivity = new Intent(this, InventarioLaberintoActivity.class);
        inventarioActivity.putExtra("laberintoParametro",laberintoMin);
        startActivity(inventarioActivity);

    }



        /*void cambiarInventario(){
        Intent InventarioActivity = new Intent(getApplicationContext(), InventarioLaberintoActivity.class);
        startActivity(InventarioActivity);

            };*/


}

