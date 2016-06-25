package grupo1.ciu.laberintoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import grupo1.ciu.laberintoapp.R;
import grupo1.ciu.laberintoapp.dominio.ElementoMin;
import grupo1.ciu.laberintoapp.dominio.LaberintoMin;

/**
 * Created by charlie on 18/06/16.
 */
public class InventarioAdapter extends ArrayAdapter<ElementoMin>{

    public InventarioAdapter(Context context, List<ElementoMin> inv) {
        super(context, R.layout.elemento_listado, inv);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getIdInterno();

    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        final ElementoMin  elementoMin = getItem(position);
        if(rowView==null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.elementoinventario_listado, parent, false);
        }
        //otra forma de manejar el inflate()
        // LayoutInflater inflater = (LayoutInflater) getContext()
        //      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       final TextView nombreElemento = (TextView) rowView.findViewById(R.id.nombreItem);
        nombreElemento.setText(elementoMin.getNombre());

        //TextView idlab = (TextView) rowView.findViewById(R.id.idLaberinto);
        //idlab.setText(laberinto.getIdInterno());

        // Codigo para insertar una imagen,ver como hacerlo si nos viene un string con la ruta
        //ImageView imagenLaberinto= rowView.findViewById(R.id.imageView);
        //imagenLaberinto.setImageDrawable(laberinto.getPathImage());

        return rowView;
    }
}
