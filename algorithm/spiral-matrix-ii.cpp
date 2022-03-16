#include<bits/stdc++.h>
using namespace std;

class Solution {
private:
    vector<vector<int>> DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
public:
    vector<vector<int>> generateMatrix(int n) {
        vector<vector<int>> res(n, vector<int>(n, 0));
        int dir = 0, l = 0, r = 0, tl, tr;
        res[0][0] = 1;
        for(int i = 0; i < n * n - 1; i ++) {
            tl = l + DIRECTION[dir][0];
            tr = r + DIRECTION[dir][1];
            if(tl >= 0 && tl < n && tr >= 0 && tr < n && res[tl][tr] == 0) {
                l = tl; r = tr;
            } else {
                dir = (dir + 1) % 4;
                l = l + DIRECTION[dir][0];
                r = r + DIRECTION[dir][1];
            }
            res[l][r] = i + 2;
        }

        return res;
    }
};

int main() {
    Solution s;
    vector<vector<int>> res = s.generateMatrix(5);
    for(int i = 0; i < res.size(); i ++) {
        for(int j = 0; j < res.size(); j ++) {
            cout << res[i][j] << " ";
        } cout << endl;
    }
}