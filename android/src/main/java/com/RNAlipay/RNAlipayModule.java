package com.alipay;

import android.text.TextUtils;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Promise;
import com.facebook.react.modules.core.DeviceEventManagerModule;

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

  	private void sendEvent(ReactContext reactContext,
                           String eventName,
						   Boolean params) {
    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
         .emit(eventName, params);
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
		sendEvent(mReactContext, "rnAlipayCallback", true);
    }
}