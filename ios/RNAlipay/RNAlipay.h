#import "RCTLog.h"
#import "RCTConvert.h"
#import "RCTBridge.h"
#import "RCTUtils.h"
#import "RCTEventDispatcher.h"
#import "RCTBridgeModule.h"
#import <UIKit/UIKit.h>
#import <AlipaySDK/AlipaySDK.h>

@interface RNAlipay : NSObject <RCTBridgeModule>
+ (instancetype) shareInstance;

- (void)processOrderWithPaymentResult:(NSURL *)resultUrl;

@end
