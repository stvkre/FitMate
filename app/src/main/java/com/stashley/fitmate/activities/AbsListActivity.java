package com.stashley.fitmate.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.contentful.vault.Vault;
import com.stashley.fitmate.App;
import com.stashley.fitmate.R;
import com.stashley.fitmate.adapters.AbsListAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public abstract class AbsListActivity<A, L> extends AbsActivity
    implements SwipeRefreshLayout.OnRefreshListener,
    LoaderManager.LoaderCallbacks<L> {

  protected final int LOADER_ID = getLoaderId();

  protected AbsListAdapter<A, ?> adapter;

  protected BroadcastReceiver reloadReceiver;

  protected abstract int getLoaderId();

  protected abstract AbsListAdapter<A, ?> createAdapter();

  protected abstract List<A> getResultList(L data);

  @Bind(R.id.swipe_refresh)
  SwipeRefreshLayout swipeRefreshLayout;

  @Bind(R.id.list)
  ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    ButterKnife.bind(this);

    adapter = createAdapter();
    createReceivers();
    registerReceiver(reloadReceiver, new IntentFilter(Vault.ACTION_SYNC_COMPLETE));
    initList();
    initSwipeRefresh();
    initLoader();
  }

  @Override
  public void onDestroy() {
    unregisterReceiver(reloadReceiver);
    super.onDestroy();
  }

  protected void createReceivers() {
    reloadReceiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        restartLoader();
      }
    };
  }

  protected void initSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(this);
  }

  protected void initList() {
    listView.setAdapter(adapter);
  }

  protected void initLoader() {
    getSupportLoaderManager().initLoader(LOADER_ID, null, this);
  }

  protected void restartLoader() {
    getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
  }

  @Override
  public void onLoadFinished(Loader<L> loader, L data) {
    if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
      swipeRefreshLayout.setRefreshing(false);
    }

    if (data != null) {
      adapter.setData(getResultList(data));
      adapter.notifyDataSetChanged();
    }
  }

  @Override
  public void onLoaderReset(Loader<L> loader) {
  }

  @Override
  public void onRefresh() {
    App.requestSync();
  }

  @OnItemClick(R.id.list)
  void onItemClick(View v, int position) {
  }
}
