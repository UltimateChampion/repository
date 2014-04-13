//
//  FirstViewController.h
//  app
//
//  Created by Richard on 4/11/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FirstViewController : UIViewController
@property (weak, nonatomic) IBOutlet UIButton *signInButton;
@property (weak, nonatomic) IBOutlet UIButton *signUpButton;
- (IBAction)pushLoginViewController:(id)sender;
- (IBAction)pushSignUpViewController:(id)sender;

@end
