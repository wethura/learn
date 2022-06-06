#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
        vector<vector<bool>> dp(l1 + 1, vector<bool>(l2 + 1, false)); dp[0][0] = true;
        for(int i = 0; i <= l1; i ++) {
            for(int j = 0; j <= l2; j ++) {
                if(i && s1[i - 1] == s3[i + j - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i][j];
                }

                if(j && s2[j - 1] == s3[i + j - 1]) {
                    dp[i][j] = dp[i][j - 1] || dp[i][j];
                }
            }
        }

        // logger(dp);

        return dp[l1][l2];
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;
    logger(s.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    timer.log("Program execute");
}
