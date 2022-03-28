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
    void dfs(vector<string> &result, string &s, int ind, string tmp, int len) {
        if(len == 4) {
            if(s.length() == ind) {
                result.push_back(tmp);
            }
            return ;
        }

        if(s[ind] == '0') {
            dfs(result, s, ind + 1, tmp.length() == 0 ? "0" : tmp + ".0", len + 1);
            return;
        }

        int n = 0;
        string now = "";
        for(int i = ind; i < s.length(); i ++) {
            n = n * 10 + (s[i] - '0');
            now += s[i];
            if(n < 256) {
                dfs(result, s, i + 1, tmp.length() == 0 ? now : tmp + "." + now, len + 1);
            } else {
                return;
            }
        }
    }
public:
    vector<string> restoreIpAddresses(string s) {
        if(!s.length()) return {};
        vector<string> result;

        dfs(result, s, 0, "", 0);

        return result;
    }
};

int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;

    logger(s.restoreIpAddresses("25525511135"));
    logger(s.restoreIpAddresses("0000"));
    logger(s.restoreIpAddresses("101023"));
    logger(s.restoreIpAddresses("10101"));

    // vector<int> arr = {1, 2, 3, 4};
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
