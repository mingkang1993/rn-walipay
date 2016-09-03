#import "Order.h"

@implementation Order

- (NSString *)description {

  NSMutableString * discription = [NSMutableString string];

  if (self.payInfo) {
    [discription appendFormat:@"payInfo=\"%@\"", self.payInfo];
  }
  if (self.appSchemeIOS) {
    [discription appendFormat:@"appSchemeIOS=\"%@\"", self.appSchemeIOS];
  }

  return discription;
}


@end
