package com.jzheadley.eat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jzheadley.eat.R;
import com.jzheadley.eat.models.Restaurant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zephy on 9/28/2016.
 */
public class NearbyRestaurantsAdapter extends ArrayAdapter<Restaurant> {
    private LayoutInflater inflater;

    public NearbyRestaurantsAdapter(Context context, int textViewResourceId, List<Restaurant> restaurants) {
        super(context, textViewResourceId, restaurants);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = inflater.inflate(R.layout.layout_restaurant_item, parent, false);
        holder = new ViewHolder(view);
        // TODO: 9/28/2016 implement getting image and inflating it into the imageView
        Glide.with(inflater.getContext()).load(getItem(position).getPictureurl()).into(holder.image);
        // Picasso.with(inflater.getContext()).load("http://lorempixel.com/200/200/sports/" + (position + 1)).into(holder.image);
        // TODO: 9/28/2016 implement functionality to take current location and find distance to restaurant
        double distanceToRestaurant = 0.0;
        holder.restaurantDistance.setText(distanceToRestaurant + " Miles");
        holder.restaurantName.setText(getItem(position).getName());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.restaurant_picture)
        ImageView image;
        @BindView(R.id.restaurant_distance)
        TextView restaurantDistance;
        @BindView(R.id.restaurant_name)
        TextView restaurantName;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
