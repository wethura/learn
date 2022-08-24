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
    void dfs(vector<vector<int>>& res, vector<int>& tmp, TreeNode* root, int sum, int targetSum) {
        sum += root->val;

        tmp.push_back(root->val);
        if(sum == targetSum && !root->left && !root->right) {
            res.push_back(tmp);
            tmp.pop_back();

            return;
        }

        if(root->left) {
            dfs(res, tmp, root->left, sum, targetSum);
        }

        
        if(root->right) {
            dfs(res, tmp, root->right, sum, targetSum);
        }

        tmp.pop_back();
    }
public:
    vector<vector<int>> pathSum(TreeNode* root, int targetSum) {
        vector<vector<int>> res;
        vector<int> tmp;

        dfs(res, tmp, root, 0, targetSum);

        return res;
    }
};


int main() {
    Timer timer("Execute timer");

    // Solution s;


    timer.log("Program execute");

}`