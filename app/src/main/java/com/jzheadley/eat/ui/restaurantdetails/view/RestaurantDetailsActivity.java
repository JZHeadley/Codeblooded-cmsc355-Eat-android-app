package com.jzheadley.eat.ui.restaurantdetails.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.menu.view.RestaurantMenuActivity;
import com.jzheadley.eat.ui.restaurantdetails.presenter.RestaurantDetailsPresenter;


public class RestaurantDetailsActivity extends BaseActivity {
    private static final String TAG = "RestaurantDetailsActivi";
    private ImageView restaurantPhoto;
    private TextView restaurantAddress;
    private TextView restaurantDescription;
    private Button showMenuBtn;
    private TextView restaurantName;
    private Restaurant restaurant;
    private RestaurantDetailsPresenter restaurantDetailsPresenter;
    private RestaurantService restaurantService;
    private int restaurantId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        restaurantService = new RestaurantService();
        restaurantDetailsPresenter = new RestaurantDetailsPresenter(this, restaurantService);
        restaurant = getIntent().getExtras().getParcelable("restaurant");
        // restaurantId = getIntent().getExtras().getInt("restaurantId");
        Log.i(TAG, "onCreate: " + restaurantId);
        // restaurantDetailsPresenter.loadRestaurantDetails(restaurantId + 1);
        displayRestaurant(restaurant);
    }

    // TODO: 10/9/2016 Fix the bug and actually display the restaurant that is clicked on...
    public void displayRestaurant(Restaurant restaurant) {
        Log.d(TAG, "displayRestaurant: " + restaurant);
        restaurantAddress = (TextView) findViewById(R.id.address_restaurant_detail);
        restaurantName = (TextView) findViewById(R.id.restaurant_name_detail);
        restaurantDescription = (TextView) findViewById(R.id.description_restaurant_detail);
        restaurantPhoto = (ImageView) findViewById(R.id.restaurant_photo_detail);

        restaurantName.setText(restaurant.getName());
        restaurantAddress.setText(restaurant.getAddress() + "\n" + restaurant.getCity()
            + ", " + restaurant.getCity() + " " + restaurant.getZipcode());
        restaurantDescription.setText(restaurant.getDescription());
        Glide.with(this)
            .load(restaurant.getPictureurl())
            .crossFade()
            .fitCenter()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(new GlideDrawableImageViewTarget(restaurantPhoto));
    }

    public void onMenuClick(View view) {
        Intent menuIntent = new Intent(view.getContext(), RestaurantMenuActivity.class);
        view.getContext().startActivity(menuIntent);
    }
}
