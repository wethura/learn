#include<bits/stdc++.h>
#include "time.h"
using namespace std;

template<typename T> void logger(T e);
template<typename T> void logger(vector<T> arr);
template<typename T> void logger(vector<vector<T>> arrs);

struct Node {
    int x, y, val;

    Node(){}

    Node(int x, int y, int val): x(x), y(y), val(val){}

    bool operator < (const Node compare) const {
        return val < compare.val;
    }
};

struct MaxNode {
    int x, y, step, val;

    MaxNode() {}

    MaxNode(int x, int y, int step, int val): x(x), y(y), step(step), val(val){}

    bool operator < (const MaxNode compare) const {
        return step == compare.step ? val < compare.val : step < compare.step;
    }
};

class Solution {

private: 
    void findMaxStep(Node &node, vector<vector<int>> &maxStepMatrix, vector<vector<int>>& matrix) {
       priority_queue<MaxNode> que;
       que.push(MaxNode(node.x, node.y, maxStepMatrix[node.x][node.y], node.val));
       set<pair<int, int>> marked;
        marked.insert(make_pair(node.x, node.y));

       while(!que.empty()) {
           MaxNode cur = que.top();
           int x = cur.x, y = cur.y, val = cur.val, step = cur.step;
           que.pop();
           marked.erase(make_pair(x, y));

           // up
            if(y > 0 && matrix[x][y - 1] > val && step >= maxStepMatrix[x][y - 1]) {
                maxStepMatrix[x][y - 1] = step + 1;
                if(marked.find(make_pair(x, y - 1)) != marked.end()) {
                    que.push(MaxNode(x, y - 1, step + 1, matrix[x][y - 1]));
                }
            }

           // down
           if(y < matrix[0].size() - 1 && matrix[x][y + 1] > val && step >= maxStepMatrix[x][y + 1]) {
               maxStepMatrix[x][y + 1] = step + 1;
                if(marked.find(make_pair(x, y + 1)) != marked.end()) {
                    que.push(MaxNode(x, y + 1, step + 1, matrix[x][y + 1]));
                }
           }

           // left
            if(x > 0 && matrix[x - 1][y] > val && step >= maxStepMatrix[x - 1][y]) {
                maxStepMatrix[x - 1][y] = step + 1;
                if(marked.find(make_pair(x - 1, y)) != marked.end()) {
                    que.push(MaxNode(x - 1, y, step + 1, matrix[x - 1][y]));
                }
            }

           // right
            if(x < matrix.size() - 1 && matrix[x + 1][y] > val && step >= maxStepMatrix[x + 1][y]) {
                maxStepMatrix[x + 1][y] = step + 1;
                if(marked.find(make_pair(x + 1, y)) != marked.end()) {
                    que.push(MaxNode(x + 1, y, step + 1, matrix[x + 1][y]));
                }
            }
       }
    }

public:
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        vector<Node> nodes;
        for(int i = 0; i < matrix.size(); i ++) {
            for(int j = 0; j < matrix[0].size(); j ++) {
                nodes.push_back(Node(i, j, matrix[i][j]));
            }
        }

        sort(nodes.begin(), nodes.end());

        vector<vector<int>> maxStepMatrix(matrix.size(), vector(matrix[0].size(), 1));

        for(Node node: nodes) {
            findMaxStep(node, maxStepMatrix, matrix);
        }

        int res = 0;
        for(int i = 0; i < matrix.size(); i ++) {
            for(int j = 0; j < matrix[0].size(); j ++) {
                res = max(res, maxStepMatrix[i][j]);
            }
        }


        // for(int i = 0; i < matrix.size(); i ++) {
        //     for(int j = 0; j < matrix[0].size(); j ++) {
        //         cout << maxStepMatrix[i][j] << " ";
        //     } cout << endl;
        // } cout << endl;

        return res;
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;

    vector<int> arr = {1, 2, 3, 4, 5};
    // logger(arr);

    // vector<char> arr = {};

    vector<vector<int>> arr_1 = {{1, 2, 3, 4}, {8, 7, 6, 5}};
    // logger(arr_1);

    logger(s.longestIncreasingPath(arr_1));

    // vector<vector<char>> arr = {};
    


    // vector<int> res = 
    // vector<vector<int>> res = 
    timer.log("Program execute");

    // vector<char> res = 
    // vector<vector<char>> res = 


    // logger(res);
}

template<typename T> void logger(T e) {
    cout << e << endl;
}

template<typename T> void logger(vector<T> arr) {
    for_each(arr.begin(), arr.end(), logger); 
    cout << endl;
}

template<typename T> void logger(vector<vector<T>> arrs) {
    for_each(arrs.begin(), arrs.end(), logger);
    cout << endl;
}
