package com.dwiyan.tvandmoviecatalogue.FavouriteFragment.ui.main;

import android.content.Context;


import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dwiyan.tvandmoviecatalogue.FavouriteFragment.MovieFavourite.MovieFavourite;
import com.dwiyan.tvandmoviecatalogue.FavouriteFragment.TVFavourite.TVFavaourite;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieListFragment;
import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.TvCatalogue.TvFragment;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class AdapterFavourite extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};

    private final Context mContext;

    public AdapterFavourite(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                MovieFavourite movieFavourite = new MovieFavourite();
                return movieFavourite;
            case 1:
                TVFavaourite tvFavaourite = new TVFavaourite();
                return  tvFavaourite;
            default:
                return null ;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}