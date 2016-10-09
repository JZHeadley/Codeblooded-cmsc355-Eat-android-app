package com.jzheadley.eat.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.jzheadley.eat.R;
import com.jzheadley.eat.models.Restaurant;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.presenters.RestaurantDetailsPresenter;


public class RestaurantDetailsActivity extends AppCompatActivity {
    private static final String TAG = "RestaurantDetailsActivi";
    private ImageView restaurantPhoto;
    private TextView restaurantAddress;
    private TextView restaurantDescription;
    private Button showMenuBtn;
    private TextView restaurantName;

    private RestaurantDetailsPresenter restaurantDetailsPresenter;
    private RestaurantService restaurantService;
    private int restaurantId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_restaurant_details);
        restaurantService = new RestaurantService();
        restaurantDetailsPresenter = new RestaurantDetailsPresenter(this, restaurantService);
        restaurantId = getIntent().getExtras().getInt("restaurantId");
        Log.d(TAG, "onCreate: " + restaurantId);
        restaurantDetailsPresenter.loadRestaurantDetails(restaurantId + 1);
    }

    // TODO: 10/9/2016 Fix the bug and actually display the restaurant that is clicked on...
    public void displayRestaurant(Restaurant restaurant) {
        Log.d(TAG, "displayRestaurant: " + restaurant);
        restaurantAddress = (TextView) findViewById(R.id.address_restaurant_detail);
        restaurantName = (TextView) findViewById(R.id.restaurant_name_detail);
        restaurantDescription = (TextView) findViewById(R.id.description_restaurant_detail);
        restaurantPhoto = (ImageView) findViewById(R.id.restaurant_photo_detail);

        restaurantName.setText(restaurant.getName());
        restaurantAddress.setText(restaurant.getAddress() + "\n" + restaurant.getCity() + ", " + restaurant.getCity() + " " + restaurant.getZipcode());
        restaurantDescription.setText(restaurant.getDescription());
        Glide.with(this)
                .load(restaurant.getPictureurl())
                .crossFade()
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new GlideDrawableImageViewTarget(restaurantPhoto));
    }
}
