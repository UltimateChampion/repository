//
//  Transaction.h
//  app
//
//  Created by Richard on 3/24/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import <Parse/Parse.h>

@interface Transaction : PFObject <PFSubclassing>

+ (NSString *)parseClassName;

@property (strong, nonatomic) NSString *name;
@property (strong, nonatomic) NSDate *date;
@property (strong, nonatomic) NSNumber *value;

- (void)setTxnValue:(NSNumber *)value;
- (NSNumber *)getTxnValue;

@end
