package com.tokyonth.english;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.tokyonth.english.activity.Dialogue;
import com.tokyonth.english.activity.WordClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnglishActivity extends BaseActivity {

    @BindView(R.id.word_card)
    public ImageView iv_word;
    @BindView(R.id.dialogue_card)
    public ImageView iv_dialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_english);
        ButterKnife.bind(this);
        GetPermissions();
    }

    @OnClick(R.id.word_card)
    public void Word_Card() {
        Intent intent = new Intent();
        intent.setClass(EnglishActivity.this, WordClass.class);
        startActivity(intent);
    }

    @OnClick(R.id.dialogue_card)
    public void Dialogue_Card() {
        Intent intent = new Intent();
        intent.setClass(EnglishActivity.this, Dialogue.class);
        startActivity(intent);
    }

}
