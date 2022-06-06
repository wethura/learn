#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
struct Node{
    int x, y, val;
    Node(){}
    Node(int x, int y, int val): x(x), y(y), val(val){}

    bool operator < (const Node& node) const {
        return val < node.val;
    }
};


class Solution {
private:
    int n, m, total;
    int direction[4][2] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    // return -1 while unreachable.
    int length(vector<vector<int>>& forest, int fx, int fy, int tx, int ty) {
        if(fx == tx && fy == ty) return 0;

        vector<vector<int>> mp(forest.size(), vector<int>(forest[0].size(), -1));
        
        int x, y;
        queue<pair<int, int>> que; que.push(make_pair(fx, fy));
        mp[fx][fy] = 0;
        while(!que.empty()) {
            fx = que.front().first;
            fy = que.front().second;
            que.pop();

            for(int i = 0; i < 4; i ++) {
                x = fx + direction[i][0];
                y = fy + direction[i][1];

                if(x < 0 || x >= n || y < 0 || y >= m || forest[x][y] == 0 || mp[x][y] != -1) {
                    continue;
                }

                if(x == tx && y == ty) return mp[fx][fy] + 1;

                que.push(make_pair(x, y));
                mp[x][y] = mp[fx][fy] + 1;
            }
        }
        
        return -1;
    }
public:
    int cutOffTree(vector<vector<int>>& forest) {
        n = forest.size(), m = forest[0].size(), total = n * m;
        vector<Node> nodes;


        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                nodes.push_back(Node(i, j, forest[i][j]));
            }
        }
        sort(nodes.begin(), nodes.end());
        
        int cur = 0; while(nodes[cur].val <= 1) cur ++;
        int cur_x = 0, cur_y = 0, ret = 0;

        // logger(nodes[2].x);
        // logger(nodes[2].y);
        // logger(nodes[2].val);

        while(cur < total) {
            int len = length(forest, cur_x, cur_y, nodes[cur].x, nodes[cur].y);

            if(len == -1) return -1;
            ret += len;
            cout << cur_x << "-" << cur_y << endl;

            cur_x = nodes[cur].x;
            cur_y = nodes[cur].y;
            cur ++;
        }


        return ret;
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;
    // vector<vector<int>> arr = {{1, 2, 3}, {0, 0, 4}, {7, 6, 5}};
    vector<vector<int>> arr = {{1, 3, 4}, {0, 0, 2}, {8, 7, 6}};
    // vector<vector<int>> arr = {{1, 3, 5}, {9, 8, 7}, {6, 4, 2}};
    logger(s.cutOffTree(arr));


    timer.log("Program execute");

}