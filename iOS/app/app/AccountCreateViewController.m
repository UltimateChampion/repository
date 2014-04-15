//
//  AccountCreateViewController.m
//  app
//
//  Created by Richard on 4/15/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import "AccountCreateViewController.h"
#import "AppDelegate.h"

@interface AccountCreateViewController () {
    UITextField *activeField;
}

@end

@implementation AccountCreateViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(touch)];
    [self.scrollView addGestureRecognizer:tap];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField
{
    return [textField resignFirstResponder];
}

- (void)textFieldDidBeginEditing:(UITextField *)textField
{
    activeField = textField;
}

- (void)textFieldDidEndEditing:(UITextField *)textField
{
    activeField = nil;
}

- (void)touch
{
    [self.view endEditing:YES];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)dismissAccountCreateScreen:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)createAccount:(id)sender {
    UserAccount *uac = [[UserAccount alloc] init];
    [uac setAccountName:[_accountNameField text]];
    [uac setInitialValue:[NSNumber numberWithDouble:[[_accountIValueField text] doubleValue]]];
    [uac setAccountValue:[uac initialValue]];
    [uac setUser:[PFUser currentUser]];
    [uac setACL:[PFACL ACLWithUser:[PFUser currentUser]]];
    [uac saveEventually];
    [_delegate accountCreated:uac];
    [self dismissViewControllerAnimated:YES completion:nil];
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
