package com.jzheadley.eat.ui.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Menu;
import com.jzheadley.eat.ui.menucategories.view.MenuCategoriesActivity;

import java.util.List;

public class MenusListAdapter extends RecyclerView.Adapter<MenusListAdapter.MenuViewHolder> {
    private final List<Menu> menus;

    public MenusListAdapter(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.layout_menu_item, parent, false);
        return new MenusListAdapter.MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MenuViewHolder holder, int position) {
        final Menu menu = menus.get(position);
        holder.menuButton.setText(menu.getMenuName());
        Glide.with(holder.menuButton.getContext())
            .load("http://lorempixel.com/400/300/food/")
            .asBitmap()
            .fitCenter()
            .centerCrop()
            // .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable = new BitmapDrawable(resource);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        holder.menuButton.setBackground(drawable);
                    }
                }
            });

        holder.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent categoriesIntent = new Intent(view.getContext(), MenuCategoriesActivity.class);
                categoriesIntent.putExtra("restaurantMenu", menu);
                view.getContext().startActivity(categoriesIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        Button menuButton;

        public MenuViewHolder(View itemView) {
            super(itemView);
            menuButton = (Button) itemView.findViewById(R.id.btn_view_menu);
        }
    }
}
