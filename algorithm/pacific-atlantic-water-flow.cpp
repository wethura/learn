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
    int n, m;
    void dfs(int i, int j, vector<vector<int>> &heights, vector<vector<int>> &dp) {
        // up

        if(i > 0 && heights[i][j] <= heights[i - 1][j] && (dp[i][j] - (dp[i][j] & dp[i - 1][j]) > 0)) {
            dp[i - 1][j] |= dp[i][j];
            dfs(i - 1, j, heights, dp);
        }

        // down
        if(i < n - 1 && heights[i][j] <= heights[i + 1][j] && (dp[i][j] - (dp[i][j] & dp[i + 1][j]) > 0)) {
            dp[i + 1][j] |= dp[i][j];
            dfs(i + 1, j, heights, dp);
        }

        // left
        if(j > 0 && heights[i][j] <= heights[i][j - 1] && (dp[i][j] - (dp[i][j] & dp[i][j - 1]) > 0)) {
            dp[i][j - 1] |= dp[i][j];
            dfs(i, j - 1, heights, dp);
        }

        // right
        if(j < m - 1 && heights[i][j] <= heights[i][j + 1] && (dp[i][j] - (dp[i][j] & dp[i][j + 1]) > 0)) {
            dp[i][j + 1] |= dp[i][j];
            dfs(i, j + 1, heights, dp);
        }
    }
public:
    vector<vector<int>> pacificAtlantic(vector<vector<int>>& heights) {
        n = heights.size(), m = heights[0].size();
        vector<vector<int>> dp = vector(heights.size(), vector<int>(heights[0].size(), 0));

        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if(!i || !j) dp[i][j] |= 1;
                if(i == n - 1 || j == m - 1) dp[i][j] |= 2;

                dfs(i, j, heights, dp);
            }
        }

        vector<vector<int>> res;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if(dp[i][j] == 3) res.push_back({i, j});
            }
        }

        // logger(dp);

        return res;
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;


    // vector<int> arr = {1, 2, 3, 4};
    // vector<char> arr = {'a', 'b', 'c', 'd'};

    // vector<vector<int>> arrs = {{1, 2, 3, 4}, {5, 6, 7, 8}};
    vector<vector<int>> arrs = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
    // vector<vector<char>> arrs = {{'a', 'b', 'c', 'd'}, {'e', 'f', 'g', 'h'}};

    s.pacificAtlantic(arrs);
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
