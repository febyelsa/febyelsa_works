package com.articles.nytimes.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.articles.nytimes.R;
import com.articles.nytimes.databinding.FragmentArticleDetailsBinding;
import com.articles.nytimes.viewmodel.ArticlesDetailsViewModel;

public class ArticleDetailFragment extends BaseFragment<ArticlesDetailsViewModel, FragmentArticleDetailsBinding> {
    @Override
    protected Class<ArticlesDetailsViewModel> getViewModel() {
        return ArticlesDetailsViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_article_details;
    }

    public static ArticleDetailFragment newInstance(Bundle args) {
        ArticleDetailFragment articleDetailFragment = new ArticleDetailFragment();
        articleDetailFragment.setArguments(args);
        return articleDetailFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(null!=getArguments()){
            viewModel.setArticleId(getArguments().getLong("articleId"));
            viewModel.setUrl(getArguments().getString("url"));
            viewModel.getArticles();
        }
        viewModel.getSelectedArticle()
                .observe(getActivity(), listResource -> {
                    dataBinding.setArticleDetailViewModel(viewModel);
                });

    }
}
