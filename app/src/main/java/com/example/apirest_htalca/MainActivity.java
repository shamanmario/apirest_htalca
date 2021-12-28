package com.example.apirest_htalca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private Adaptador adaptador;
    private List<Publicacion> posteos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //posteos = null;
        recuperarPublicaciones();
/*
        if(posteos!=null){
            recycler = findViewById(R.id.recycler);
            adaptador = new Adaptador(posteos);
            recycler.setLayoutManager(new LinearLayoutManager(this));
            recycler.setAdapter(adaptador);
        }*/
    }

    private void recuperarPublicaciones(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Publicacion>> call = jsonPlaceHolderApi.getPublicaciones();

        call.enqueue(new Callback<List<Publicacion>>() {
            @Override
            public void onResponse(Call<List<Publicacion>> call, Response<List<Publicacion>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Código error: "+response.code(), Toast.LENGTH_LONG).show();
                }

                posteos = response.body();

                recycler = findViewById(R.id.recycler);
                adaptador = new Adaptador(posteos);
                recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recycler.setAdapter(adaptador);

                /*
                for(Publicacion p : posteos){
                    String mensaje = "";

                    mensaje+="id mensaje: "+p.getId()+"\n";
                    mensaje+="titulo mensaje: "+p.getTitle()+"\n";
                    mensaje+="cuerpo mensaje: "+p.getBody()+"\n\n";

                    System.out.println(mensaje);
                }*/
            }

            @Override
            public void onFailure(Call<List<Publicacion>> call, Throwable throwable) {
                Log.d("errorConexion", throwable.getMessage());
            }
        });
    }


}