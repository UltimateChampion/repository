//
//  AccountTransactionListViewController.h
//  app
//
//  Created by Richard on 4/15/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UserAccount.h"
#import "Transaction.h"

@interface AccountTransactionListViewController : UIViewController <UITableViewDelegate, UITableViewDataSource>
@property (weak, nonatomic) IBOutlet UILabel *accountNameLabel;
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UIButton *backButton;
@property (strong, nonatomic) UserAccount *userAccount;

- (IBAction)backButtonPressed:(id)sender;

@end
