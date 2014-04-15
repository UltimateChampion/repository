//
//  AccountCreateViewController.h
//  app
//
//  Created by Richard on 4/15/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UserAccount.h"

@protocol AccountCreationDelegate <NSObject>

- (void)accountCreated:(UserAccount *)newAccount;

@end

@interface AccountCreateViewController : UIViewController <UITextFieldDelegate>
@property (weak, nonatomic) IBOutlet UIButton *cancelButton;
@property (weak, nonatomic) IBOutlet UIButton *saveButton;
@property (weak, nonatomic) IBOutlet UITextField *accountNameField;
@property (weak, nonatomic) IBOutlet UITextField *accountIValueField;
@property (weak, nonatomic) IBOutlet UIScrollView *scrollView;

@property (nonatomic, assign) id delegate;

- (IBAction)dismissAccountCreateScreen:(id)sender;
- (IBAction)createAccount:(id)sender;

@end
