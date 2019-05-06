package com.tokyonth.english.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.tokyonth.english.BaseActivity;
import com.tokyonth.english.adapter.CardPageAdapter;
import com.tokyonth.english.R;
import com.tokyonth.english.ui.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WordClass extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_left)
    public Button btn_left;
    @BindView(R.id.btn_right)
    public Button btn_right;
    @BindView(R.id.vp)
    public ViewPager vp;

    private View BasicsCard;
    private View LifeCard;
    private View ScienceCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_class);
        ButterKnife.bind(this);
        setVP();
        initView();
    }

    private void initView() {
        ArrayList<Integer> viewIds = new ArrayList<>();
        viewIds.add(R.id.number_card);
        viewIds.add(R.id.organ_card);
        viewIds.add(R.id.animal_card);
        viewIds.add(R.id.color_card);
        setViewClickListener(viewIds, BasicsCard);

        ArrayList<Integer> viewIds0 = new ArrayList<>();
        viewIds0.add(R.id.vehicle_card);
        viewIds0.add(R.id.fruits_card);
        viewIds0.add(R.id.vegetables_card);
        viewIds0.add(R.id.weather_card);
        setViewClickListener(viewIds0, LifeCard);

        ArrayList<Integer> viewIds1 = new ArrayList<>();
        viewIds1.add(R.id.chemistry_card);
        viewIds1.add(R.id.physics_card);
        viewIds1.add(R.id.biology_card);
        setViewClickListener(viewIds1, ScienceCard);
    }

    public void setViewClickListener(ArrayList<Integer> viewIds, View rootView) {
        for (int viewId : viewIds) {
            rootView.findViewById(viewId).setOnClickListener(this);
        }
    }

    private void setVP() {
        LayoutInflater inflater = getLayoutInflater();
        LifeCard = inflater.inflate(R.layout.life_card,null);
        BasicsCard = inflater.inflate(R.layout.basics_card, null);
        ScienceCard = inflater.inflate(R.layout.science_card, null);
        ArrayList<View> listView = new ArrayList<>();
        listView.add(BasicsCard);
        listView.add(LifeCard);
        listView.add(ScienceCard);
        CardPageAdapter cardPageAdapter = new CardPageAdapter(this, listView);
        vp.setPageTransformer(false,new ZoomOutPageTransformer());
        vp.setAdapter(cardPageAdapter);
    }

    @OnClick(R.id.btn_right)
    public void to_Right() {
        int i = vp.getCurrentItem();
        vp.setCurrentItem(i + 1);
    }

    @OnClick(R.id.btn_left)
    public void to_left() {
        int i = vp.getCurrentItem();
        vp.setCurrentItem(i - 1);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.number_card:
                intent.putExtra("name","数字");
                break;
            case R.id.animal_card:
                intent.putExtra("name","动物");
                break;
            case R.id.color_card:
                intent.putExtra("name","颜色");
                break;
            case R.id.organ_card:
                intent.putExtra("name","器官");
                break;

            case R.id.vehicle_card:
                intent.putExtra("name","交通工具");
                break;
            case R.id.fruits_card:
                intent.putExtra("name","水果");
                break;
            case R.id.vegetables_card:
                intent.putExtra("name","蔬菜");
                break;
            case R.id.weather_card:
                intent.putExtra("name","天气");
                break;

            case R.id.chemistry_card:
                intent.putExtra("name","化学");
                break;
            case R.id.physics_card:
                intent.putExtra("name","物理");
                break;
            case R.id.biology_card:
                intent.putExtra("name","生物");
                break;
        }
        intent.setClass(WordClass.this, Exhibition.class);
        startActivity(intent);
    }
}

