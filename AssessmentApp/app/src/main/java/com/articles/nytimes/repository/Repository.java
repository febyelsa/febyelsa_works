package com.articles.nytimes.repository;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.articles.nytimes.database.ArticleEntity;
import com.articles.nytimes.database.ArticlesDao;
import com.articles.nytimes.interfaces.ResponseListener;
import com.articles.nytimes.network.ApiService;
import com.articles.nytimes.utils.Constants;

import java.util.List;

public class Repository extends BaseRepository {

    private ResponseListener repositoryCallback;
    private ResponseListener responseListener = new ResponseListener() {
        @Override
        public void onResponse(String status, MutableLiveData<List<ArticleEntity>> mutableLiveData) {
            if (Constants.STATUS_SUCCESS.equalsIgnoreCase(status) && null != mutableLiveData) {
                saveArticles(status, mutableLiveData);
                return;
            }

            repositoryCallback.onResponse(status, mutableLiveData);
        }
    };

    public Repository(Application application, ArticlesDao articlesDao, ApiService service) {
        super(application, articlesDao, service);
    }

    private void initiateServiceCall() {
        getApiService().connectToNetwork();
        getApiService().startApiServiceCall(responseListener);
    }

    public void loadArticles(ResponseListener repositoryCallBack) {

        MutableLiveData<List<ArticleEntity>> articlesList = new MutableLiveData<>();
        this.repositoryCallback = repositoryCallBack;

        loadSavedArticles(articlesList);

    }
    private void loadSavedArticles(MutableLiveData<List<ArticleEntity>> articlesList) {

        new AsyncTask<Void, Void, Void>() {
            List<ArticleEntity> articleEntities;

            @Override
            protected Void doInBackground(Void... voids) {
                articleEntities = getArticlesDao().loadArticles();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                articlesList.setValue(articleEntities);

                if ( articlesList.getValue() != null && !articlesList.getValue().isEmpty()) {
                    repositoryCallback.onResponse(Constants.STATUS_SUCCESS, articlesList);
                    return;
                }

                initiateServiceCall();
            }
        }.execute();
    }

    private void saveArticles(String status, MutableLiveData<List<ArticleEntity>> listMutableLiveData) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                getArticlesDao().saveArticles(listMutableLiveData.getValue());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                repositoryCallback.onResponse(status, listMutableLiveData);
            }
        }.execute();
    }

    public  MutableLiveData<ArticleEntity> getEntity(long id, MutableLiveData<ArticleEntity> articleEntityMutableLiveData) {

        new AsyncTask<Void, Void, ArticleEntity>() {

            @Override
            protected ArticleEntity doInBackground(Void... voids) {
              return getArticlesDao().loadArticleById(id);

            }

            @Override
            protected void onPostExecute(ArticleEntity articleEntity) {
                articleEntityMutableLiveData.setValue(articleEntity);
            }
        }.execute();
        return articleEntityMutableLiveData;
    }
}
