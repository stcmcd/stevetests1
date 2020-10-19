Scenario 1: Fund Manager raises a Future's order for a fund
Given Order Management login screen is displayed
When Fund Manager logs in
Then the "Order Management - Fund Manager" login screen is displayed
When Fund Manager selects "Raise Single Order – Future" in Order Type dialog
Then "Single Order – Future" screen is displayed
When Enter order details "Future"
| security | transType | order       | tradeDate  | settlementDate | fund |
| 060036   | Buy       | 10,000,000  | 01/07/2020 | 17/07/2020     | 8356 |
Then "Order Saved" screen is displayed