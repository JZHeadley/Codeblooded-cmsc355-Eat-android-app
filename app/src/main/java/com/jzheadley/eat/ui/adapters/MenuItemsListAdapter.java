package com.jzheadley.eat.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.MenuItem;

import java.util.List;

public class MenuItemsListAdapter extends RecyclerView.Adapter<MenuItemsListAdapter.MenuItemViewHolder> {
    List<MenuItem> menuItems;

    public MenuItemsListAdapter(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.layout_menu_item_item, parent, false);
        return new MenuItemsListAdapter.MenuItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuItemViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        holder.menuItemName.setText(menuItem.getName());
        holder.menuItemPrice.setText(menuItem.getPrice());
        holder.menuItemDescription.setText(menuItem.getDescription());


    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    static class MenuItemViewHolder extends RecyclerView.ViewHolder {
        TextView menuItemName;
        TextView menuItemPrice;
        TextView menuItemDescription;

        public MenuItemViewHolder(View itemView) {
            super(itemView);
            menuItemName = (TextView) itemView.findViewById(R.id.menu_item_name);
            menuItemPrice = (TextView) itemView.findViewById(R.id.menu_item_price);
            menuItemDescription = (TextView) itemView.findViewById(R.id.menu_item_description);

        }
    }
}
