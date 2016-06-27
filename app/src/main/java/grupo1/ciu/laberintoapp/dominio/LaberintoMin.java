package grupo1.ciu.laberintoapp.dominio;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by charlie on 18/06/16.
 */
public class LaberintoMin {

    String nombreLaberinto;
    String pathImage;
    Long idInterno;
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

    public Long getIdInterno() {
        return idInterno;
    }

    public String getDescripcion(){return descripcion;}

        public LaberintoMin (JSONObject lab) {
            try {
                this.id = lab.getString("idString");
                this.descripcion= lab.getString("descripcion");
                this.nombreLaberinto = lab.getString("nombreLaberinto");
                this.pathImage = lab.getString("pathImage");
                this.idInterno= lab.getLong("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    public static ArrayList<LaberintoMin> fromJson ( JSONArray  jsonObjects) {
        ArrayList<LaberintoMin> laberintosM = new ArrayList<LaberintoMin>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                    laberintosM.add(new LaberintoMin(jsonObjects.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return laberintosM;
        }
    }


