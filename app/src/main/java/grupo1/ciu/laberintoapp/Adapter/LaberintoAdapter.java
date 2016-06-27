package grupo1.ciu.laberintoapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import grupo1.ciu.laberintoapp.InventarioLaberintoActivity;
import grupo1.ciu.laberintoapp.R;
import grupo1.ciu.laberintoapp.dominio.LaberintoMin;

/**
 * Created by charlie on 18/06/16.
 */
public class LaberintoAdapter extends ArrayAdapter<LaberintoMin> {

        public String idDelLaberinto;
public Long idlong;

        public LaberintoAdapter(Context context, List<LaberintoMin> laberintos) {
            super(context, R.layout.elemento_listado, laberintos);
        }

        @Override
        public long getItemId(int position) {
            //return Long.parseLong(getItem(position).getIdInterno());
            return getItem(position).getIdInterno();
        }

        @Override
        public View getView(final int position, View rowView, ViewGroup parent) {
            final LaberintoMin  laberinto = getItem(position);
            if(rowView==null) {
                 rowView = LayoutInflater.from(getContext()).inflate(R.layout.elemento_listado, parent, false);
            }
            //otra forma de manejar el inflate()
           // LayoutInflater inflater = (LayoutInflater) getContext()
              //      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            idDelLaberinto=laberinto.getId();
            idlong = laberinto.getIdInterno();

            final TextView nombreLaberinto = (TextView) rowView.findViewById(R.id.nombreLaberinto);
            nombreLaberinto.setText(laberinto.getNombreLaberinto());
            /*final TextView idLaberinto= (TextView) rowView.findViewById(R.id.idLaberinto);
            idLaberinto.setText(Integer.toString(laberinto.getIdInterno()));*/

            final TextView descripcionLaberinto = (TextView) rowView.findViewById(R.id.descripcionLab);
            descripcionLaberinto.setText(laberinto.getDescripcion());

            String URL_PHOTOS= "http://imagizer.imageshack.us/a/";
            ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
            Picasso.with(getContext())
                    .load(URL_PHOTOS + laberinto.getPathImage())
                    .into(imageView);

            // Codigo para insertar una imagen,ver como hacerlo si nos viene un string con la ruta
            //ImageView imagenLaberinto= rowView.findViewById(R.id.imageView);
            //imagenLaberinto.setImageDrawable(laberinto.getPathImage());


            final Button inventario= (Button)rowView.findViewById(R.id.buttonListado);
            inventario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("c","pase por aca");
                    
                    Intent inventarioActivity = new Intent(getContext(), InventarioLaberintoActivity.class);
                    inventarioActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   // Log.i("Que dato paso",(idDelLaberinto));
                    inventarioActivity.putExtra("id",idDelLaberinto);
                    inventarioActivity.putExtra("idlong", idlong);
                    getContext().startActivity(inventarioActivity);

                }
            });

            return rowView;
        }




}

