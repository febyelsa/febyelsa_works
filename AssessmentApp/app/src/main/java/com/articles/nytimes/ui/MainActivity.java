package com.articles.nytimes.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.articles.nytimes.R;
import com.articles.nytimes.ui.fragments.ArticleListFragment;
import com.articles.nytimes.utils.FragmentUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentUtils.replaceFragment(this, ArticleListFragment.newInstance(), R.id.fragContainer, false);

    }
}
