package com.jzheadley.eat.ui.adapters;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
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
import com.jzheadley.eat.data.services.LocationService;
import com.jzheadley.eat.ui.restaurantdetails.view.RestaurantDetailsActivity;

import java.io.IOException;
import java.util.List;

public class RestaurantsListAdapter extends RecyclerView
    .Adapter<RestaurantsListAdapter.RestaurantViewHolder> {

    private static final String TAG = "NearbyRestaurantsAdapte";
    private final LocationService locationService;
    private List<Restaurant> restaurants;
    private Geocoder geocoder;

    public RestaurantsListAdapter(List<Restaurant> restaurants, LocationService locationService, Geocoder geocoder) {
        this.restaurants = restaurants;
        this.locationService = locationService;
        this.geocoder = geocoder;
    }

    public static double convertMetersToMiles(double meters) {
        return (meters / 1609.344);
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
        final Restaurant restaurant = restaurants.get(position);
        // TODO: 10/6/2016 implement getting current location and distance to restaurant
        Location restaurantLocation = new Location("jzheadley");
        Log.d(TAG, "onBindViewHolder: " + locationService.getLocation());
        // restaurantLocation = Geo


        double restaurantDistance = 0;
        List<Address> possibleLocations = null;
        if (locationService.getLocation() != null) {
            try {
                possibleLocations = geocoder.getFromLocationName(restaurant.getName() + " " + restaurant.getCity() + " " + restaurant.getCountry(), 10);
                if (possibleLocations.size() > 0) {
                    restaurantLocation.setLatitude(possibleLocations.get(0).getLatitude());
                    restaurantLocation.setLongitude(possibleLocations.get(0).getLongitude());
                    restaurantDistance = convertMetersToMiles(locationService.getLocation().distanceTo(restaurantLocation));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String restaurantDistanceText = "" + restaurantDistance;
        restaurantViewHolder.restaurantDistance.setText(restaurantDistanceText);

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
        restaurantViewHolder.restaurantDescription.setText(restaurant.getDescription());
        restaurantViewHolder.restaurantName.setText(restaurant.getName());
        restaurantViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + restaurantViewHolder
                    .getAdapterPosition());
                Intent restaurantDetailsIntent =
                    new Intent(view.getContext(), RestaurantDetailsActivity.class);
                restaurantDetailsIntent.putExtra("restaurant", restaurant);
                view.getContext().startActivity(restaurantDetailsIntent);
            }
        });
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
