package com.jzheadley.eat.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jzheadley.eat.R;
import com.jzheadley.eat.models.Restaurant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NearbyRestaurantsAdapter extends RecyclerView.Adapter<NearbyRestaurantsAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurants;

    public NearbyRestaurantsAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_restaurant_item, parent, false);
        return new RestaurantViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder restaurantViewHolder, int position) {
        Restaurant restaurant = restaurants.get(position);

        // TODO: 10/6/2016 Get the picture and set the viewHolder's image to be it
        Glide.with(restaurantViewHolder.itemView.getContext())
                .load(restaurant.getPictureurl())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.restaurant_placeholder)
                .into(restaurantViewHolder.image);
        // TODO: 10/6/2016 implement getting current location and distance to restaurant
        double restaurantDistance = 0;
        String restaurantDistanceText = "" + restaurantDistance;

        restaurantViewHolder.restaurantDistance.setText(restaurantDistanceText);
        restaurantViewHolder.restaurantName.setText(restaurant.getName());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.restaurant_picture)
        protected ImageView image;
        @BindView(R.id.restaurant_distance)
        protected TextView restaurantDistance;
        @BindView(R.id.restaurant_name)
        protected TextView restaurantName;


        public RestaurantViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
