#include<bits/stdc++.h>
#include "time.h"
using namespace std;

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
        que.push(root);

        while(!que.empty()) {
            TreeNode* tmp = que.front(); que.pop();
            if(pre_insertes ++ == 0) {
                res.push_back({});
            }
            res[ind].push_back(tmp->val);

            if(tmp->left) {
                que.push(tmp->left);
                next_count ++;
            }

            if(tmp->right) {
                que.push(tmp->right);
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

    timer.log("Program execute");


    // logger(res);
}
