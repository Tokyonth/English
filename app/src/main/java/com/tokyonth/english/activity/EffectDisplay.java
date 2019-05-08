package com.tokyonth.english.activity;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.tokyonth.english.R;
import com.tokyonth.english.overlay.SimpleOverlayAdapter;
import com.tokyonth.english.BaseActivity;
import com.tokyonth.english.translate.TransApi;
import com.tokyonth.english.translate.TranslateResult;
import com.tokyonth.english.ui.CartoonTextView;
import com.tokyonth.english.voice.TtsUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tokyonth.english.source.SourcesConf.WordForZh;

public class EffectDisplay extends BaseActivity {

    @BindView(R.id.music_en)
    public CartoonTextView tv_en;
    @BindView(R.id.music_zh)
    public CartoonTextView tv_zh;
    @BindView(R.id.btn_l)
    public Button btn_l;
    @BindView(R.id.btn_r)
    public Button btn_r;
    @BindView(R.id.vp)
    public ViewPager vp;

    private List<String> text_zh_list = new ArrayList<>();
    private Handler handler = new Handler();

    private int VpCurrentItem;
    private String zh_text;
    private String en_text;
    private TtsUtils tts;
    private String[] image_url;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibition_word);
        ButterKnife.bind(this);
        VpCurrentItem = getIntent().getIntExtra("id",0);
        image_url = getIntent().getStringArrayExtra("image_url");
        name = getIntent().getStringExtra("name");
        tts = new TtsUtils(this);
        setVp();
    }

    @OnClick(R.id.btn_l)
    public void Btn_L() {
        int i = vp.getCurrentItem();
        vp.setCurrentItem(i - 1);
    }

    @OnClick(R.id.btn_r)
    public void Btn_R() {
        int i = vp.getCurrentItem();
        vp.setCurrentItem(i + 1);
    }

    @OnClick(R.id.music_en)
    public void Music_en() {
        en_text = tv_en.getText().toString().trim();
        tts.TtsPlay(en_text);
        //英文资源
        //String s = WordsStrings.musicPath("");
       // MediaManager.playSound(s, null);
    }

    @OnClick(R.id.music_zh)
    public void Music_zh() {
        tts.TtsPlay(zh_text);
      //  String s = WordsStrings.musicPath(zh);
       // MediaManager.playSound(s, null);
    }

    private void TranslateForZh(final String query) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String resultJson = new TransApi().getTransResult(query, "auto", "en");
                Log.d("返回结果",resultJson);

                Gson gson = new Gson();
                TranslateResult translateResult = gson.fromJson(resultJson, TranslateResult.class);
                final List<TranslateResult.TransResultBean> trans_result = translateResult.getTrans_result();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (TranslateResult.TransResultBean s : trans_result) {
                            tv_en.setText(s.getDst());
                            //填充翻译内容
                        }
                    }
                });
            }
        }).start();
    }

    private void setVp() {
        Collections.addAll(text_zh_list, WordForZh(name));

        SimpleOverlayAdapter adapter = new SimpleOverlayAdapter(this);
        adapter.setImgUrlsAndBindViewPager(vp, image_url, 3); //3为卡片叠层可是层
        vp.setAdapter(adapter);
        vp.setCurrentItem(VpCurrentItem);

        tv_zh.setText(zh_text = text_zh_list.get(VpCurrentItem));
        //通过百度翻译中文填充 英文单词
        //此处在线程中处理en_text
        TranslateForZh(zh_text);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               // Log.d("长度",String.valueOf(img.length));
               // Log.d("滑动",String.valueOf(position));
                if (position == image_url.length - 1) {
                    btn_r.setVisibility(View.INVISIBLE);
                } else if (position == 0) {
                    btn_l.setVisibility(View.INVISIBLE);
                } else {
                    btn_r.setVisibility(View.VISIBLE);
                    btn_l.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {
                tv_zh.setText(zh_text = text_zh_list.get(position));
                //通过百度翻译中文填充 英文单词
                TranslateForZh(zh_text);
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

    }

}
