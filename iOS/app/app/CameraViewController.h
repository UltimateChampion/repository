//
//  CameraViewController.h
//  app
//
//  Created by Richard on 3/25/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import "AppDelegate.h"
#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>

@interface CameraViewController : UIViewController <AVCaptureVideoDataOutputSampleBufferDelegate>

@property (strong, nonatomic) UIImage *lastCapturedPicture;
@property (weak, nonatomic) IBOutlet UIButton *takePictureButton;

@property BOOL isUsingFrontFacingCamera;

- (IBAction)takePicture:(id)sender;

@end
