package com.tokyonth.english;

import android.app.Application;
import android.graphics.Typeface;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class initializeApp extends Application{

	//字体配置
	private Typeface typeface;
	private static initializeApp instance;
  
	@Override
	public void onCreate() {
		// 应用程序入口处调用,避免手机内存过小,杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
		// 注意：此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，请增加参数：SpeechConstant.FORCE_LOGIN+"=true"
		// 参数间使用“,”分隔。
		// 设置你申请的应用appid
		
		// 注意： appid 必须和下载的SDK保持一致，否则会出现10407错误
		StringBuffer param = new StringBuffer();
		param.append("appid=" + getString(R.string.app_id));
		param.append(",");
		// 设置使用v5+
		param.append(SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_MSC);
		SpeechUtility.createUtility(initializeApp.this, param.toString());

		//字体
		instance = (initializeApp) getApplicationContext();
		typeface = Typeface.createFromAsset(instance.getAssets(), "fonts/font.ttf");
		super.onCreate();
	}

	public static initializeApp getInstance() {
		return instance;
	}

	public Typeface getTypeface() {
		return typeface;
	}

	public void setTypeface(Typeface typeface) {
		this.typeface = typeface;
	}

}
