//
//  ViewController.m
//  app
//
//  Created by Richard on 3/24/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import "LoginViewController.h"
#import "AppDelegate.h"
#import "CameraViewController.h"
#import <QuartzCore/QuartzCore.h>
#import <Parse/Parse.h>

@interface LoginViewController () {
    UITextField *activeField;
}
@end

@implementation LoginViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    [self.scrollView setContentSize:[[UIScreen mainScreen] bounds].size];
    [self.scrollView setUserInteractionEnabled:YES];
    
    // create the blurred image
    UIImage *bgImage = [UIImage imageNamed:@"bgImage.jpg"];
    CIContext *context = [CIContext contextWithOptions:nil];
    CIImage *inputImage = [CIImage imageWithCGImage:bgImage.CGImage];
    
    // set up the Gaussian Blur
    CIFilter *filter = [CIFilter filterWithName:@"CIGaussianBlur"];
    [filter setValue:inputImage forKeyPath:kCIInputImageKey];
    [filter setValue:[NSNumber numberWithFloat:5.0f] forKeyPath:kCIInputRadiusKey];
    CIImage *result = [filter valueForKey:kCIOutputImageKey];
    CGImageRef cgImage = [context createCGImage:result fromRect:[inputImage extent]];
    
    // set the blurred image as the scrollview's background
    [self.scrollView setBackgroundColor:[UIColor colorWithPatternImage:[UIImage imageWithCGImage:cgImage]]];
    
    [self.roundedView setUserInteractionEnabled:YES];
    self.roundedView.layer.cornerRadius = 5;
    self.roundedView.layer.masksToBounds = YES;
    
    [self.username setDelegate:self];
    [self.password setDelegate:self];
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(touch)];
    [self.scrollView addGestureRecognizer:tap];
    
    [self registerForKeyboardNotifications];
    
    self.userImageView = [[CircularImageView alloc] initWithFrame:CGRectMake(110, 110, 100, 100)];
    [self.userImageView setUserPicture:[UIImage imageNamed:@"DefaultUserPicture.png"]];
    
    // use a UIButton here instead
    UITapGestureRecognizer *takePicture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(takePicture)];
    [self.userImageView addGestureRecognizer:takePicture];
    [self.view addSubview:self.userImageView];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)getStarted:(id)sender
{
    NSString *usernameString = [_username text];
    NSString *passwordString = [_password text];
    [PFUser logInWithUsernameInBackground:usernameString password:passwordString block:^(PFUser *user, NSError *error) {
        if (error) {
            NSLog(@"something went wrong");
            return;
        }
        else {
            [self.view endEditing:YES];
            UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
            CameraViewController *viewController = [storyboard instantiateViewControllerWithIdentifier:@"AccountListView"];
            [self presentViewController:viewController animated:YES completion:nil];
        }
    }];
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

- (void)registerForKeyboardNotifications
{
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWasShown:) name:UIKeyboardWillShowNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillBeHidden:) name:UIKeyboardWillHideNotification object:nil];
}

- (void)keyboardWasShown:(NSNotification *)notification
{
    CGFloat insetHeight = 0;
    {
        // the keyboard is mounted on the window object
        AppDelegate *delegate = (AppDelegate*)[[UIApplication sharedApplication] delegate];
        UIWindow *window = delegate.window;
        
        // UIKeyboardFrameEndUserInfoKey is key for the keyboard frame in screen coordinates
        NSDictionary *userInfo = [notification userInfo];
        CGRect keyboardFrame = [[userInfo objectForKey:UIKeyboardFrameEndUserInfoKey] CGRectValue];
        
        // convert scrollview frame to window coordinates to account for rotations
        CGRect ownFrame = [window convertRect:self.scrollView.frame fromView:self.scrollView.superview];
        
        // calculate the area covered by the keyboard
        CGRect coveredFrame = CGRectIntersection(ownFrame, keyboardFrame);
        
        // convert back in case screen is rotated
        coveredFrame = [window convertRect:coveredFrame toView:self.scrollView.superview];
        
        // Now coveredFrame gives us exactly the area of our view that is covered,
        // and thus the hight is also the bottom inset we need to apply.
        insetHeight = coveredFrame.size.height;
    }
    
	// set inset
	self.scrollView.contentInset = UIEdgeInsetsMake(0, 0, insetHeight, 0);
	self.scrollView.scrollIndicatorInsets = self.scrollView.contentInset;
    
    // scroll to bottom minus the inset
    CGFloat offset = self.scrollView.contentInset.bottom - [self.scrollView contentSize].height;
    CGPoint bottomOffset = CGPointMake(0, offset);
    [self.scrollView setContentOffset:bottomOffset animated:YES];
}

- (void)keyboardWillBeHidden:(NSNotification *)notification
{
    // read the animation settings of the keyboard
    NSTimeInterval animationDuration;
    UIViewAnimationCurve animationCurve;
    NSDictionary* userInfo = [notification userInfo];
    [[userInfo objectForKey:UIKeyboardAnimationCurveUserInfoKey] getValue:&animationCurve];
    [[userInfo objectForKey:UIKeyboardAnimationDurationUserInfoKey] getValue:&animationDuration];
    
    // Remove the inset using the animation settings of the keyboard.
    // This has the effect of moving the view down in sync with the keyboard.
    [UIView beginAnimations:nil context:nil];
    [UIView setAnimationDuration:animationDuration];
    [UIView setAnimationCurve:animationCurve];
    self.scrollView.contentInset = UIEdgeInsetsMake(0, 0, 0, 0);
    self.scrollView.scrollIndicatorInsets = self.scrollView.contentInset;
    [UIView commitAnimations];
}

- (void)takePicture
{
    [self.view endEditing:YES];
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    CameraViewController *viewController = [storyboard instantiateViewControllerWithIdentifier:@"CameraView"];
    [self presentViewController:viewController animated:YES completion:nil];
}

@end
