package com.dwiyan.tvandmoviecatalogue.supportConfig;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
public class CustomGlide extends AppGlideModule {

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}