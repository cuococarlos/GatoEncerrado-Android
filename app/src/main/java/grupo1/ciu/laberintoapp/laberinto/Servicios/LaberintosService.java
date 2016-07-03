package grupo1.ciu.laberintoapp.laberinto.Servicios;

import org.json.JSONArray;

import java.util.List;

import grupo1.ciu.laberintoapp.dominio.ElementoMin;
import grupo1.ciu.laberintoapp.dominio.LaberintoMin;
import retrofit.Call;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by charlie on 18/06/16.
 */
public interface LaberintosService {
    @GET("laberintos")
    public Call<List<LaberintoMin>>getLaberintos();

    @GET("inventario/{idlab}/1")
    public Call<List<ElementoMin>>getInventario(@Path("idlab")String idlab);

    @GET("laberinto/1/{idlab}")
    public Call<LaberintoMin>getLaberinto(@Path("idlab")String idlab);

    @GET("descartar/1/{iditem}")
    public Call<String>getInventarioNuevo(@Path("iditem")String iditem);

}
