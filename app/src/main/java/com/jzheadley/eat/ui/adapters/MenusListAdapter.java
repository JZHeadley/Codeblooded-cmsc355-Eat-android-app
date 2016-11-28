package com.jzheadley.eat.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class MenusListAdapter extends RecyclerView.Adapter<MenusListAdapter.MenuViewHolder> {
    @Override
    public MenusListAdapter.MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MenusListAdapter.MenuViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        public MenuViewHolder(View itemView) {
            super(itemView);
        }
    }
}
