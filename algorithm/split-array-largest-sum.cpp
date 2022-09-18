#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
private:
    bool isOk(vector<int>& nums, int m, long long& target) {
        long long tmp = 0;
        for(int n: nums) {
            if(tmp + n > target) {
                if(--m <= 0) return false;
                if((tmp = n) > target) return false;
            } else {
                tmp += n;
            }
            // cout << "m: " << m << " tmp: " << tmp << " n:" << n <<endl;
        }

        return true;
    }
public:
    int splitArray(vector<int>& nums, int m) {
        long long left = 0, right = 0, middle;
        for(int n: nums) {
            right = right + n;
        }

        while(left < right) {
            // cout << left << " - " << right << endl;
            middle = (left + right) / 2;
            if(isOk(nums, m, middle)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }

        return left;
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;
    // vector<int> arr_01 = {7,2,5,10,8};
    // logger(s.splitArray(arr_01, 2));
    
    // vector<int> arr_02 = {1,2,3,4,5};
    // logger(s.splitArray(arr_02, 2));
        
    vector<int> arr_03 = {1,4,4};
    logger(s.splitArray(arr_03, 2));


    timer.log("Program execute");

}