package com.stashley.fitmate.vault;

import com.contentful.vault.Asset;
import com.contentful.vault.ContentType;
import com.contentful.vault.Field;
import com.contentful.vault.Resource;
import com.stashley.fitmate.lib.Const;

import org.parceler.Parcel;

@ContentType(Const.CONTENT_TYPE_CATEGORY)
@Parcel
public class Category extends Resource {
  @Field
  String title;

  @Field
  String shortDescription;

  @Field Asset icon;

  public String title() {
    return title;
  }

  public String shortDescription() {
    return shortDescription;
  }

  public Asset icon() {
    return icon;
  }
}
