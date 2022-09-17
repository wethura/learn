#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * 桶分法: 每个桶放取模值在 [0, k] 区间的值(如果有一个桶需要放下俩个值, 可以直接知道这个桶里面已经有俩个满足条件的值了，
 * 不需要继续往下进行判断，可知同一个桶在我们的计算过程中只需要放下一个值)
 * 然后再查看前一个桶和后一个是否有满足条件的值即可判断是否有满足条件的了
 */
class Solution {
private:
    const long long OFFSET = 1L << 31;
    long long getIdx(int num, int interval) {
        return 1LL * (OFFSET + num) / (interval + 1);
    }
public:
    bool containsNearbyAlmostDuplicate(vector<int>& nums, int indexDiff, int valueDiff) {
        unordered_map<long long, long long> bucket;

        long long len = nums.size();
        for(int i = 0; i < len; i ++) {
            long long idx = getIdx(nums[i], valueDiff);

            if(bucket.find(idx) != bucket.end()) {
                return true;
            }

            if((bucket.find(idx - 1) != bucket.end() && abs(bucket[idx - 1] - nums[i]) <= valueDiff) ||
                (bucket.find(idx + 1) != bucket.end() && abs(bucket[idx + 1] - nums[i]) <= valueDiff)) {
                    return true;
                }

            bucket[idx] = nums[i];

            if(i >= indexDiff) {
                bucket.erase(getIdx(nums[i - indexDiff], valueDiff));
            }
        }

        return false;
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;
    // vector<int> arr_01 = {2,1};
    // logger(s.containsNearbyAlmostDuplicate(arr_01, 1, 1));

    // vector<int> arr_02 = {1,0,1,1};
    // logger(s.containsNearbyAlmostDuplicate(arr_02, 1, 2));
    
    // vector<int> arr_03 = {1,5,9,1,5,9};
    // logger(s.containsNearbyAlmostDuplicate(arr_03, 2, 3));
        
    // vector<int> arr_04 = {1,3,5,7,9,11};
    // logger(s.containsNearbyAlmostDuplicate(arr_04, 1, 1));

    // vector<int> arr_05 = {-3,3};
    // logger(s.containsNearbyAlmostDuplicate(arr_05, 2, 4));

    vector<int> arr_06 = {-3,3,6};
    logger(s.containsNearbyAlmostDuplicate(arr_06, 2, 3));

    timer.log("Program execute");

}