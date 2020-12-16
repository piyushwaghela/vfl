package com.example.vocalforlocal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.blogspot.atifsoftwares.circularimageview.CircularImageView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


public class applicationforworker extends AppCompatActivity {
    EditText name,phonenumber,adharnumber,profe,sd,location;
    CircularImageView profile;
    Button reg;

    private Uri im;

    private static  final  int CAMERA_REQ_CODE = 100;
    private static  final  int STORAGE_REQ_CODE = 101;

    private static  final  int IMAGE_PICK_CAMERA_CODE = 102;
    private static  final  int IMAGE_PICK_GALL_CODE = 103;

    private String[] cameraP;
    private String[] storageP;



    private Uri imageUri;

    private String dname, dmobile,dacard,dprofe,dsd,dlocation;

    private MydbHelper mydbhelper;

    AwesomeValidation awesomeValidation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicationforworker);
        name = findViewById(R.id.name);
        phonenumber = findViewById(R.id.phonenumber);
        adharnumber = findViewById(R.id.adharcard);
        profe = findViewById(R.id.profe);
        sd =findViewById(R.id.sd);
        location = findViewById(R.id.location);
        profile = findViewById(R.id.profile);
        reg = findViewById(R.id.reg);



        cameraP =new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        storageP =new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};






        //validation
        awesomeValidation= new AwesomeValidation(ValidationStyle.BASIC);


        awesomeValidation.addValidation(this,R.id.name,
                RegexTemplate.NOT_EMPTY,R.string.invalid_username);

        awesomeValidation.addValidation(this,R.id.phonenumber,
                "[5-9]{1}[0-9]{9}$",R.string.invalid_mobilenumber);

        awesomeValidation.addValidation(this,R.id.adharcard,
                RegexTemplate.NOT_EMPTY,R.string.invalid_adharnumbers);

        awesomeValidation.addValidation(this,R.id.profe,
                RegexTemplate.NOT_EMPTY,R.string.invalid_profession);

        awesomeValidation.addValidation(this,R.id.sd,
                RegexTemplate.NOT_EMPTY,R.string.invalid_description);

        awesomeValidation.addValidation(this,R.id.location,
                RegexTemplate.NOT_EMPTY,R.string.invalid_location);

        mydbhelper = new MydbHelper(this);



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {

                    inputdata();
                    Intent intent = new Intent(getApplicationContext(),logsin.class);
                    startActivity(intent);
                    finish();

                }
            }


        });




        profile.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {

                imagepickdialog();

            }
        });


    }

    private void inputdata() {

        dname = ""+name.getText().toString().trim();
        dmobile = ""+phonenumber.getText().toString().trim();
        dacard = ""+adharnumber.getText().toString().trim();
        dprofe = ""+profe.getText().toString().trim();
        dsd = ""+sd.getText().toString().trim();
        dlocation = ""+location.getText().toString().trim();

        long id = mydbhelper.insertrecord(
                ""+dname,
                ""+imageUri,
                ""+dmobile,
                ""+dacard,
                ""+dprofe,
                ""+dlocation,
                ""+dsd
        );





        Toast.makeText(this,"Work Profile Created Successful!!",Toast.LENGTH_SHORT).show();
        name.setText("");
        phonenumber.setText("");
        adharnumber.setText("");
        profe.setText("");
        sd.setText("");
        location.setText("");
        profile.setImageResource(R.drawable.pf);


    }



    private void imagepickdialog() {

        String[] option = {"Camera","Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Image From");

        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which==0){
                    if(!checkcamerapermission()){
                        reqcamper();
                    }
                    else {

                        pickfromcamera();

                    }

                }
                else if (which==1){

                    if(!checkstoragepermissin()){
                        reqstoreper();
                    }
                    else {
                                pickfromgall();

                    }

                }

            }
        });

        builder.create().show();



    }

    private void pickfromgall() {

        Intent gall = new Intent(Intent.ACTION_PICK);
        gall.setType("image/*");
        startActivityForResult(gall,IMAGE_PICK_GALL_CODE);

    }

    private void pickfromcamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Image_Title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image_description");

        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent,IMAGE_PICK_CAMERA_CODE);
    }



    private boolean checkstoragepermissin(){

        boolean res = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return res;


    }

    private void reqstoreper(){
        ActivityCompat.requestPermissions(this,storageP,STORAGE_REQ_CODE);

    }

    private boolean checkcamerapermission(){

        boolean res = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean res1 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return res && res1;

    }

    private void reqcamper(){
        ActivityCompat.requestPermissions(this,cameraP,CAMERA_REQ_CODE);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case CAMERA_REQ_CODE:{

                if(grantResults.length>0){

                    boolean cameraaccp = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageaccp = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if(cameraaccp && storageaccp){
                        pickfromcamera();
                    }else {
                        Toast.makeText(this,"Camera and Storage Permission required",Toast.LENGTH_SHORT).show();


                    }

                }
            }
            break;
            case STORAGE_REQ_CODE:{

                if(grantResults.length>0){

                    boolean storageaccp = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(storageaccp){
                        pickfromgall();
                    }else {

                        Toast.makeText(this,"Storage permisiion required",Toast.LENGTH_SHORT).show();

                    }

                }
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_PICK_GALL_CODE){

                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1).start(this);
            }

            else if(requestCode == IMAGE_PICK_CAMERA_CODE){

                CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1,1).start(this);


            }
            else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){

                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if(resultCode == RESULT_OK){
                    Uri resuri = result.getUri();

                    imageUri = resuri;

                    profile.setImageURI(resuri);
                }

                else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){

                    Exception error = result.getError();

                    Toast.makeText(this,""+error,Toast.LENGTH_SHORT).show();




                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}













