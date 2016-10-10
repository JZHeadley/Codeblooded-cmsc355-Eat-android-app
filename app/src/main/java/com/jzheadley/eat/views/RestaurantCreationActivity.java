package com.jzheadley.eat.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.jzheadley.eat.R;
import com.jzheadley.eat.models.services.RestaurantService;
import com.jzheadley.eat.presenters.RestaurantCreationPresenter;
import com.jzheadley.eat.views.layoutViews.CheckBoxGroupView;

import java.io.IOException;

public class RestaurantCreationActivity extends BaseActivity {
    private RestaurantCreationPresenter restaurantCreationPresenter;
    private RestaurantService restaurantService;
    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_restaurant);
        restaurantService = new RestaurantService();
        restaurantCreationPresenter = new RestaurantCreationPresenter(this, restaurantService);
        createFoodTypeCheckBoxes();
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
}
