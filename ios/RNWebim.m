#import "RNWebim.h"
#import <React/RCTLog.h>

@implementation RNWebim

    RCT_EXPORT_MODULE();

    RCT_EXPORT_METHOD(init:(NSString *)name)
    {
        RCTLogInfo(@"Pretending %@ ", name);
    }

@end
