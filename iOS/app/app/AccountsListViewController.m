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
#import "AccountTableViewCell.h"
#import "AccountCreateViewController.h"
#import "AccountTransactionListViewController.h"

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
}

- (NSArray *)getAccountsList
{
    PFQuery *query = [PFQuery queryWithClassName:@"Account"];
    [query whereKey:@"user" equalTo:[PFUser currentUser]]; // so unsafe
    
//    __block NSArray *o;
//    [query findObjectsInBackgroundWithBlock:^(NSArray *objects, NSError *error) {
//        if (error) {
//            NSLog(@"%@", [error localizedDescription]);
//            return;
//        }
//        else {
//            o = [NSArray arrayWithArray:objects];
//        }
//    }];
    
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
    
    AccountTableViewCell *cell = (AccountTableViewCell *)[tableView dequeueReusableCellWithIdentifier:tableIdentifier];
    if (!cell) {
        NSArray *nib = [[NSBundle mainBundle] loadNibNamed:@"AccountTableViewCell" owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    
    [[cell accountNameLabel] setText:[[dataSource objectAtIndex:indexPath.row] accountName]];
    [[cell accountSubtextLabel] setText:[[[dataSource objectAtIndex:indexPath.row] updatedAt] description]];
    
    NSNumberFormatter *f = [[NSNumberFormatter alloc] init];
    [f setNumberStyle:NSNumberFormatterCurrencyStyle];
    NSNumber *av = [[dataSource objectAtIndex:indexPath.row] accountValue];
    [[cell accountBalanceLabel] setText:[f stringFromNumber:av]];
    
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 66;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    AccountTransactionListViewController *viewController = [storyboard instantiateViewControllerWithIdentifier:@"AccountTransactionListView"];
    [viewController setUserAccount:[dataSource objectAtIndex:indexPath.row]];
    [self presentViewController:viewController animated:YES completion:nil];
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

- (void)accountCreated:(UserAccount *)newAccount
{
    [dataSource addObject:newAccount];
    [_tableView reloadData];
}

#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    NSLog(@"%@", segue.identifier);
    if ([segue.identifier isEqualToString:@"accountCreateSegue"]) {
        NSLog(@"gonna segue");
        ((AccountCreateViewController *)[segue destinationViewController]).delegate = self;
    }
}

- (IBAction)openSettingsView:(id)sender {
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    UIViewController *viewController = [storyboard instantiateViewControllerWithIdentifier:@"FirstView"];
    [self presentViewController:viewController animated:NO completion:nil];
}

@end
