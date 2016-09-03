package com.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;


import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.Promise;

import com.alipay.sdk.app.PayTask;

public class RNAlipayModule extends ReactContextBaseJavaModule {
	private final ReactApplicationContext mReactContext;

	public RNAlipayModule(ReactApplicationContext reactContext) {
		super(reactContext);
		mReactContext = reactContext;
  	}
  	
	@Override
  	public String getName() {
    	return "RNAlipay";
  	}

  	@ReactMethod
  	public void pay(ReadableMap options, Promise promise) {

        String payInfo = options.getString("payInfo");

		if (TextUtils.isEmpty(payInfo)) {

		    promise.reject("需要配置payInfo,传给支付宝的参数");

			return;
		}

        System.out.println(payInfo);

		PayTask alipay = new PayTask(getCurrentActivity());
		String result = alipay.pay(payInfo);
		//cb.invoke(result);
		promise.resolve(result);
    }
}