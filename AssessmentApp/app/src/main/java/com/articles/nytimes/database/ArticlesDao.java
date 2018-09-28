package com.articles.nytimes.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ArticlesDao {

    @Query("SELECT * FROM articles")
    List<ArticleEntity> loadArticles();

    @Query("SELECT * FROM articles where id=:id")
    ArticleEntity loadArticleById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveArticles(List<ArticleEntity> articleEntities);

}
