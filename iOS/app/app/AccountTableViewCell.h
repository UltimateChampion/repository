//
//  AccountTableViewCell.h
//  app
//
//  Created by Richard on 4/14/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AccountTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *accountNameLabel;
@property (weak, nonatomic) IBOutlet UILabel *accountSubtextLabel;
@property (weak, nonatomic) IBOutlet UILabel *accountBalanceLabel;

@end
