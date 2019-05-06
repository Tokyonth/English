package com.tokyonth.english.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CardPageAdapter extends PagerAdapter {

    protected Context context;
    private List<View> list_views;

    public CardPageAdapter(Context context, List<View> list_views) {
        this.context = context;
        this.list_views = new ArrayList<>();
        this.list_views = list_views;
    }

    @Override
    public int getCount() {
        return list_views.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list_views.get(position));
        return list_views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return  view == object;
    }

}

