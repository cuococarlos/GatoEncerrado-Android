package grupo1.ciu.laberintoapp.dominio;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by charlie on 18/06/16.
 */
public class LaberintoMin implements  Parcelable {

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
    // Burocracia de Parceable
    public LaberintoMin(Parcel in){
        readFromParcel(in);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Parcelable.Creator<LaberintoMin> CREATOR
            = new Parcelable.Creator<LaberintoMin>() {
        public LaberintoMin createFromParcel(Parcel in) {
            return new LaberintoMin(in);
        }

        public LaberintoMin[] newArray(int size) {
            return new LaberintoMin[size];
        }
    };


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombreLaberinto);
        dest.writeString(this.pathImage);
        dest.writeLong(this.idInterno);
        dest.writeString(this.descripcion);
        dest.writeString(this.id);
    }
    private void readFromParcel(Parcel in){
        nombreLaberinto=in.readString();
        pathImage=in.readString();
        idInterno=in.readLong();
        descripcion=in.readString();
        id=in.readString();
    }
    // Fin de Burocracia de Parceable
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


