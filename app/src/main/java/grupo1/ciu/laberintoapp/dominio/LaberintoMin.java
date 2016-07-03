package grupo1.ciu.laberintoapp.dominio;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by charlie on 18/06/16.
 */
public class LaberintoMin implements  Serializable {

    String nombreLaberinto;
    String pathImage;
    String descripcion;
    String id;



    public String getNombreLaberinto() {
        return nombreLaberinto;
    }

    public String getId() {
        return id;
    }

    public String getPathImage() {
        return pathImage;
    }


    public String getDescripcion() {
        return descripcion;
    }

}



