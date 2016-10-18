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
import com.jzheadley.eat.models.Restaurant;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.menu.view.RestaurantMenuActivity;
import com.jzheadley.eat.ui.restaurantdetails.presenter.RestaurantDetailsPresenter;


public class RestaurantDetailsActivity extends BaseActivity {
    private static final String TAG = "RestaurantDetailsActivi";
    private ImageView mRestaurantPhoto;
    private TextView mRestaurantAddress;
    private TextView mRestaurantDescription;
    private Button mShowMenuBtn;
    private TextView mRestaurantName;

    private RestaurantDetailsPresenter mRestaurantDetailsPresenter;
    private RestaurantService mRestaurantService;
    private int mRestaurantId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        mRestaurantService = new RestaurantService();
        mRestaurantDetailsPresenter = new RestaurantDetailsPresenter(this, mRestaurantService);
//        mRestaurantId = getIntent().getExtras().getParcelable("restaurant");
        mRestaurantId = getIntent().getExtras().getInt("restaurantId");
        Log.i(TAG, "onCreate: " + mRestaurantId);
        mRestaurantDetailsPresenter.loadRestaurantDetails(mRestaurantId + 1);
    }

    // TODO: 10/9/2016 Fix the bug and actually display the restaurant that is clicked on...
    public void displayRestaurant(Restaurant restaurant) {
        Log.d(TAG, "displayRestaurant: " + restaurant);
        mRestaurantAddress = (TextView) findViewById(R.id.address_restaurant_detail);
        mRestaurantName = (TextView) findViewById(R.id.restaurant_name_detail);
        mRestaurantDescription = (TextView) findViewById(R.id.description_restaurant_detail);
        mRestaurantPhoto = (ImageView) findViewById(R.id.restaurant_photo_detail);

        mRestaurantName.setText(restaurant.getName());
        mRestaurantAddress.setText(restaurant.getAddress() + "\n" + restaurant.getCity()
                + ", " + restaurant.getCity() + " " + restaurant.getZipcode());
        mRestaurantDescription.setText(restaurant.getDescription());
        Glide.with(this)
                .load(restaurant.getPictureurl())
                .crossFade()
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new GlideDrawableImageViewTarget(mRestaurantPhoto));
    }

    public void onMenuClick(View view) {
        Intent menuIntent = new Intent(view.getContext(), RestaurantMenuActivity.class);
        view.getContext().startActivity(menuIntent);
    }
}
