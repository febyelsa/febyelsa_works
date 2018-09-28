package com.articles.nytimes.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.articles.nytimes.database.AppDataBase;
import com.articles.nytimes.database.ArticlesDao;
import com.articles.nytimes.network.ApiService;
import com.articles.nytimes.repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application application() {
        return application;
    }

    @Provides
    @Singleton
    ApiService provideApiService() {
        return new ApiService();
    }

    @Provides
    @Singleton
    AppDataBase provideArticleDatabase() {
        return Room.databaseBuilder(application, AppDataBase.class, "articles.db").build();
    }

    @Provides
    @Singleton
    ArticlesDao provideArticleDao(AppDataBase articleDatabase) {
        return articleDatabase.articleDao();
    }

    @Provides
    @Singleton
    Repository providesRepository(ArticlesDao articlesDao, ApiService apiService) {
        return new Repository(application, articlesDao, apiService);
    }
}

