package com.tokyonth.english.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tokyonth.english.adapter.VOAdapter;
import com.tokyonth.english.model.ItemVO;
import com.tokyonth.english.R;
import com.tokyonth.english.BaseActivity;
import com.tokyonth.english.ui.CartoonTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//import static com.tokyonth.english.base.SourcesConf.img;
import static com.tokyonth.english.base.SourcesConf.imgUrls;
import static com.tokyonth.english.base.SourcesConf.str_zh;

public class Exhibition extends BaseActivity {

    private List<ItemVO> mList = new ArrayList<>();

    @BindView(R.id.recycler_content)
    public RecyclerView recyclerView;
    @BindView(R.id.show_title)
    public CartoonTextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.effect_display);
        ButterKnife.bind(this);
        String name = getIntent().getStringExtra("name");
        Log.d("name",name);
        tv_title.setText(name);

        addData();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        final VOAdapter voAdapter = new VOAdapter(mList,this);
        recyclerView.setAdapter(voAdapter);
        voAdapter.setOnItemClickListener(new VOAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent();
                intent.setClass(Exhibition.this, EffectDisplay.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });

    }

    private void addData() {
        int index = 0;
            for (String s : str_zh) {
                String path = imgUrls[index++];
                ItemVO itemVO = new ItemVO(path, s);
                mList.add(itemVO);
            }
    }

}
