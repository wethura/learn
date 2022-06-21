#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
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
private:
    void dfs(vector<vector<int>> &res, TreeNode* rt, int deep) {
        if(res.size() <= deep) {
            res.push_back({});
        }
        res[deep].push_back(rt->val);

        if(rt->left) dfs(res, rt->left, deep + 1);
        if(rt->right) dfs(res, rt->right, deep + 1);
    }
public:
    vector<vector<int>> levelOrderBottom(TreeNode* root) {
        if(root == nullptr) return {};
        vector<vector<int>> res;
        dfs(res, root, 0);

        reverse(res.begin(), res.end());
        return res;
    }
};


int main() {
    Timer timer("Execute timer");

    // Solution s;


    timer.log("Program execute");

}