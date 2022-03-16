#include<bits/stdc++.h>

using namespace std;

class Solution {
private:
    int format(int a) {
        if(a > 0) return 0;
        else return a;
    }
public:
    int calculateMinimumHP(vector<vector<int>>& dungeon) {

        int row = dungeon.size(), col = dungeon[0].size();
        vector<vector<int>> dp;dp.resize(row);
        for(int i = 0; i < row; i ++) dp[i].resize(col);

        for(int i = row - 1; i >= 0; i --) {
            for(int j = col - 1; j >= 0; j --) {
                if(i == row - 1 && j == col - 1) {
                    dp[i][j] = format(dungeon[i][j]);
                } else if (i == row - 1) {
                    dp[i][j] = format(dungeon[i][j] + dp[i][j + 1]);
                } else if (j == col - 1) {
                    dp[i][j] = format(dungeon[i][j] + dp[i + 1][j]);
                } else {
                    dp[i][j] = max(format(dungeon[i][j] + dp[i][j + 1]), format(dungeon[i][j] + dp[i + 1][j]));
                }
            }

        }

        for(int i = 0; i < row; i ++) {
            for(int j = 0; j < col; j ++) {
                cout << dp[i][j] << " ";
            }
            cout << endl;
        }
        return 1 - dp[0][0];
    }
};

int main() {

}