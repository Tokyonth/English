package com.tokyonth.english;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.tokyonth.english.utils.CopyAssetsFile;
import com.tokyonth.english.utils.UnZipFile;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadActivity extends BaseActivity {

    @BindView(R.id.tv_version)
    public TextView tv_version;
    @BindView(R.id.tv_app_name)
    public TextView tv_app_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_load);
        ButterKnife.bind(this);
        try {
            tv_version.append(getVersionName());
            new CopyAssetsFile(this,"sources.zip");
            new UnZipFile(getFilesDir() + "/sources.zip", getFilesDir().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(LoadActivity.this,EnglishActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);

    }

    private String getVersionName() throws Exception {
        // 获取PackageManager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
        return packInfo.versionName;
    }

}
