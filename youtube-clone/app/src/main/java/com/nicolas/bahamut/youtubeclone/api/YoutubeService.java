package com.nicolas.bahamut.youtubeclone.api;

import com.nicolas.bahamut.youtubeclone.model.Resultado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YoutubeService {
    @GET("search")
    Call<Resultado> recuperarVideos(@Query("part") String part,
                                    @Query("order") String order,
                                    @Query("maxResults") String maxResults,
                                    @Query("key") String key,
                                    @Query("channelId") String channelId,
                                    @Query("q") String q);

    //@GET("search/{param1}")
    //recuperarVideos(@Path("param1") String param1, String param2);

    /*
    https://www.googleapis.com/youtube/v3/search
    ?part=snippet
    &order=date
    &maxResults=20
    &key=
    &channelId=UCiwFcYaaqDSkzaA0CipV2BA
    */
}
