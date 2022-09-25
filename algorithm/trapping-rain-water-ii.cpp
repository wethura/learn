#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * idea: binary search the height of the place, and cover the same height near it.
 */
// class SolutionFailed {
// private:
//     int  maxn = 0, m, n;
//     vector<vector<bool>> confirmed;
//     const vector<vector<int>> directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

//     int getJustNotOverflow(vector<vector<int>>& heightMap, int x, int y) {
//         // logger(heightMap);

//         if(x == 0 || y == 0) {
//             return heightMap[x][y];
//         }

//         int left = heightMap[x][y], right = maxn, mid;
        
//         while(left < right) {
//             mid = (left + right + 1) / 2;

//             // initial
//             stack<pair<int, int>> sta;
//             sta.push(make_pair(x, y));

//             bool isOk = true;

//             vector<vector<bool>> visited = vector<vector<bool>>(m, vector<bool>(n, false));
//             visited[x][y] = true;

//             while(!sta.empty()) {
//                 pair<int,int> t = sta.top();
//                 sta.pop();

//                 for(vector<int> direction: directions) {
//                     if(visited[t.first + direction[0]][get<1>(t) + direction[1]]) continue;

//                     if(confirmed[t.first + direction[0]][get<1>(t) + direction[1]] && heightMap[t.first + direction[0]][get<1>(t) + direction[1]] < mid) {
//                         isOk = false;
//                     } else if(heightMap[t.first + direction[0]][get<1>(t) + direction[1]] < mid) {
//                         visited[t.first + direction[0]][get<1>(t) + direction[1]] = true;
//                         sta.push(make_pair(t.first + direction[0], get<1>(t) + direction[1]));
//                     }
//                 }
//             }

//             if(isOk) {
//                 left = mid;
//             } else {
//                 right = mid - 1;
//             }
//         }

//         return left;
//     }

//     int countAndVisited(vector<vector<int>>& heightMap, int x, int y, int height) {
//         int ret = 0;
//         stack<pair<int,int>> sta;
//         sta.push(make_pair(x, y));

//         while(!sta.empty()) {
//             pair<int,int> t = sta.top();
//             sta.pop();

//             ret += height - heightMap[t.first][get<1>(t)];
//             heightMap[t.first][get<1>(t)] = height;

//             for(vector<int> direction: directions) {
//                 if(confirmed[t.first + direction[0]][get<1>(t) + direction[1]]) continue;
                
//                 if(heightMap[t.first][get<1>(t)] == height) {
//                     if(heightMap[t.first + direction[0]][get<1>(t) + direction[1]] == height) {
//                         confirmed[t.first + direction[0]][get<1>(t) + direction[1]] = true;
//                         sta.push(make_pair(t.first + direction[0], get<1>(t) + direction[1]));
//                     }
//                 } else if(heightMap[t.first + direction[0]][get<1>(t) + direction[1]] <= height) {
//                     confirmed[t.first + direction[0]][get<1>(t) + direction[1]] = true;
//                     sta.push(make_pair(t.first + direction[0], get<1>(t) + direction[1]));
//                 }
//             }
//         }

//         return ret;
//     }
// public:
//     int trapRainWater(vector<vector<int>> heightMap) {
//         m = heightMap.size(), n = heightMap[0].size();
//         confirmed = vector<vector<bool>>(m, vector<bool>(n, false));

//         for(int i = 0; i < m; i ++) {
//             for(int j = 0; j < n; j ++) {
//                 maxn = max(maxn, heightMap[i][j]);
//                 if(!i || !j || i == m - 1 || j == n - 1) {
//                     confirmed[i][j] = true;
//                 }
//             }
//         }

//         // logger(heightMap);

//         int ret = 0;
//         for(int i = 1; i < m - 1; i ++) {
//             for(int j = 1; j < n - 1; j ++) {
//                 if(confirmed[i][j]) continue;

//                 ret += countAndVisited(heightMap, i, j, getJustNotOverflow(heightMap, i, j));
//             }
//         }

//         return ret;
//     }
// };


class Solution {
private:
    const vector<pair<int, int>> directions = {make_pair(-1, 0), make_pair(0, -1), make_pair(1, 0), make_pair(0, 1)}; 
public:
    int trapRainWater(vector<vector<int>>& heightMap) {
        // logger(heightMap);

        int m = heightMap.size(), n = heightMap[0].size(), ret = 0;
        vector<vector<bool>> visited = vector<vector<bool>>(m, vector<bool>(n, false));

        auto comp = [](tuple<int, int, int>& a, tuple<int, int, int>& b){
            return get<0>(a) > get<0>(b);
        };
        priority_queue<tuple<int, int, int>, vector<tuple<int,int,int>>, decltype(comp)> que(comp);

        for(int i = 0; i < n; i ++) {
            que.push(make_tuple(heightMap[0][i], 0, i));
            que.push(make_tuple(heightMap[m - 1][i], m - 1, i));

            visited[0][i] = true;
            visited[m - 1][i] = true;
        }

        for(int i = 1; i < m - 1; i ++) {
            que.push(make_tuple(heightMap[i][0], i, 0));
            que.push(make_tuple(heightMap[i][n - 1], i, n - 1));

            visited[i][0] = true;
            visited[i][n - 1] = true;
        }

        
        while(!que.empty()) {
            tuple<int, int, int> t = que.top();
            que.pop();

            for(pair<int, int> direction: directions) {
                if(get<1>(t) + direction.first < 0 || get<1>(t) + direction.first >= m || 
                    get<2>(t) + direction.second < 0 || get<2>(t) + direction.second >= n ||
                    visited[get<1>(t) + direction.first][get<2>(t) + direction.second]) continue;

                if(heightMap[get<1>(t) + direction.first][get<2>(t) + direction.second] < get<0>(t)) {
                    ret += get<0>(t) - heightMap[get<1>(t) + direction.first][get<2>(t) + direction.second];
                    heightMap[get<1>(t) + direction.first][get<2>(t) + direction.second] = get<0>(t);
                }

                visited[get<1>(t) + direction.first][get<2>(t) + direction.second] = true;
                tuple<int, int, int> dest = make_tuple(heightMap[get<1>(t) + direction.first][get<2>(t) + direction.second], get<1>(t) + direction.first, get<2>(t) + direction.second);
                que.push(dest);
            }
        }
        

        return ret;
    }
};

int main() {
    Timer timer("Execute timer");

    Solution s;

    vector<vector<int>> arr_01 = {{3,3,3,3,3},{3,2,2,2,3},{3,2,1,2,3},{3,2,2,2,3},{3,3,3,3,3}};
    logger(s.trapRainWater(arr_01));

    vector<vector<int>> arr_02 = {{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};
    logger(s.trapRainWater(arr_02));


    timer.log("Program execute");

}