package com.articles.nytimes.network;

import com.google.gson.annotations.SerializedName;
import com.articles.nytimes.database.ArticleEntity;

import java.util.List;

public class ServiceResponse {

    public List<ArticleEntity> getPopularArticles() {
        return popularArticles;
    }

    public void setPopularArticles(List<ArticleEntity> popularArticles) {
        this.popularArticles = popularArticles;
    }

    @SerializedName("results")
    private List<ArticleEntity> popularArticles;
}
