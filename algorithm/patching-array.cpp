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
public:
    int minPatches(vector<int>& nums, int n) {
        int result = 0, canReach = 0, current = 1, index = 0;
        while(current <= n) {
            // cout << current << " - " << n << endl;
            while(index < nums.size() && nums[index] <= current) {
                canReach += nums[index ++];
            }

            if(canReach >= current) {
                current = canReach + 1;
            } else {
                result ++;
                canReach += current;
                current = canReach + 1;
            }
        }

        return result;
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;
    vector<int> arr_1 = {1, 3};
    logger(s.minPatches(arr_1, 6));

    vector<int> arr_2 = {1, 5, 10};
    logger(s.minPatches(arr_2, 20));

    vector<int> arr_3 = {1};
    logger(s.minPatches(arr_3, 2147483647));

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
