//
//  UserPictureView.h
//  app
//
//  Created by Richard on 3/24/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CircularImageView : UIView

@property (strong, nonatomic) UIImageView *userPictureView;
@property (strong, nonatomic) UIImage *userPicture;
@property (strong, nonatomic) UIColor *backingColor;

- (id)initWithFrame:(CGRect)frame picture:(UIImage*)userPicture;

@end
