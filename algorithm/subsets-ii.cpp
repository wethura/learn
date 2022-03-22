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
    int OFFSET = 10;
    vector<int> counter = vector<int>(21, 0);
    void dfs(int index, vector<vector<int>> &results, vector<int> &tmp) {
        if(index == 21) {
            results.push_back(tmp);
            return ;
        }

        // don't push
        dfs(index + 1, results, tmp);
        for(int i = 0; i < counter[index]; i ++) {
            // push
            tmp.push_back(index - OFFSET);
            dfs(index + 1, results, tmp);
        }
        for(int i = 0; i < counter[index]; i ++) 
            tmp.pop_back();
    }
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        for(int n: nums) {
            counter[n + OFFSET] ++;
        }

        vector<vector<int>> results;
        vector<int> tmp;

        dfs(0, results, tmp);

        return results;
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;

    vector<int> arr = {1, 2, 2, 2, 2, 2};
    // vector<char> arr = {'a', 'b', 'c', 'd'};

    // vector<vector<int>> arrs = {{1, 2, 3, 4}, {5, 6, 7, 8}};
    // vector<vector<char>> arrs = {{'a', 'b', 'c', 'd'}, {'e', 'f', 'g', 'h'}};

    logger(s.subsetsWithDup(arr));

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
