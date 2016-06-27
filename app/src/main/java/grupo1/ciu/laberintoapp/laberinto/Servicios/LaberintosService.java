package grupo1.ciu.laberintoapp.laberinto.Servicios;

import org.json.JSONArray;

import java.util.List;

import grupo1.ciu.laberintoapp.dominio.ElementoMin;
import grupo1.ciu.laberintoapp.dominio.LaberintoMin;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by charlie on 18/06/16.
 */
public interface LaberintosService {
    @GET("laberintos")
    public Call<List<LaberintoMin>>getLaberintos();
    //public Call<JSONArray>getLaberintos();
    @GET("inventario/{idlab}/1")
    public Call<List<ElementoMin>>getInventario(@Path("idlab")String idlab);

}
