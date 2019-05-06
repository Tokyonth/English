package com.tokyonth.english.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tokyonth.english.R;

import java.util.List;

public class WordsListAdapter extends PagerAdapter {

        private Context mContext;
        private List<Integer> mData;

        public WordsListAdapter(Context context ,List<Integer> list) {
            mContext = context;
            mData = list;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(mContext, R.layout.words_item,null);
            ImageView iv = (ImageView) view.findViewById(R.id.words_image);
            iv.setImageResource(mData.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

}
