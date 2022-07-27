#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**·
 * coding here
 */
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> nums_set;
        for(const int& n:nums) {
            if(!nums_set.count(n)) {
                nums_set.emplace(n);
            }
        }

        int ret = 0;
        for(const int& n: nums_set) {
            if(!nums_set.count(n - 1)) {
                int check = n, tmp = 1;
                while(nums_set.count(++ check)) tmp ++;
                ret = max(ret, tmp);
            }
        }

        return ret;
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;
    vector<int> arr = {2,42,5,2,1,3,4,6,87,22};
    logger(s.longestConsecutive(arr));


    timer.log("Program execute");
}