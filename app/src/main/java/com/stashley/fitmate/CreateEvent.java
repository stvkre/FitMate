package com.stashley.fitmate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


public class CreateEvent extends AppCompatActivity {

    public ImageView mSelectImage;

    private static final int GALLERY_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        mSelectImage = (ImageView) findViewById(R.id.imageSelect);

        mSelectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                if (galleryIntent.resolveActivity(getPackageManager()) !=null) {
                    startActivityForResult(galleryIntent, GALLERY_REQUEST);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            Uri imageUri = data.getData();

            mSelectImage.setImageURI(imageUri);
        }
    }
}
