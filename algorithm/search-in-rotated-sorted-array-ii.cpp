#include<bits/stdc++.h>
#include "time.h"
using namespace std;

template<typename T> void logger(T e);
template<typename T> void logger(vector<T> arr);
template<typename T> void logger(vector<vector<T>> arrs);

/**
 * coding here
 */
class Solution {
private:
    int findTarget(int l, int r, vector<int>& nums, int target) {
        if(l > r) return false;
        if(l == r) return nums[l] == target;

        int m = (l + r) >> 1;
        if(nums[l] == target || nums[m] == target || nums[r] == target) 
            return true;

        if(m - 1 >= l + 1 && (nums[l + 1] >= nums[m - 1] || (nums[m - 1] >= target && target >= nums[l + 1]))) {
            if(findTarget(l + 1, m - 1, nums, target)) {
                return true;
            }
        }

        if(r - 1 >= m + 1 && (nums[m + 1] >= nums[r - 1] || (nums[r - 1] >= target && target >= nums[m + 1]))) {
            if(findTarget(m + 1, r - 1, nums, target)) {
                return true;
            }
        }

        return false;
    }
public:
    bool search(vector<int>& nums, int target) {
        return findTarget(0, nums.size() - 1, nums, target);
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;

    vector<int> arr = {0, 1, 1, 2, 0, 0};
    logger(s.search(arr, 2));


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
