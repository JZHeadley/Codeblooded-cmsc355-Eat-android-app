package com.jzheadley.eat.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;

import com.jzheadley.eat.R;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.presenters.RestaurantCreationPresenter;
import com.jzheadley.eat.views.layoutViews.CheckBoxGroupView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class RestaurantCreationActivity extends BaseActivity {
    private RestaurantCreationPresenter restaurantCreationPresenter;
    private RestaurantService restaurantService;
    private int PICK_IMAGE_REQUEST = 1;

    public void onMenuHoursButtonClick(View view) {
        Intent menuHoursIntent = new Intent(view.getContext(), MenuHoursActivity.class);
        view.getContext().startActivity(menuHoursIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_restaurant);
        restaurantService = new RestaurantService();
        restaurantCreationPresenter = new RestaurantCreationPresenter(this, restaurantService);
        createFoodTypeCheckBoxes();

        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for (Locale loc : locale) {
            country = loc.getDisplayCountry();
            if (country.length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        Spinner countrySpinner = (Spinner) findViewById(R.id.country_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        countrySpinner.setAdapter(adapter);
    }

    private void createFoodTypeCheckBoxes() {
        CheckBoxGroupView checkBoxGroup = (CheckBoxGroupView) findViewById(R.id.type_of_food_check_group);
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
        startActivityForResult(Intent.createChooser(imageGalleryChooserIntent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri pathToFile = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), pathToFile);
                ImageView imageView = (ImageView) findViewById(R.id.restaurant_creation_add_photo);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onDoneButtonClick(View view) {
        Intent doneIntent = new Intent(view.getContext(), RestaurantMenuActivity.class);
        view.getContext().startActivity(doneIntent);
    }


}
