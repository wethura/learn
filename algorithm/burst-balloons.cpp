#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxCoins(vector<int>& nums) {
        int len = 0;
        for(int i = 0; i < nums.size(); i ++) {
            if(nums[i] != 0) nums[len ++] = nums[i];
        }

        if(len == 0) return 0;
        if(len == 1) return nums[0];
        if(len == 2) return nums[0] * nums[1] + max(nums[0], nums[1]);

        vector<vector<int>> dp(len, vector<int>(len, 0));
        for(int i = 2; i < len; i ++) {
            for(int j = 0; j < len - i; j ++) {
                for(int k = j + 1; k < i + j; k ++) {
                    dp[j][j + i] = max(dp[j][j + i], dp[j][k] + dp[k][j + i] + nums[j] * nums[k] * nums[j + i]);
                }
            }
        }

        // for(int i = 0; i < len; i ++) {
        //     for(int j = 0; j < len; j ++) cout << dp[i][j] << " "; cout << endl;
        // }

        return dp[0][len - 1] + nums[0] * nums[len - 1] + max(nums[0], nums[len - 1]);
    }
};

int main() {
    Solution s;
    vector<int> nums = {0, 0, 0, 1, 2, 3, 4, 5, 6, 7};
    // vector<int> nums = {1, 2, 3, 4, 5};
    cout << s.maxCoins(nums) << endl;
}