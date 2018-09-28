package com.articles.nytimes.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=be89bc7594ee45a5ba63f74d70bc4c1a")
    Call<ServiceResponse> getArticlesList();
}
