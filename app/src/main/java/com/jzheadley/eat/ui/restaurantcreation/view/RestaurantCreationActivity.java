package com.jzheadley.eat.ui.restaurantcreation.view;

import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.jzheadley.eat.R;
import com.jzheadley.eat.data.models.Restaurant;
import com.jzheadley.eat.data.services.RestaurantService;
import com.jzheadley.eat.data.services.UserService;
import com.jzheadley.eat.ui.base.view.BaseActivity;
import com.jzheadley.eat.ui.layoutobjects.CheckBoxGroupView;
import com.jzheadley.eat.ui.menucreation.view.MenuCreationActivity;
import com.jzheadley.eat.ui.restaurantcreation.presenter.RestaurantCreationPresenter;
import com.jzheadley.eat.utils.Constants;
import com.sakebook.android.uploadhelper.UploadHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class RestaurantCreationActivity extends BaseActivity {
    private static final String TAG = "RestaurantCreationActiv";
    private static final int OPENING_HOURS_RESULT = 2;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int MENU_CREATION_RESULT = 3;
    private RestaurantCreationPresenter restaurantCreationPresenter;
    private Restaurant restaurant;
    private String imgurPhotoUrl = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_restaurant);
        RestaurantService restaurantService = new RestaurantService();
        UserService userService = new UserService();
        restaurantCreationPresenter = new RestaurantCreationPresenter(this,
            restaurantService, userService);
        createFoodTypeCheckBoxes();
        setupCountrySpinner();
        restaurant = new Restaurant();

    }

    private void setupCountrySpinner() {
        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<>();
        String country;
        for (Locale loc : locale) {
            country = loc.getDisplayCountry();
            if (country.length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
        Spinner countrySpinner = (Spinner) findViewById(R.id.restaurant_creation_country_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
            android.R.layout.simple_spinner_item, countries);
        countrySpinner.setAdapter(adapter);
        countrySpinner.setSelection(adapter.getPosition("United States"));
    }

    private void createFoodTypeCheckBoxes() {
        CheckBoxGroupView checkBoxGroup = (CheckBoxGroupView)
            findViewById(R.id.restaurant_food_type);
        for (String str : getResources().getStringArray(R.array.types_of_food)) {
            CheckBox checkbox = new CheckBox(getApplicationContext());
            checkbox.setText(str);
            checkbox.setTextColor(Color.BLACK);
            checkBoxGroup.put(checkbox);
        }
    }


    public void onNewRestaurantImageClick(View view) {
        Intent imageGalleryChooserIntent = new Intent();
        imageGalleryChooserIntent.setType("image/*");
        imageGalleryChooserIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(imageGalleryChooserIntent, "Select Picture"),
            PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                // TODO: 11/2/2016 MVP This
                if (resultCode == RESULT_OK) {
                    UploadHelper helper = new UploadHelper(this, restaurantCreationPresenter);
                    helper.setClientId(Constants.IMGUR_CLIENT_ID);
                    helper.setSecretId(Constants.IMGUR_SECRET);
                    Uri pathToFile = data.getData();
                    helper.uploadData(data.getData());
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),
                            pathToFile);
                        ImageView imageView = (ImageView)
                            findViewById(R.id.restaurant_creation_add_photo);
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException error) {
                        error.printStackTrace();
                    }
                }
                break;
            case OPENING_HOURS_RESULT:
                break;
            default:
                break;
        }
    }

    public void setRestaurantUrl(String url) {
        imgurPhotoUrl = url;
    }


    public void onSubmitButton(View view) {
        // TODO: 10/11/2016 Figure out how to represent menuHours in the database and add them
        Log.d(TAG, "onSubmitButton: " + ((EditText)
            findViewById(R.id.restaurant_creation_address)).getText().toString());

        restaurant.setAddress(((EditText)
            findViewById(R.id.restaurant_creation_address)).getText().toString());
        restaurant.setCity(((EditText)
            findViewById(R.id.restaurant_creation_city)).getText().toString());
        restaurant.setCountry(((Spinner)
            findViewById(R.id.restaurant_creation_country_spinner))
            .getSelectedItem().toString());
        restaurant.setDescription(((EditText)
            findViewById(R.id.restaurant_creation_description)).getText().toString());
        restaurant.setName(((EditText)
            findViewById(R.id.restaurant_creation_name)).getText().toString());
        restaurant.setZipcode(((EditText)
            findViewById(R.id.restaurant_creation_zipcode)).getText().toString());
        restaurant.setPictureurl(imgurPhotoUrl);

        Log.d(TAG, "onSubmitButton: " + restaurant);


        restaurantCreationPresenter.getUserByFirebaseId(
            FirebaseAuth.getInstance().getCurrentUser().getUid(), restaurant);

        restaurantCreationPresenter.postRestaurant(restaurant);
        finish();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_restaurant_creation_hours:
                Intent menuHoursIntent = new Intent(view.getContext(), OpeningHoursActivity.class);
                menuHoursIntent.putExtra("restaurant", restaurant);
                startActivityForResult(menuHoursIntent, OPENING_HOURS_RESULT);
                break;

            case R.id.btn_menu_creation:
                Intent menuCreationIntent = new Intent(view.getContext(), MenuCreationActivity.class);
                startActivityForResult(menuCreationIntent, MENU_CREATION_RESULT);
                break;

            default:
                break;
        }
    }
}
