//
//  FirstViewController.m
//  app
//
//  Created by Richard on 4/11/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import "FirstViewController.h"
#import "LoginViewController.h"

@interface FirstViewController ()

@end

@implementation FirstViewController

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
    [self.view setBackgroundColor:[UIColor colorWithPatternImage:[UIImage imageWithCGImage:cgImage]]];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (UIStatusBarStyle)preferredStatusBarStyle
{
    return UIStatusBarStyleLightContent;
}


#pragma mark - Navigation

- (IBAction)pushLoginViewController:(id)sender {
    CATransition *transition = [CATransition animation];
    transition.duration = 0.3;
    transition.type = kCATransitionFade;
    [self.view.layer addAnimation:transition forKey:kCATransition];
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    LoginViewController *viewController = [storyboard instantiateViewControllerWithIdentifier:@"LoginView"];
    [self presentViewController:viewController animated:NO completion:nil];
}

- (IBAction)pushSignUpViewController:(id)sender {
}
@end
