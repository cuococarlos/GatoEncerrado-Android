package grupo1.ciu.laberintoapp;

import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import grupo1.ciu.laberintoapp.Adapter.LaberintoAdapter;
import grupo1.ciu.laberintoapp.dominio.LaberintoMin;
import grupo1.ciu.laberintoapp.laberinto.Servicios.LaberintosService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ListaLaberintosFragment extends ListFragment implements View.OnClickListener {
    //Agregadas copiadas del ejemplo
    public interface Callbacks {
        void onItemSelected(LaberintoMin laberintoMin);
    }
    @Override
    public void onClick(View v) {

    }
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(LaberintoMin laberintoMin) {
        }
    };
    private Callbacks mCallbacks = sDummyCallbacks;

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_laberintos, null, false);
    }*/

    public ListaLaberintosFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LaberintosService laberintosService;
        //String BASE_URL = "http://192.168.0.16:7000/";
        //String BASE_URL = "http://192.168.1.7:7000/";
        String BASE_URL= "http://192.168.122.1:7000/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        laberintosService = retrofit.create(LaberintosService.class);
        Call<List<LaberintoMin>> LaberintoCall = laberintosService.getLaberintos();

        LaberintoCall.enqueue(new Callback<List<LaberintoMin>>() {
            @Override
            public void onResponse(Response<List<LaberintoMin>> response, Retrofit retrofit) {
                List<LaberintoMin> laberintos = response.body();
                setListAdapter(new LaberintoAdapter(getActivity(),laberintos));




            };


            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("LaberintoApp", t.getMessage());
            }


        });


    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }


        mCallbacks = (Callbacks) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

       public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        LaberintoMin labMin = (LaberintoMin) listView.getAdapter().getItem(position);
        mCallbacks.onItemSelected(labMin);


    }
}
