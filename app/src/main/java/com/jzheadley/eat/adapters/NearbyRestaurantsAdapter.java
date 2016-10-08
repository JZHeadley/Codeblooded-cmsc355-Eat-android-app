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
        Glide.with(restaurantViewHolder.itemView.getContext())
                .load(restaurant.getPictureurl())
                .crossFade()
                // TODO: 10/8/2016 Tweak how the image is cropped and displayed.
                .fitCenter()
                .centerCrop()
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

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        // TODO: 10/8/2016 Figure out how to make this work with ButterKnife.
        ImageView image;
        TextView restaurantDistance;
        TextView restaurantName;


        RestaurantViewHolder(View view) {
            super(view);
            restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
            restaurantDistance = (TextView) view.findViewById(R.id.restaurant_distance);
            image = (ImageView) view.findViewById(R.id.restaurant_photo);
        }
    }

}
