package com.articles.nytimes.di;

import com.articles.nytimes.viewmodel.ArticlesDetailsViewModel;
import com.articles.nytimes.viewmodel.ArticlesViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ArticlesViewModel bindsArticleModule(ArticlesViewModel searchViewModel);
    @Binds
    abstract ArticlesDetailsViewModel bindsArticleDetailModule(ArticlesDetailsViewModel searchViewModel);
}
