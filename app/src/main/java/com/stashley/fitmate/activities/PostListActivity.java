package com.stashley.fitmate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.view.View;

import com.stashley.fitmate.Intents;
import com.stashley.fitmate.adapters.AbsListAdapter;
import com.stashley.fitmate.adapters.PostListAdapter;
import com.stashley.fitmate.lib.LoaderId;
import com.stashley.fitmate.loaders.PostListLoader;
import com.stashley.fitmate.vault.Author;
import com.stashley.fitmate.vault.Post;
import com.stashley.fitmate.views.AuthorView;

import org.parceler.Parcels;

import java.util.List;

public class PostListActivity extends AbsListActivity<Post, PostListLoader.Result> {
  private Author author;

  private AuthorView authorView;

  @Override
  protected int getLoaderId() {
    return LoaderId.forClass(PostListActivity.class);
  }

  @Override
  protected AbsListAdapter<Post, ?> createAdapter() {
    return new PostListAdapter();
  }

  @Override
  public Loader<PostListLoader.Result> onCreateLoader(int id, Bundle args) {
    return new PostListLoader(getAuthorRemoteId());
  }

  @Override
  protected List<Post> getResultList(PostListLoader.Result data) {
    return data.posts();
  }

  @Override
  public void onLoadFinished(Loader<PostListLoader.Result> loader, PostListLoader.Result data) {
    super.onLoadFinished(loader, data);
    if (data != null) {
      author = data.author();
      if (author != null) {
        authorView.populate(author);
      }
    }
  }

  @Override
  void onItemClick(View v, int position) {
    int headerViewsCount = listView.getHeaderViewsCount();
    if (headerViewsCount > 0 && position < headerViewsCount) {
      return;
    }

    Post post = adapter.getItem(position - headerViewsCount);
    startActivity(new Intent(this, PostActivity.class)
        .putExtra(Intents.EXTRA_POST, Parcels.wrap(post)));
  }

  @Override
  protected void initList() {
    String authorRemoteId = getAuthorRemoteId();
    if (authorRemoteId != null) {
      authorView = new AuthorView(this);
      listView.addHeaderView(authorView);
    }

    super.initList();
  }

  @Nullable
  private String getAuthorRemoteId() {
    return getIntent().getStringExtra(Intents.EXTRA_REMOTE_ID);
  }
}
