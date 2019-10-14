package com.dwiyan.tvandmoviecatalogue.supportConfig;

import android.view.View;

public class CustomClick implements View.OnClickListener {

    private int position;
    private ItemClickCallback callback;

    public CustomClick(int position, ItemClickCallback callback) {
        this.position = position;
        this.callback = callback;
    }


    public interface ItemClickCallback {
        void ItemClicked(View v, int position);
    }

    @Override
    public void onClick(View view) {
        callback.ItemClicked(view, position);
    }

}