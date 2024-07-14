#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int i, ans = 0, size = prices.size();
        for(i = 1; i < size; i ++) {
            if(prices[i] > prices[i - 1]) {
                ans += prices[i] - prices[i - 1];
            }
        }

        return ans;
    }
};

int main(int argc, char const *argv[]) {

    return 0;
}
