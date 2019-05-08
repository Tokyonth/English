package com.tokyonth.english;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.tokyonth.english.pic.ImageTool;
import com.tokyonth.english.utils.CopyAssetsFile;
import com.tokyonth.english.utils.UnZipFile;

import java.util.ArrayList;

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

        /*new Thread() {
            @Override
            public void run() {
                String[] str_zh = {"苹果","婴儿","狗","妈妈","火车","老师"};
                ImageTool imageTool = new ImageTool();
                ArrayList<String> list;
                list = imageTool.GetImageList(str_zh);

                for (int i = 0; i < list.size() ; i++) {
                           System.out.println("打印" + list.get(i));
                     }

                Message msg = Message.obtain();
                //msg.obj = list;
                msg.what = 2;
                handler.sendMessage(msg);
            }
        }.start();*/

    }

    /*Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 2:
                    System.out.print(msg.obj);
                    Toast.makeText(LoadActivity.this,"爬虫完成",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };*/

    private String getVersionName() throws Exception {
        // 获取PackageManager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
        return packInfo.versionName;
    }

}
