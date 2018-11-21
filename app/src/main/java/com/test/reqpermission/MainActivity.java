package com.test.reqpermission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    public static int PHOTO_PERMISS = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
    }

    /**
     * 请求相关的权限
     */
    private void requestPhotoPermiss(){
        PermissionGen.with(MainActivity.this)
                .addRequestCode(PHOTO_PERMISS)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = Constant.SUCCESSCODE)
    public void requestPhotoSuccess(){
        //成功之后的处理
        //.......
    }

    @PermissionFail(requestCode = Constant.SUCCESSCODE)
    public void requestPhotoFail(){
        //失败之后的处理，我一般是跳到设置界面
        goToSetting(mContext);
    }
    /***
     * 去设置界面
     */
    public void goToSetting(Context context){
        //go to setting view
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }




}
