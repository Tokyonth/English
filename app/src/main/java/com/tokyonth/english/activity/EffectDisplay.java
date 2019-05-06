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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tokyonth.english.base.SourcesConf.imgUrls;
import static com.tokyonth.english.base.SourcesConf.str_zh;

public class EffectDisplay extends BaseActivity {

    @BindView(R.id.music_en)
    public CartoonTextView music_en;
    @BindView(R.id.music_zh)
    public CartoonTextView music_zh;
    @BindView(R.id.btn_l)
    public Button btn_l;
    @BindView(R.id.btn_r)
    public Button btn_r;
    @BindView(R.id.vp)
    public ViewPager vp;

    private List<String> text_zh = new ArrayList<>();

    private int VpCurrentItem;
    private Handler handler = new Handler();
    private String zh;
    private String en;
    private TtsUtils tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibition_word);
        ButterKnife.bind(this);
        VpCurrentItem = getIntent().getIntExtra("id",0);
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
        en = music_en.getText().toString().trim();
        tts.TtsPlay(en);
        ////英文资源
        //String s = WordsStrings.musicPath("");
       // MediaManager.playSound(s, null);
    }

    @OnClick(R.id.music_zh)
    public void Music_zh() {
        tts.TtsPlay(zh);
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
                            music_en.setText(s.getDst());
                            //填充翻译内容
                        }
                    }
                });
            }
        }).start();
    }

    private void setVp() {
        //int index = 0;
        for (String str : str_zh) {
            //image_path.add(i);
            text_zh.add(str);
        }

        SimpleOverlayAdapter adapter = new SimpleOverlayAdapter(this);
        adapter.setImgUrlsAndBindViewPager(vp, imgUrls, 3); //3为卡片叠层可是层
        vp.setAdapter(adapter);
        vp.setCurrentItem(VpCurrentItem);

        music_zh.setText(zh = text_zh.get(VpCurrentItem));
        //通过百度翻译中文填充 英文单词
        TranslateForZh(zh);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               // Log.d("长度",String.valueOf(img.length));
               // Log.d("滑动",String.valueOf(position));
                if (position == imgUrls.length - 1) {
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
                music_zh.setText(zh = text_zh.get(position));
                //通过百度翻译中文填充 英文单词
                TranslateForZh(zh);
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

    }

}
