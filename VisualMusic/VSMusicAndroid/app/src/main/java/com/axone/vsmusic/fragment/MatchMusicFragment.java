package com.axone.vsmusic.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.axone.vsmusic.R;
import com.axone.vsmusic.activity.WebMusicActivity;
import com.axone.vsmusic.util.UtilFunction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 秋水 on 2017/9/12.
 */

public class MatchMusicFragment extends Fragment {

    private ImageButton bt_photo;
    private ImageButton bt_album;

    private final  int resultFromCamera = 1;
    private final int resultFromAlbum = 2;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.viewpager_matchmusic, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bt_photo = (ImageButton) view.findViewById(R.id.match_photo);
        bt_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

        bt_album = (ImageButton) view.findViewById(R.id.match_album);
        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case resultFromCamera:
                getResultFromCamera(resultCode, data);
                break;
            case resultFromAlbum:
                getResultFromAlbum(resultCode, data);
                break;
            default:
                break;
        }


    }

    private void getResultFromCamera(int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK) {
            String sdStatus = Environment.getExternalStorageState();
            // 检测sd是否可用
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
                Log.i("TestFile",
                        "SD card is not avaiable/writeable right now.");
                return;
            }
            //获取当前时间
            SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyyMMddHHmmss");
            Date curDate =  new Date(System.currentTimeMillis());

            String   name   =   formatter.format(curDate) + ".jpg";
            Bundle bundle = data.getExtras();
            // 获取相机返回的数据，并转换为Bitmap图片格式
            Bitmap bitmap = (Bitmap) bundle.get("data");

            FileOutputStream fos = null;

            String dir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            System.out.println("本地文件目录：" + getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());

            String fileName = dir + "/"+name;
            System.out.println("文件路径：" + dir);
            try {
                fos = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 把数据写入文件

                startActivity(name, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void getResultFromAlbum(int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK) {
            Uri imgUri= data.getData();
            System.out.println("图片配置信息：");
            System.out.println(imgUri.toString());
            startActivity(null, UtilFunction.getPath(getContext(), imgUri));
        }

    }

    private void startActivity(String name, String location){

        Intent intent = new Intent();
        intent.setClass(getContext(), WebMusicActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("location", location);
        intent.putExtra("load", true);
        this.startActivity(intent);
    }
}
