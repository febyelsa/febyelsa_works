package com.articles.nytimes.ui;

import android.app.Application;

import com.articles.nytimes.di.AppComponent;
import com.articles.nytimes.di.AppModule;
import com.articles.nytimes.di.DaggerAppComponent;

public class NYTimesArticlesApplication   extends Application {

    AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)) .build();
        appComponent.inject(this);
    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
