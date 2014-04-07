//
//  UserAccount.m
//  app
//
//  Created by Richard on 3/24/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import "UserAccount.h"
#import <Parse/PFObject+Subclass.h>

@implementation UserAccount

@dynamic accountName;
@dynamic accountValue;
@dynamic initialValue;

+ (NSString *)parseClassName
{
    return @"Account";
}

-(id)initWithAccountName:(NSString *)name initialValue:(NSNumber *)value
{
    self = [super init];
    if (self) {
        self.accountName = name;
        self.initialValue = value;
        self.accountValue = value;
    }
    
    return self;
}



@end
