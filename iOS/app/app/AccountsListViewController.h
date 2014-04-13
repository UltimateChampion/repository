//
//  AccountsListViewController.h
//  app
//
//  Created by Richard on 4/13/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AccountsListViewController : UIViewController
@property (weak, nonatomic) IBOutlet UIView *userInfoView;
@property (weak, nonatomic) IBOutlet UIView *accountsListView;
@property (weak, nonatomic) IBOutlet UIButton *addAccountButton;
@property (weak, nonatomic) IBOutlet UIButton *settingsButton;


- (IBAction)addAccount:(id)sender;
- (IBAction)openSettingsView:(id)sender;

@end
