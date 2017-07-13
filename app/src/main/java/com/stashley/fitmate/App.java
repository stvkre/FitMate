package com.stashley.fitmate;

import android.app.Application;

import com.contentful.vault.SyncConfig;
import com.contentful.vault.Vault;

import com.stashley.fitmate.lib.ClientProvider;
import com.stashley.fitmate.vault.BlogSpace;

public class App extends Application {
  private static App instance;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    requestSync();
  }

  public static App get() {
    return instance;
  }

  public static void requestSync() {
    requestSync(false);
  }

  public static void requestSync(boolean invalidate) {
    Vault.with(get(), BlogSpace.class).requestSync(
        SyncConfig.builder()
            .setClient(ClientProvider.get())
            .setInvalidate(invalidate)
            .build());
  }
}
