package com.articles.nytimes.di;

import android.app.Application;

import com.articles.nytimes.repository.BaseRepository;
import com.articles.nytimes.viewmodel.ArticlesDetailsViewModel;
import com.articles.nytimes.viewmodel.ArticlesViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {

    void inject(ArticlesViewModel articlesViewModel);
    void inject(ArticlesDetailsViewModel articlesViewModel);
    void inject(Application application);
    void inject(BaseRepository baseRepository);

    @Component.Builder
    interface Builder {
        AppComponent build();
        Builder appModule(AppModule appModule);
    }

}
