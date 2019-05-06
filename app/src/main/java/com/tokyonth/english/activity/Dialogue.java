package com.tokyonth.english.activity;

import android.os.Bundle;

import com.tokyonth.english.R;
import com.tokyonth.english.BaseActivity;
import com.tokyonth.english.utils.MediaManager;
import com.tokyonth.english.widget.BubbleLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dialogue extends BaseActivity {

    @BindView(R.id.tv_an)
    BubbleLayout tv_an;
    @BindView(R.id.tv_qu)
    BubbleLayout tv_qu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_dialogue);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_qu)
    public void TV_QU (){
        String url = "http://tts.baidu.com/text2audio?lan=zh&ie=UTF-8&text=Hello World!  Hello World!";
        MediaManager.playSound(url, null);
    }

    @OnClick(R.id.tv_an)
    public void TV_AN (){
        String url = "http://tts.baidu.com/text2audio?lan=zh&ie=UTF-8&text=Hello World!  Hello World!";
        MediaManager.playSound(url, null);
    }

}
