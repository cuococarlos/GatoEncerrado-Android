package grupo1.ciu.laberintoapp.dominio;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by charlie on 18/06/16.
 */
public class ElementoMin {
    String nombre;
    String descripcion;
    String id;

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getId() {
        return id;
    }

  /* public ElementoMin (JSONObject ele) {
        try {
            this.id= ele.getString("id");
            this.nombre= ele.getString("nombre");
            this.descripcion = ele.getString("descripcion");
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


*/
}
