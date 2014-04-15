//
//  AccountTransactionListViewController.m
//  app
//
//  Created by Richard on 4/15/14.
//  Copyright (c) 2014 example. All rights reserved.
//

#import <Parse/Parse.h>
#import "AccountTransactionListViewController.h"
#import "AccountTableViewCell.h"

@interface AccountTransactionListViewController () {
    NSMutableArray *dataSource;
}

@end

@implementation AccountTransactionListViewController

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
    
    [self.accountNameLabel setText:[_userAccount accountName]];
    dataSource = [NSMutableArray arrayWithArray:[self getTransactionsList]];
}

- (NSArray *)getTransactionsList
{
    PFQuery *query = [PFQuery queryWithClassName:@"Transaction"];
    [query whereKey:@"userAccount" equalTo:_userAccount]; // so unsafe
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
    
    [[cell accountNameLabel] setText:[[dataSource objectAtIndex:indexPath.row] name]];
    [[cell accountSubtextLabel] setText:[[[dataSource objectAtIndex:indexPath.row] date] description]];
    
    NSNumberFormatter *f = [[NSNumberFormatter alloc] init];
    [f setNumberStyle:NSNumberFormatterCurrencyStyle];
    NSNumber *av = [[dataSource objectAtIndex:indexPath.row] getTxnValue];
    [[cell accountBalanceLabel] setText:[f stringFromNumber:av]];
    
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 66;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Do nothing for now
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

- (IBAction)backButtonPressed:(id)sender {
    [PFUser logOut];
    [self dismissViewControllerAnimated:YES completion:nil];
}

@end
