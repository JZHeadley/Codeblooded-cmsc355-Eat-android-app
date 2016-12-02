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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Category;
import com.jzheadley.eat.ui.categorymenuitems.view.CategoryMenuItemsActivity;

import java.util.List;

public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.CategoryViewHolder> {
    private final List<Category> categories;

    public CategoriesListAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.layout_category_item, parent, false);
        return new CategoriesListAdapter.CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        final Category category = categories.get(position);

        holder.categoryButton.setText(category.getCategoryName());
        Glide.with(holder.categoryButton.getContext())
            .load("http://lorempixel.com/357/200/food/")
            .asBitmap()
            .fitCenter()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable = new BitmapDrawable(resource);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        holder.categoryButton.setBackground(drawable);
                    }
                }
            });
        holder.categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuItemsIntent = new Intent(view.getContext(), CategoryMenuItemsActivity.class);
                menuItemsIntent.putExtra("menuCategory", category);
                view.getContext().startActivity(menuItemsIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        Button categoryButton;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryButton = (Button) itemView.findViewById(R.id.btn_view_category);
        }
    }
}
