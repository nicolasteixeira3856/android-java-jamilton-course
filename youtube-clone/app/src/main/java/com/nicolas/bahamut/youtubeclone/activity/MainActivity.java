package com.nicolas.bahamut.youtubeclone.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.nicolas.bahamut.youtubeclone.R;
import com.nicolas.bahamut.youtubeclone.adapter.AdapterVideo;
import com.nicolas.bahamut.youtubeclone.api.YoutubeService;
import com.nicolas.bahamut.youtubeclone.helper.RetrofitConfig;
import com.nicolas.bahamut.youtubeclone.helper.YoutubeConfig;
import com.nicolas.bahamut.youtubeclone.listener.RecyclerItemClickListener;
import com.nicolas.bahamut.youtubeclone.model.Item;
import com.nicolas.bahamut.youtubeclone.model.Resultado;
import com.nicolas.bahamut.youtubeclone.model.Video;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;
    private static final String GOOGLE_API_KEY = "AIzaSyBGyc7yd274NsU475VoypedshkfFmpvQac";
    //private YouTubePlayer.PlaybackEventListener playbackEventListener;
    //private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener;

    private RecyclerView recyclerVideos;

    private List<Item> videos = new ArrayList<>();
    private Resultado resultado;
    private AdapterVideo adapterVideo;

    private MaterialSearchView searchView;

    //Retrofit
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar componentes
        recyclerVideos = findViewById(R.id.recyclerVideos);
        //searchView = findViewById(R.id.search_view);

        //Configurações iniciais
        retrofit = RetrofitConfig.getRetrofit();

        //Configura a toolbar
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle("YouTube Clone");

        //Recupera videos
        recuperarVideos("");

        /*searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                recuperarVideos("");
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recuperarVideos(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });*/
    }

    private void recuperarVideos (String pesquisa) {
        String q = pesquisa.replaceAll( " ", "+");
        YoutubeService youtubeService = retrofit.create(YoutubeService.class);
        youtubeService.recuperarVideos(
            "snippet", "date", "20", YoutubeConfig.CHAVE_YOUTUBE_API, YoutubeConfig.CANAL_ID, q
        ).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    resultado = response.body();
                    videos = resultado.items;
                    configurarRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });
    }

    public void configurarRecyclerView() {
        adapterVideo = new AdapterVideo(videos, this);
        recyclerVideos.setHasFixedSize(true);
        recyclerVideos.setLayoutManager(new LinearLayoutManager(this));
        recyclerVideos.setAdapter(adapterVideo);

        //Configura o evento de clique
        recyclerVideos.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                recyclerVideos,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Item video = videos.get(position);
                        String idVideo = video.id.videoId;

                        Intent i = new Intent(MainActivity.this, PlayerActivity.class);
                        i.putExtra("idVideo", idVideo);
                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem( item );
        return true;
    }*/
}

//@Override
//public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean foiRestaurado) {
//if (!foiRestaurado) {
////youTubePlayer.cueVideo("h3WlrW8zWUU");
//youTubePlayer.cuePlaylist("PLY8IGXWe0dLA_xS3OKI0z_uHikZs-Hi8A");
//}
//}
//
//@Override
//public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//Toast.makeText(this, "Erro ao inicializar o player! " + youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
//}
//
//        playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
//            @Override
//            public void onPlaying() {
//
//            }
//
//            @Override
//            public void onPaused() {
//                Toast.makeText(MainActivity.this, "Pausou", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onStopped() {
//
//            }
//
//            @Override
//            public void onBuffering(boolean b) {
//
//            }
//
//            @Override
//            public void onSeekTo(int i) {
//
//            }
//        };
//
//        playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
//            @Override
//            public void onLoading() {
//
//            }
//
//            @Override
//            public void onLoaded(String s) {
//
//            }
//
//            @Override
//            public void onAdStarted() {
//
//            }
//
//            @Override
//            public void onVideoStarted() {
//                Toast.makeText(MainActivity.this, "Iniciou", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onVideoEnded() {
//
//            }
//
//            @Override
//            public void onError(YouTubePlayer.ErrorReason errorReason) {
//
//            }
//        };
//
//        youTubePlayerView = findViewById(R.id.viewYoutubePlayer);
//        youTubePlayerView.initialize(GOOGLE_API_KEY, this);