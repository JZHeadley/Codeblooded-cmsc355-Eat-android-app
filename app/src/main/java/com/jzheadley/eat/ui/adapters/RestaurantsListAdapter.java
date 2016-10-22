package com.jzheadley.eat.ui.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.ui.restaurantdetails.view.RestaurantDetailsActivity;

import java.util.List;

public class RestaurantsListAdapter extends RecyclerView.Adapter<RestaurantsListAdapter.RestaurantViewHolder> {

    private static final String TAG = "NearbyRestaurantsAdapte";
    private List<Restaurant> mRestaurants;

    public RestaurantsListAdapter(List<Restaurant> restaurants) {
        this.mRestaurants = restaurants;
    }


    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_restaurant_item, parent, false);
        return new RestaurantViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final RestaurantViewHolder restaurantViewHolder, int position) {
        final Restaurant restaurant = mRestaurants.get(position);
        // TODO: 10/6/2016 implement getting current location and distance to restaurant
        double restaurantDistance = 0;
        String restaurantDistanceText = "" + restaurantDistance;

        // Load image after text so it has something in the view if image doesn't immediately load
        Glide.with(restaurantViewHolder.itemView.getContext())
                .load(restaurant.getPictureurl())
                .crossFade()
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.restaurant_placeholder)
                .into(new GlideDrawableImageViewTarget(restaurantViewHolder.image));
        Log.d(TAG, "onBindViewHolder: Position is " + position);
        restaurantViewHolder.restaurantDistance.setText(restaurantDistanceText);
        restaurantViewHolder.restaurantDescription.setText(restaurant.getDescription());
        restaurantViewHolder.restaurantName.setText(restaurant.getName());
        restaurantViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + restaurantViewHolder.getAdapterPosition());
                Intent restaurantDetailsIntent = new Intent(v.getContext(), RestaurantDetailsActivity.class);
                restaurantDetailsIntent.putExtra("restaurantId", restaurantViewHolder.getAdapterPosition());
//                restaurantDetailsIntent.putExtra("restaurantId", mRestaurants.get(restaurantViewHolder.getAdapterPosition()));
                v.getContext().startActivity(restaurantDetailsIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        // TODO: 10/8/2016 Figure out how to make this work with ButterKnife.
        ImageView image;
        TextView restaurantDistance;
        TextView restaurantName;
        TextView restaurantDescription;

        RestaurantViewHolder(View view) {
            super(view);
            restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
            restaurantDescription = (TextView) view.findViewById(R.id.restaurant_description);
            restaurantDistance = (TextView) view.findViewById(R.id.restaurant_distance);
            image = (ImageView) view.findViewById(R.id.restaurant_photo);
        }
    }

}
