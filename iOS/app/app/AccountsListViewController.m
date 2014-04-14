//
//  AccountsListViewController.m
//  app
//
//  Created by Richard on 4/13/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import <Parse/Parse.h>
#import "AccountsListViewController.h"
#import "UserAccount.h"

@interface AccountsListViewController () {
    NSMutableArray *dataSource;
}

@end

@implementation AccountsListViewController

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
    // Create an empty table header view with small bottom border view
    UIView *tableHeaderView = [[UIView alloc] initWithFrame: CGRectMake(0.0, 0.0, self.view.frame.size.width, 180.0)];
    UIView *blackBorderView = [[UIView alloc] initWithFrame: CGRectMake(0.0, 179.0, self.view.frame.size.width, 1.0)];
    blackBorderView.backgroundColor = [UIColor colorWithRed: 0.0 green: 0.0 blue: 0.0 alpha: 0.8];
    [tableHeaderView addSubview: blackBorderView];
    
    _tableView.tableHeaderView = tableHeaderView;
    
    // Create the underlying imageview and offset it
    _headerYOffset = -150.0;
    _userView = [[UIView alloc] init];
    _userView.backgroundColor = [UIColor darkGrayColor];
    CGRect headerFrame = _userView.frame;
    headerFrame.origin.y = _headerYOffset;
    _userView.frame = headerFrame;
    [self.view insertSubview: _userView belowSubview: _tableView];
    
    _userPictureView = [[CircularImageView alloc] initWithFrame:CGRectMake(160, 160, 100, 100)];
    [_userView addSubview:_userPictureView];
    
    dataSource = [NSMutableArray arrayWithArray:[self getAccountsList]];
    NSLog(@"%@", [(UserAccount*)[dataSource objectAtIndex:0] accountName]);
}

- (NSArray *)getAccountsList
{
    PFQuery *query = [PFQuery queryWithClassName:@"Account"];
    [query whereKey:@"user" equalTo:[PFUser currentUser]]; // so unsafe
    return [query findObjects];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [dataSource count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *tableIdentifier = @"AccountTableViewCell";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:tableIdentifier];
    if (!cell) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:tableIdentifier];
    }
    
    cell.textLabel.text = [(UserAccount *)[dataSource objectAtIndex:indexPath.row] accountName];
    return cell;
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    CGFloat scrollOffset = scrollView.contentOffset.y;
    CGRect headerImageFrame = _userView.frame;
    
    if (scrollOffset < 0) {
        // Adjust image proportionally
        headerImageFrame.origin.y = _headerYOffset - ((scrollOffset / 3));
    } else {
        // We're scrolling up, return to normal behavior
        headerImageFrame.origin.y = _headerYOffset - scrollOffset;
    }
    
    _userView.frame = headerImageFrame;
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

- (IBAction)addAccount:(id)sender {
}

- (IBAction)openSettingsView:(id)sender {
}
@end
