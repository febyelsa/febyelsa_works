package com.articles.nytimes.interfaces;

import android.arch.lifecycle.MutableLiveData;

import com.articles.nytimes.database.ArticleEntity;

import java.util.List;

public interface ResponseListener {
    void onResponse(String status, MutableLiveData<List<ArticleEntity>> mutableLiveData);
}
