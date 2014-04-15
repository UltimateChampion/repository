//
//  RegisterViewController.m
//  app
//
//  Created by Richard on 4/13/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import "RegisterViewController.h"
#import "AppDelegate.h"
#import "CameraViewController.h"
#import "AccountsListViewController.h"
#import <Parse/Parse.h>

@interface RegisterViewController () {
    UITextField *activeField;
}

@end

@implementation RegisterViewController

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
    [self.view setBackgroundColor:[UIColor colorWithRed:(243/255.0) green:(241/255.0) blue:(240/255.0) alpha:1]];
    [self.scrollView setUserInteractionEnabled:YES];
    [self.scrollView setScrollEnabled:YES];
    
    // [self registerForKeyboardNotifications];
    
//    CGRect rect = CGRectMake(120, 30, 80, 80);
//    self.userImageView = [[CircularImageView alloc] initWithFrame:rect];
//    [self.userImageView setUserPicture:[UIImage imageNamed:@"DefaultUserPicture.png"]];
//    UITapGestureRecognizer *takePicture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(takePicture)];
//    [self.userImageView addGestureRecognizer:takePicture];
//    [self.scrollView addSubview:self.userImageView];
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(touch)];
    [self.scrollView addGestureRecognizer:tap];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)touch
{
    [self.view endEditing:YES];
}

//- (void)registerForKeyboardNotifications
//{
//    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWasShown:) name:UIKeyboardWillShowNotification object:nil];
//    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillBeHidden:) name:UIKeyboardWillHideNotification object:nil];
//}
//
//- (void)keyboardWasShown:(NSNotification *)notification
//{
//    CGFloat insetHeight = 0;
//    {
//        // the keyboard is mounted on the window object
//        AppDelegate *delegate = (AppDelegate*)[[UIApplication sharedApplication] delegate];
//        UIWindow *window = delegate.window;
//        
//        // UIKeyboardFrameEndUserInfoKey is key for the keyboard frame in screen coordinates
//        NSDictionary *userInfo = [notification userInfo];
//        CGRect keyboardFrame = [[userInfo objectForKey:UIKeyboardFrameEndUserInfoKey] CGRectValue];
//        
//        // convert scrollview frame to window coordinates to account for rotations
//        CGRect ownFrame = [window convertRect:self.scrollView.frame fromView:self.scrollView.superview];
//        
//        // calculate the area covered by the keyboard
//        CGRect coveredFrame = CGRectIntersection(ownFrame, keyboardFrame);
//        
//        // convert back in case screen is rotated
//        coveredFrame = [window convertRect:coveredFrame toView:self.scrollView.superview];
//        
//        // Now coveredFrame gives us exactly the area of our view that is covered,
//        // and thus the hight is also the bottom inset we need to apply.
//        insetHeight = coveredFrame.size.height;
//    }
//    
//	// set inset
//	self.scrollView.contentInset = UIEdgeInsetsMake(0, 0, insetHeight, 0);
//	self.scrollView.scrollIndicatorInsets = self.scrollView.contentInset;
//    
//    // scroll to bottom minus the inset
//    CGFloat offset = self.scrollView.contentInset.bottom - [self.scrollView contentSize].height;
//    CGPoint bottomOffset = CGPointMake(0, offset);
//    [self.scrollView setContentOffset:bottomOffset animated:YES];
//}
//
//- (void)keyboardWillBeHidden:(NSNotification *)notification
//{
//    // read the animation settings of the keyboard
//    NSTimeInterval animationDuration;
//    UIViewAnimationCurve animationCurve;
//    NSDictionary* userInfo = [notification userInfo];
//    [[userInfo objectForKey:UIKeyboardAnimationCurveUserInfoKey] getValue:&animationCurve];
//    [[userInfo objectForKey:UIKeyboardAnimationDurationUserInfoKey] getValue:&animationDuration];
//    
//    // Remove the inset using the animation settings of the keyboard.
//    // This has the effect of moving the view down in sync with the keyboard.
//    [UIView beginAnimations:nil context:nil];
//    [UIView setAnimationDuration:animationDuration];
//    [UIView setAnimationCurve:animationCurve];
//    self.scrollView.contentInset = UIEdgeInsetsMake(0, 0, 0, 0);
//    self.scrollView.scrollIndicatorInsets = self.scrollView.contentInset;
//    [UIView commitAnimations];
//}



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

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (void)takePicture
{
    [self.view endEditing:YES];
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    CameraViewController *viewController = [storyboard instantiateViewControllerWithIdentifier:@"CameraView"];
    [self presentViewController:viewController animated:YES completion:nil];
}

- (IBAction)dismissRegisterScreen:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)saveButtonPressed:(id)sender {
    PFUser *user = [PFUser user];
    [user setUsername:[_username text]];
    [user setPassword:[_password text]];
    [user setEmail:[_emailAddress text]];
    user[@"firstName"] = [_firstName text];
    user[@"lastName"] = [_lastName text];
    
    [user signUpInBackgroundWithBlock:^(BOOL succeeded, NSError *error) {
        if (!error) {
            UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
            AccountsListViewController *viewController = [storyboard instantiateViewControllerWithIdentifier:@"AccountsListView"];
            [self presentViewController:viewController animated:YES completion:nil];
        }
        else {
            NSLog(@"%@", [error userInfo][@"error"]);
        }
    }];
}

- (UIStatusBarStyle)preferredStatusBarStyle
{
    return UIStatusBarStyleDefault;
}
@end
