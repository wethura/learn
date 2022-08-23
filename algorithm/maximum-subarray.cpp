#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
private: 
    const int INF = 0x3f3f3f3f;
public:
    int maxSubArray(vector<int>& nums) {
        int ret = - INF, cur = 0;
        for(int n: nums) {
            if(cur >= 0) {
                cur += n;
            } else {
                cur = n;
            }
            ret = ret > cur ? ret : cur;
        }

        return ret;
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;
    vector<int> arr1 = {-2,1,-3,4,-1,2,1,-5,4};
    // expected 6
    logger(s.maxSubArray(arr1));
    
    vector<int> arr2 = {-2,-1,-3,-1,-1,-2,-1,-5,-4};
    // expected -1
    logger(s.maxSubArray(arr2));

    vector<int> arr3 = {-1,1,2,1};
    // expected 4
    logger(s.maxSubArray(arr3));

    timer.log("Program execute");

}