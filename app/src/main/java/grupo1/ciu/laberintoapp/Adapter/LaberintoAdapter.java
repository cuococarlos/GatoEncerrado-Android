package grupo1.ciu.laberintoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import grupo1.ciu.laberintoapp.R;
import grupo1.ciu.laberintoapp.dominio.LaberintoMin;

/**
 * Created by charlie on 18/06/16.
 */
public class LaberintoAdapter extends ArrayAdapter<LaberintoMin> {


        public LaberintoAdapter(Context context, List<LaberintoMin> peliculas) {
            super(context, R.layout.elemento_listado, peliculas);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getIdInterno();

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.elemento_listado, parent, false);
            final LaberintoMin  laberinto = getItem(position);

            TextView nombreLaberinto = (TextView) rowView.findViewById(R.id.nombreLaberinto);
            nombreLaberinto.setText(laberinto.getNombreLaberinto());

            TextView idlab = (TextView) rowView.findViewById(R.id.idLaberinto);
            idlab.setText(laberinto.getIdInterno());

            /* Codigo para insertar una imagen,ver como hacerlo si nos viene un string con la ruta
            ImageView imagenLaberinto= rowView.findViewById(R.id.imageView_imagen)
            imagenLaberinto.setImageDrawable(laberinto.getPathImage());*/

            return rowView;
        }
    }

