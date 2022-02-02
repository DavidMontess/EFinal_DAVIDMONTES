package com.idat.efinal;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class PerfilFragment extends Fragment {

    ImageView imagenProfile;
    ProgressDialog progressDialog;
    private static  final int CAMARA_REQUEST = 100;
    private static final int IMAGE_PICK_CAMARA_REQUEST=400;

    String camaraPermission[];
    Uri imageUri;
    String profileOrCoverImage;
    MaterialButton editImage;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //aparece un error aqui a la hora de querer traer el button y no me deja ejecutar el perfil para cambiar la imagen
        editImage.findViewById(R.id.edit_image);
        imagenProfile.findViewById(R.id.imagePerfil);
        progressDialog= new ProgressDialog(this.getContext());
        progressDialog.setCanceledOnTouchOutside(false);
        camaraPermission=new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("updating Profile Picture");
                profileOrCoverImage="image";
                showImagePicDialog();
            }
        });




    }




    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ){
        View view = inflater.inflate(R.layout.perfil_fragment,container,false);
        return view;


    }


    private void showImagePicDialog() {
        String options[]={"Camara","Gallery"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
        builder.setTitle("Pick image from");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    if(!checkCamaraPermission()){
                        requestCamaraPermission();
                    }else{
                        pickFromCamera();
                    }
                }
            }
        });
        builder.create().show();
    }

    private void pickFromCamera() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp_pic");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Temp description");
        imageUri=this.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
        Intent cameraIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(cameraIntent,IMAGE_PICK_CAMARA_REQUEST);

    }

    private void requestCamaraPermission() {
        requestPermissions(camaraPermission,CAMARA_REQUEST);
    }

    private boolean checkCamaraPermission() {
        boolean result= ContextCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1=ContextCompat.checkSelfPermission(this.getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }
}
