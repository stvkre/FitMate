package com.stashley.fitmate.vault;

import com.contentful.vault.Space;

import com.stashley.fitmate.lib.Const;

@Space(value = Const.SPACE_ID, models = { Author.class, Category.class, Post.class })
public class BlogSpace {
}
