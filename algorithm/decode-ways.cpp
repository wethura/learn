#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
public:
    int numDecodings(string s) {
        if(s.length() == 0 || s[0] == '0') return 0;
        else if(s.length() == 1) return 1;

        int l = s.length(), preNumber = 100, curNumber;
        vector<int> dp = vector<int>(l + 1, 0); dp[0] = 1;

        for(int i = 1; i <= l; i ++) {
            curNumber = s[i - 1] - '0';
            if(i - 2 >= 0 && preNumber > 0 && preNumber * 10 + curNumber <= 26) {
                dp[i] += dp[i - 2];
            } 

            if(curNumber > 0) {
                dp[i] += dp[i - 1];
            }

            preNumber = curNumber;
        }

        logger(dp);

        return dp[l];
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;

    logger(s.numDecodings("226"));


    timer.log("Program execute");


    // logger(res);
}