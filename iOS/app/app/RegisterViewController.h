//
//  RegisterViewController.h
//  app
//
//  Created by Richard on 4/13/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CircularImageView.h"

@interface RegisterViewController : UIViewController <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UIScrollView *scrollView;
@property (weak, nonatomic) IBOutlet UIButton *cancelButton;
@property (weak, nonatomic) IBOutlet UIButton *saveButton;
@property (strong, nonatomic) CircularImageView *userImageView;
@property (weak, nonatomic) IBOutlet UITextField *firstName;
@property (weak, nonatomic) IBOutlet UITextField *lastName;
@property (weak, nonatomic) IBOutlet UITextField *emailAddress;
@property (weak, nonatomic) IBOutlet UITextField *username;
@property (weak, nonatomic) IBOutlet UITextField *password;

- (IBAction)dismissRegisterScreen:(id)sender;
- (IBAction)saveButtonPressed:(id)sender;

@end
