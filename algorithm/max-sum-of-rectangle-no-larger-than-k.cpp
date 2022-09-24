#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
public:
    int maxSumSubmatrix(vector<vector<int>>& matrix, int expect) {
        int n = matrix.size(), m = matrix[0].size(), ret = -1000000;
        for (int i = 0; i < n; i++) {
            vector<int> nums(m);
            for (int j = i; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    nums[k] += matrix[j][k];
                }
                
                // 利用 set 红黑树的特征来做优化
                set<int> numSet{0};
                int sum = 0;
                for(int num: nums) {
                    sum += num;
                    auto it = numSet.lower_bound(sum - expect);
                    if(it != numSet.end()) {
                        ret = max(ret, sum - *it);
                    }
                    numSet.insert(sum);
                }
            }
            
        }
        return ret;
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;
    vector<vector<int>> arrs = {{1}};
    logger(s.maxSumSubmatrix(arrs, 1));


    timer.log("Program execute");

}