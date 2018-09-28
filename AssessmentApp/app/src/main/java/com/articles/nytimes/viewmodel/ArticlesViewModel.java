package com.articles.nytimes.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.widget.Toast;

import com.articles.nytimes.R;
import com.articles.nytimes.database.ArticleEntity;
import com.articles.nytimes.di.AppModule;
import com.articles.nytimes.di.DaggerAppComponent;
import com.articles.nytimes.interfaces.ResponseListener;
import com.articles.nytimes.repository.Repository;
import com.articles.nytimes.utils.Constants;

import java.util.List;

import javax.inject.Inject;

public class ArticlesViewModel extends AndroidViewModel {

    @Inject
    Repository repository;

    MutableLiveData<List<ArticleEntity>> mutableLiveData = new MutableLiveData<>();

    ResponseListener responseListener = (status, mutableLiveData) ->{

        this.mutableLiveData.setValue(mutableLiveData.getValue());

        if(Constants.STATUS_FAILURE.equalsIgnoreCase(status)){
            Toast.makeText(getApplication(),getApplication().getString(R.string.msg_failed),Toast.LENGTH_SHORT).show();
        }

    };

     ArticlesViewModel(Application application) {
        super(application);
        buildDagger();
        loadArticles();
    }

    public void loadArticles() {
       getArticles();
    }

    private void getArticles(){
        repository.loadArticles(responseListener);
    }

    public MutableLiveData<List<ArticleEntity>> getMutableLiveData() {
        return mutableLiveData;
    }

    private void buildDagger() {
        DaggerAppComponent.builder().appModule(new AppModule(getApplication())).build().inject(this);
    }



}
