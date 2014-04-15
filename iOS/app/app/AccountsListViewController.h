//
//  AccountsListViewController.h
//  app
//
//  Created by Richard on 4/13/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CircularImageView.h"
#import "AccountCreateViewController.h"

@interface AccountsListViewController : UIViewController <UIScrollViewDelegate, UITableViewDelegate, UITableViewDataSource, AccountCreationDelegate>
@property (weak, nonatomic) IBOutlet UIView *accountsListView;
@property (weak, nonatomic) IBOutlet UIButton *addAccountButton;
@property (weak, nonatomic) IBOutlet UIButton *settingsButton;
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (strong, nonatomic) UIView *userView;
@property (strong, nonatomic) CircularImageView *userPictureView;
@property double headerYOffset;

- (IBAction)openSettingsView:(id)sender;

@end
