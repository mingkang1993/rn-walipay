#import "RNAlipay.h"
#import "Order.h"
#import "DataSigner.h"
#import <AlipaySDK/AlipaySDK.h>

@implementation RNAlipay

RCT_EXPORT_MODULE();

RCT_REMAP_METHOD(pay, options:(NSDictionary *)options
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject)
{
    NSString *appScheme = [options objectForKey:@"appSchemeIOS"];

    //将签名成功字符串格式化为订单字符串,请严格按照该格式
    NSString *orderString = [options objectForKey:@"payInfo"];
    if (orderString != nil) {

        NSLog(@"orderString = %@", orderString);

        //resolve(@"支付成功!");

        [[AlipaySDK defaultService] payOrder:orderString fromScheme:appScheme callback:^(NSDictionary *resultDic) {
            NSLog(@"reslut = %@",resultDic);

            NSLog(@"orderString = %@", @"支付成功啦啦啦啦！");
            resolve(@"支付成功!");
        }];
        return;
    }

    NSError *error = nil;
    reject(@"支付失败", @"参数错误", error);

}

@end