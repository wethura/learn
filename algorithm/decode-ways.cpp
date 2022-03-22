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
    int numDecodings(string s) {
        if(s.length() == 0 || s[0] == '0') return 0;
        else if(s.length() == 1) return 1;

        int l = s.length(), preNumber = 100, curNumber;
        vector<int> dp = vector<int>(l + 1, 0); dp[0] = 1;

        for(int i = 1; i <= l; i ++) {
            curNumber = s[i - 1] - '0';
            if(i - 2 >= 0 && preNumber > 0 && preNumber * 10 + curNumber <= 26) {
                dp[i] += dp[i - 2];
            } 

            if(curNumber > 0) {
                dp[i] += dp[i - 1];
            }

            preNumber = curNumber;
        }

        logger(dp);

        return dp[l];
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;

    // vector<int> arr = {1, 2, 3, 4};
    // vector<char> arr = {'a', 'b', 'c', 'd'};

    // vector<vector<int>> arrs = {{1, 2, 3, 4}, {5, 6, 7, 8}};
    // vector<vector<char>> arrs = {{'a', 'b', 'c', 'd'}, {'e', 'f', 'g', 'h'}};

    logger(s.numDecodings("226"));

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
