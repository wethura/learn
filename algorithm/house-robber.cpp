#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
public:
    int rob(vector<int>& nums) {
        int f = 0, s = 0, t = 0;
        for(int n: nums) {
            t = max(f + n, s);
            f = s;
            s = t;

            // logger(s);
        }

        return s;
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;

    vector<int> arr = {1, 2, 3, 1};
    // vector<int> arr = {2,7,9,3,1};
    timer.log("Program execute");


    logger(s.rob(arr));
}