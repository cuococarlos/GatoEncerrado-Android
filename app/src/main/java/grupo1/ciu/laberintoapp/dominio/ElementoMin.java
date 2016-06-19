package grupo1.ciu.laberintoapp.dominio;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by charlie on 18/06/16.
 */
public class ElementoMin {
    String nombre;
    String des;
    int idInterno;

    public String getNombre() {
        return nombre;
    }

    public String getDes() {
        return des;
    }

    public int getIdInterno() {
        return idInterno;
    }

    public ElementoMin (JSONObject ele) {
        try {
            this.nombre= ele.getString("nombre");
            this.des = ele.getString("des");
            this.idInterno= ele.getInt("Id");
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }



}
