//
//  Transaction.m
//  app
//
//  Created by Richard on 3/24/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import "Transaction.h"
#import <Parse/PFObject+Subclass.h>

@implementation Transaction

@dynamic name;
@dynamic date;
@dynamic value;

+ (NSString *)parseClassName
{
    return @"Transaction";
}

- (id)initWithName:(NSString *)name date:(NSDate *)date value:(NSNumber *)value
{
    self = [super init];
    if (self) {
        self.name = name;
        self.date = date;
        self.value = value;
    }
    
    return self;
}

@end
