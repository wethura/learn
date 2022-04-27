#include<bits/stdc++.h>
#include "time.h"
using namespace std;

template<typename T> void logger(T e);
template<typename T> void logger(vector<T> arr);
template<typename T> void logger(vector<vector<T>> arrs);

/**
 * coding here
 */
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution {
public:
    vector<vector<int>> levelOrder(TreeNode* root) {
        if(!root) return {};

        vector<vector<int>> res;
        queue<TreeNode*> que;
        int pre_count = 1, pre_insertes = 0,  next_count = 0, ind = 0;
        que.push_back(root);

        while(!que.empty()) {
            TreeNode* tmp = que.front(); que.pop();
            if(pre_insertes ++ == 0) {
                res.push_back({});
            }

            if(tmp->left) {
                que.push_back(tmp->left);
                next_count ++;
            }

            if(tmp->right) {
                que.push_back(tmp->right);
                next_count ++;
            }

            if(pre_insertes == pre_count) {
                pre_insertes = 0;
                pre_count = next_count;
                next_count = 0;
                ind ++;
            }
        }

        return res;
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    // Solution s;

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
