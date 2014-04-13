//
//  CameraViewController.m
//  app
//
//  Created by Richard on 3/25/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import "CameraViewController.h"
#import <CoreImage/CoreImage.h>
#import <ImageIO/ImageIO.h>
#import <QuartzCore/QuartzCore.h>

@interface CameraViewController () {
    AVCaptureSession *session;
    AVCaptureDevice *device;
    AVCaptureStillImageOutput *stillImageOutput;
    AVCaptureVideoDataOutput *videoDataOutput;
    AVCaptureVideoPreviewLayer *videoPreviewLayer;
    UIImageView *pictureView;
}

@end


#define kScreenWidth [[UIScreen mainScreen] applicationFrame].size.width
#define kScreenHeight [[UIScreen mainScreen] applicationFrame].size.height

@implementation CameraViewController

- (void)viewDidLoad
{
    session = [[AVCaptureSession alloc] init];
    if ([session canSetSessionPreset:AVCaptureSessionPresetHigh]) {
        [session setSessionPreset:AVCaptureSessionPresetHigh];
    }
    else {
        NSLog(@"AVSession couldn't be set to high quality");
    }
    
    for (AVCaptureDevice *captureDevice in [AVCaptureDevice devices]) {
        if ([captureDevice hasMediaType:AVMediaTypeVideo]) {
            if ([captureDevice position] == AVCaptureDevicePositionFront) {
                self.isUsingFrontFacingCamera = YES;
                device = captureDevice;
                goto deviceFound;
            }
        }
    }
    
    if (device == nil) {
        self.isUsingFrontFacingCamera = NO;
        device = [AVCaptureDevice defaultDeviceWithMediaType:AVMediaTypeVideo];
    }
    
deviceFound:;
    NSError *error;
    AVCaptureDeviceInput *deviceInput = [AVCaptureDeviceInput deviceInputWithDevice:device error:&error];
    if (!error) {
        if ([session canAddInput:deviceInput]) {
            [session addInput:deviceInput];
        }
        
        stillImageOutput = [[AVCaptureStillImageOutput alloc] init];
        NSDictionary *outputSettings = [[NSDictionary alloc] initWithObjectsAndKeys:AVVideoCodecJPEG, AVVideoCodecKey, nil];
        [stillImageOutput setOutputSettings:outputSettings];
        
        videoDataOutput = [[AVCaptureVideoDataOutput alloc] init];
        NSDictionary *videoOutputSettings = [NSDictionary dictionaryWithObject:[NSNumber numberWithInt:kCMPixelFormat_32BGRA] forKey:(id)kCVPixelBufferPixelFormatTypeKey];
        [videoDataOutput setVideoSettings:videoOutputSettings];
        [videoDataOutput setAlwaysDiscardsLateVideoFrames:YES];
        
        if ([session canAddOutput:videoDataOutput]) {
            [session addOutput:videoDataOutput];
        }
        
        if ([session canAddOutput:stillImageOutput]) {
            [session addOutput:stillImageOutput];
        }
        
        videoPreviewLayer = [AVCaptureVideoPreviewLayer layerWithSession:session];
        videoPreviewLayer.frame = self.view.bounds;
        videoPreviewLayer.videoGravity = AVLayerVideoGravityResizeAspectFill;
        [self.view.layer addSublayer:videoPreviewLayer];
        [self.view bringSubviewToFront:self.takePictureButton];
        
        [session startRunning];
    }
    
    session = nil;
	if (error) {
		UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:
                                  [NSString stringWithFormat:@"Failed with error %d", (int)[error code]]
                                                            message:[error localizedDescription]
                                                           delegate:nil
                                                  cancelButtonTitle:@"Dismiss"
                                                  otherButtonTitles:nil];
		[alertView show];
	}
}

- (void)takePicture:(id)sender
{
    AVCaptureConnection *captureConnection = nil;
    for (AVCaptureConnection *connection in stillImageOutput.connections) {
        for (AVCaptureInputPort *port in [connection inputPorts]) {
            if ([[port mediaType] isEqual:AVMediaTypeVideo]) {
                captureConnection = connection;
                break;
            }
            
            if (captureConnection) break;
        }
    }
    
    [stillImageOutput captureStillImageAsynchronouslyFromConnection:captureConnection completionHandler:
     ^(CMSampleBufferRef imageSampleBuffer, NSError *error) {
         if (error) {
             NSLog(@"Problem with taking picture: %@", [error localizedDescription]);
             return;
         }
         if (imageSampleBuffer != NULL) {
             NSData *imageData = [AVCaptureStillImageOutput jpegStillImageNSDataRepresentation:imageSampleBuffer];
             self.lastCapturedPicture = [[UIImage alloc] initWithData:imageData];
             
             if (!pictureView) {
                 pictureView = [[UIImageView alloc] initWithFrame:[self.view frame]];
             }
             
             pictureView.backgroundColor = [UIColor colorWithPatternImage:self.lastCapturedPicture];
             [self.view addSubview:pictureView];
         }
    }];
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

@end