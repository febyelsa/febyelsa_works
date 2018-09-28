package com.articles.nytimes.repository;

import android.app.Application;

import com.articles.nytimes.database.ArticlesDao;
import com.articles.nytimes.di.AppModule;
import com.articles.nytimes.di.DaggerAppComponent;
import com.articles.nytimes.network.ApiService;

import javax.inject.Inject;

public class BaseRepository {

     ArticlesDao articlesDao;
     ApiService apiService;
     Application application;

    @Inject
    public BaseRepository(Application application, ArticlesDao articlesDao, ApiService service) {
        this.application = application;
        this.articlesDao = articlesDao;
        this.apiService = service;
        goDagger();
    }

    public BaseRepository() {
        //default constructor
    }

    private void goDagger() {
        DaggerAppComponent.builder().appModule(new AppModule(application)).build().inject(this);
    }

    public ArticlesDao getArticlesDao() {
        return articlesDao;
    }

    public ApiService getApiService() {
        return apiService;
    }

}
