#include<bits/stdc++.h>
#include "time.h"
using namespace std;

template<typename T> void logger(T e);
template<typename T> void logger(vector<T> arr);
template<typename T> void logger(vector<vector<T>> arrs);

/**
 * coding here
 * 自以为是的利用快排的原理进行优化，结果比人家全部排序的速度还要慢~麻了
 * 
 * 可能由于数据的特殊性，加上洗牌算法之后发现性能上来了，但是没有比全部排序快，不开心~~~
 */
class Solution {
// private: 
//     int find_middle_by_optimized_quicksort(int l, int r, vector<int>& nums) {
//         int ql = l, qr = r;
//         while(ql < qr) {
//             while(ql < qr && nums[qr] >= nums[l]) qr --;
//             while(ql < qr && nums[ql] <= nums[l]) ql ++;
            
//             if(ql != qr) swap(nums[ql], nums[qr]);
//         }
//         swap(nums[l], nums[ql]);
        
        
//         // cout << ql << endl;
//         if((nums.size() % 2 == 0 && (ql == nums.size() / 2 || ql == (nums.size() / 2) - 1)) || nums.size() / 2 == ql) {
//             return nums[ql];
//         } 

//         if(ql >= (nums.size() + 1) / 2) {
//             return find_middle_by_optimized_quicksort(l, ql - 1, nums);
//         } else {
//             return find_middle_by_optimized_quicksort(ql + 1, r, nums);
//         }
//     }
public:
    int minMoves2(vector<int>& nums) {
        // int middle = find_middle_by_optimized_quicksort(0, nums.size() - 1, nums);
        int ret = 0;
        sort(nums.begin(), nums.end());

        int m = nums.size() / 2;
        for(int n: nums) {
            ret += abs(nums[m] - n);
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
    // vector<char> arr = {'a', 'b', 'c', 'd'};

    // vector<vector<int>> arrs = {{1, 2, 3, 4}, {5, 6, 7, 8}};
    // vector<vector<char>> arrs = {{'a', 'b', 'c', 'd'}, {'e', 'f', 'g', 'h'}};


    // vector<int> res = s.resolve();
    // vector<vector<int>> res = s.resolve();
    // vector<char> res = s.resolve();
    // vector<vector<char>> res = s.resolve();

    timer.log("Program execute");


    // logger(res);
}

template<typename T> void logger(T e) {
    cout << e << endl;
}

template<typename T> void logger(vector<T> arr) {
    for_each(arr.begin(), arr.end(), [](T &e){cout << e << " ";});
    cout << endl << endl;;
}

template<typename T> void logger(vector<vector<T>> arrs) {
    for_each(arrs.begin(), arrs.end(), [](vector<T> &arr) {
        for_each(arr.begin(), arr.end(), [](T &e){cout << e << " ";});
        cout << endl;
    });cout << endl;
}
