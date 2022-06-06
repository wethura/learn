#include<bits/stdc++.h>
#include "time.h"
using namespace std;

/**
 * coding here
 * 自以为是的利用快排的原理进行优化，结果比人家全部排序的速度还要慢~麻了
 * 
 * 第一次优化，可能由于数据的特殊性，加上洗牌算法之后发现性能上来了，但是没有比全部排序快，不开心~~~
 * 
 * 第二次优化，简化自己做的多重判断，仔细想想没必要，将时间压缩到跟 sort 一样了。
 */
class Solution {
private: 
    int find_middle_by_optimized_quicksort(int l, int r, vector<int>& nums) {
        int ql = l, qr = r;
        while(ql < qr) {
            while(ql < qr && nums[qr] >= nums[l]) qr --;
            while(ql < qr && nums[ql] <= nums[l]) ql ++;
            
            if(ql != qr) swap(nums[ql], nums[qr]);
        }
        swap(nums[l], nums[ql]);
        
        
        // cout << ql << endl;
        if(ql == nums.size() / 2) {
            return nums[ql];
        } 

        if(ql >= (nums.size() + 1) / 2) {
            return find_middle_by_optimized_quicksort(l, ql - 1, nums);
        } else {
            return find_middle_by_optimized_quicksort(ql + 1, r, nums);
        }
    }
public:
    int minMoves2(vector<int>& nums) {
        random_shuffle(nums.begin(), nums.end());
        int middle = find_middle_by_optimized_quicksort(0, nums.size() - 1, nums);
        int ret = 0;
        for(int n: nums) {
            ret += abs(middle - n);
        }

        // logger(nums);
        // logger(middle);
        return ret;
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;

    vector<int> arr = {1, 10, 2, 9};
    s.minMoves2(arr);

    timer.log("Program execute");
}
