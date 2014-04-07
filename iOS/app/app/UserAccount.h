//
//  UserAccount.h
//  app
//
//  Created by Richard on 3/24/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import <Parse/Parse.h>

@interface UserAccount : PFObject <PFSubclassing>

+ (NSString *)parseClassName;

@property (strong, nonatomic) NSString *accountName;
@property (strong, nonatomic) NSNumber *accountValue;
@property (strong, nonatomic) NSNumber *initialValue;

@end
