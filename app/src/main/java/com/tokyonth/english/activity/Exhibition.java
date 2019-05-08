package com.tokyonth.english.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.tokyonth.english.adapter.VOAdapter;
import com.tokyonth.english.model.ItemVO;
import com.tokyonth.english.R;
import com.tokyonth.english.BaseActivity;
import com.tokyonth.english.pic.ImageTool;
import com.tokyonth.english.ui.CartoonTextView;
import com.tokyonth.english.ui.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tokyonth.english.source.SourcesConf.WordForZh;

public class Exhibition extends BaseActivity {

    private List<ItemVO> mList = new ArrayList<>();
    private VOAdapter voAdapter;
   // private ProgressDialog progressDialog;
    private String[] str_url;
    private String name;
    private CustomDialog customDialog;

    @BindView(R.id.recycler_content)
    public RecyclerView recyclerView;
    @BindView(R.id.show_title)
    public CartoonTextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.effect_display);
        ButterKnife.bind(this);
        name = getIntent().getStringExtra("name");
        Log.d("name",name);
        tv_title.setText(name);

        addData();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        voAdapter = new VOAdapter(mList,this);
        recyclerView.setAdapter(voAdapter);
        voAdapter.setOnItemClickListener(new VOAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent();
                intent.setClass(Exhibition.this, EffectDisplay.class);
                intent.putExtra("id",position);
                intent.putExtra("name",name);
                intent.putExtra("image_url",str_url);
                startActivity(intent);
            }
        });

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 2:
                    //progressDialog.dismiss();
                    customDialog.dismiss();
                    voAdapter.notifyDataSetChanged();
                    str_url = (String[]) msg.obj;
                    Toast.makeText(Exhibition.this,"爬虫完成",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void addData() {
        customDialog = new CustomDialog(this);
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.setTitle("提示");
        customDialog.setMessage("请稍候……");
        customDialog.show();
        //progressDialog = ProgressDialog.show(Exhibition.this,null, "处理中，请稍候……");
        new Thread() {
            @Override
            public void run() {
                ImageTool imageTool = new ImageTool();
                String[] imageList = imageTool.GetImageList(WordForZh(name));

                int index = 0;
                String path = null;
                for (String s : WordForZh(name)) {
                    path = imageList[index++];
                    ItemVO itemVO = new ItemVO(path, s);
                    mList.add(itemVO);
                }
                Log.d("爬虫",path);

                Message msg = Message.obtain();
                msg.what = 2;
                msg.obj = imageList;
                handler.sendMessage(msg);
            }
        }.start();
    }

}
