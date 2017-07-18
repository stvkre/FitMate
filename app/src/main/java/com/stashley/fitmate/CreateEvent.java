package com.stashley.fitmate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class CreateEvent extends AppCompatActivity {

    private ImageView mSelectImage;
    private EditText mCreateEventTitle;
    private EditText mCreateEventDesc;

    private Button mSubmitBtn;

    private Uri mImageUri = null;



    private static final int GALLERY_REQUEST = 1;

    private StorageReference mStorage;

    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // getting firebase permissions to store data

        mStorage = FirebaseStorage.getInstance().getReference();

        mSelectImage = (ImageView) findViewById(R.id.imageSelect);
        mCreateEventTitle = (EditText) findViewById(R.id.titleField);
        mCreateEventDesc = (EditText) findViewById(R.id.descField);

        mSubmitBtn = (Button) findViewById(R.id.submitBtn);

        mProgress = new ProgressDialog(this);

        mSelectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                // app accesing device gallery to choose image

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                if (galleryIntent.resolveActivity(getPackageManager()) !=null) {
                    startActivityForResult(galleryIntent, GALLERY_REQUEST);
                }

            }
        });

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPosting();
            }
        });
    }

    // uploading data on the database

    private void startPosting() {

        // progress message to show upload status

        mProgress.setMessage("Posting to Events...");
        mProgress.show();

        String title_val = mCreateEventTitle.getText().toString().trim();
        String desc_val = mCreateEventDesc.getText().toString().trim();

        if (!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val) && mImageUri !=null){

            StorageReference filepath = mStorage.child("Event_Images").child(mImageUri.getLastPathSegment());
            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    mProgress.dismiss();
                }
            });

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
           mImageUri = data.getData();

            mSelectImage.setImageURI(mImageUri);
        }
    }
}
