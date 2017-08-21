package com.example.hardware;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends AppCompatActivity {

    private Button btnTakePhoto;
    private ImageView imgPreview;

    private static final int CAPTURAR_IMAGEM = 1;
    private static final int PERMISSAO_USAR_CAMERA = 12;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
        imgPreview = (ImageView) findViewById(R.id.imgPreview);

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // verficando se o app já tem permissao para usa a camera
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSAO_USAR_CAMERA);
                } else {
                    // permissao já foi dada
                    capturarFoto();
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSAO_USAR_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                capturarFoto();
            } else {
                Toast.makeText(this, "É necessário dar permissão para o aplicativo utilizar a câmera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void capturarFoto(){
        // abrindo activity da camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURAR_IMAGEM);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURAR_IMAGEM) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imgPreview.setImageBitmap(imageBitmap);
            } else {
                Toast.makeText(this, "Algum problema ao capturar imagem", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
