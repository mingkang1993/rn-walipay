# rn-walipay
react-native 支付宝手机支付模块

# 安装
##iOS
1. `npm install rn-alipay --save`
2. 去官方下ios android 包,放入 android/libs下面
3. 在XCode中右击项目的 `Libraries` 文件夹 ➜ `Add Files to`
4. 进入 `node_modules` ➜ `rn-alipay` ➜ `ios` ➜ 选择 `RNAlipay.xcodeproj`
5. 展开`RNAlipay.xcodeproj`➜ `Products`➜ 添加 `libRNAlipay.a` 到`Build Phases -> Link Binary With Libraries`
6. 在`Build Phases`选项卡的`Link Binary With Libraries`中，点击“+”号增加以下依赖：<img title="iOS" src="https://github.com/mingkang1993/react-native-alipay/blob/master/assets/0.jpg">
7. 将`RNAlipay.xcodeproj`下`AlipaySDK.framework`、`libssl.a`、`libcrypto.a`文件拖入复制到项目文件夹下：<img title="iOS" src="https://github.com/mingkang1993/react-native-alipay/blob/master/assets/1.jpg">
8. 编译运行


##Android
* `npm install rn-alipay --save`
```java
// file: android/settings.gradle
...
include ':rn-alipay'  // <- add
project(':rn-alipay').projectDir = new File(settingsDir, '../node_modules/rn-alipay/android')  // <- add
```

```java
// file: android/app/build.gradle
...
dependencies {
		...
		compile project(':rn-alipay')  // <- add
}
```
* 注册模块
* 对于 react-native 版本低于 0.19.0 (use cat ./node_modules/react-native/package.json | grep version)
```java
// file: MainActivity.java
import com.alipay.RNAlipayPackage;  // <- import

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {

  ...

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mReactRootView = new ReactRootView(this);

    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      .setBundleAssetName("index.android.bundle")
      .setJSMainModuleName("index.android")
      .addPackage(new MainReactPackage())
      .addPackage(new RNAlipayPackage(this))      // <- add package
      .setUseDeveloperSupport(BuildConfig.DEBUG)
      .setInitialLifecycleState(LifecycleState.RESUMED)
      .build();

    mReactRootView.startReactApplication(mReactInstanceManager, "ExampleRN", null);

    setContentView(mReactRootView);
  }

  ...

}
```
* 对于 react-native 0.19.0及以上版本
```java
// file: MainActivity.java
	...
import com.alipay.RNAlipayPackage;//<- import package

public class MainActivity extends ReactActivity {

   /**
   * A list of packages used by the app. If the app uses additional views
   * or modules besides the default ones, add more packages here.
   */
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
            new MainReactPackage(), //<- Add comma
            new RNAlipayPackage(this) //<- Add package
        );
    }
...
}
```

##使用说明
1. 导入模块
```javascript
const Alipay = require('rn-alipay');
```
2. 基本使用
```javascript
const data = {
    payInfo,
    appSchemeIOS: 'testapp20', //应用注册scheme,Info.plist定义URL types
};
Alipay.pay(data).then((msg) => {
    console.log(msg);
}, (e) => {
    console.log(e);
});
```
##参考支付宝文档
* [支付宝开放平台移动支付文档](https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.OuUIpb&treeId=59&articleId=103563&docType=1)
