//
//  UserPictureView.m
//  app
//
//  Created by Richard on 3/24/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import "CircularImageView.h"
#import <QuartzCore/QuartzCore.h>

@implementation CircularImageView

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        self.backgroundColor = [UIColor lightGrayColor];
        self.userPictureView = [[UIImageView alloc] initWithFrame:CGRectMake(frame.origin.x, frame.origin.y, frame.size.width-2, frame.size.height-2)];
        self.userPictureView.center = CGPointMake(self.bounds.size.width/2, self.bounds.size.height/2);
        self.userPictureView.layer.cornerRadius = roundf(self.userPictureView.frame.size.width/2);
        self.userPictureView.layer.masksToBounds = YES;
        self.layer.cornerRadius = roundf(frame.size.width/2);
        self.layer.masksToBounds = YES;
        CALayer *containerLayer = [CALayer layer];
//        containerLayer.shadowColor = [UIColor whiteColor].CGColor;
//        containerLayer.shadowRadius = 10.0f;
//        containerLayer.shadowOffset = CGSizeMake(0.0f, 5.0f);
//        containerLayer.shadowOpacity = 1.0f;
//        
        [containerLayer addSublayer:self.userPictureView.layer];
        [self.layer addSublayer:containerLayer];
    }
    
    return self;
}

- (id)initWithFrame:(CGRect)frame picture:(UIImage *)userPicture
{
    self = [self initWithFrame:frame];
    if (self) {
        [self setUserPicture:userPicture];
    }
    
    return self;
}

- (void)setUserPicture:(UIImage *)userPicture
{
    _userPicture = userPicture;
    [self.userPictureView setImage:userPicture];
}

@end
