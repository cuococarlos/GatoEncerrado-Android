package grupo1.ciu.laberintoapp.dominio;

/**
 * Created by charlie on 18/06/16.
 */
public class LaberintoMin {

        String nombreLaberinto;

    public String getNombreLaberinto() {
        return nombreLaberinto;
    }

    public String getPathImage() {
        return pathImage;
    }

    public int getIdInterno() {
        return idInterno;
    }

    String pathImage;
        int idInterno;


        public LaberintoMin (String nombre, String path , int id){
            this.nombreLaberinto = nombre;
            this.pathImage = path;
            this.idInterno = id;
        }

}
