package com.articles.nytimes.interfaces;

import com.articles.nytimes.database.ArticleEntity;

public interface ItemClickCallBack {
    void onArticleItemClicked(ArticleEntity articleEntity);
}
