package com.stashley.fitmate.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.contentful.vault.Asset;
import com.squareup.picasso.Picasso;
import com.stashley.fitmate.R;
import com.stashley.fitmate.vault.Author;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AuthorView extends LinearLayout {
  @Bind(R.id.photo)
  ImageView photo;

  @Bind(R.id.name)
  TextView name;

  @Bind(R.id.bio)
  TextView bio;

  public AuthorView(Context context) {
    super(context);
    init();
  }

  private void init() {
    View.inflate(getContext(), R.layout.view_author, this);
    ButterKnife.bind(this);
  }

  public void populate(Author author) {
    Asset asset = author.profilePhoto();
    if (asset != null) {
      Picasso.with(getContext()).load(asset.url()).fit().centerInside().into(photo);
    }
    name.setText(author.name());
    bio.setText(author.biography());
  }
}