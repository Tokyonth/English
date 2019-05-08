package com.tokyonth.english.activity;

import android.os.Bundle;

import com.tokyonth.english.R;
import com.tokyonth.english.BaseActivity;
import com.tokyonth.english.voice.TtsUtils;
import com.tokyonth.english.widget.BubbleLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dialogue extends BaseActivity {

    @BindView(R.id.tv_an)
    BubbleLayout tv_an;
    @BindView(R.id.tv_qu)
    BubbleLayout tv_qu;

    private TtsUtils tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_dialogue);
        ButterKnife.bind(this);
        tts = new TtsUtils(this);
    }

    @OnClick(R.id.tv_qu)
    public void TV_QU (){
        tts.TtsPlay("Hello World!  Hello World!");
    }

    @OnClick(R.id.tv_an)
    public void TV_AN (){
        tts.TtsPlay("Hello World!  Hello World!");
    }

}
