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
    int ret_deep = -1, ret;
    void dfs(TreeNode* rt, int deep) {
        if(deep > ret_deep) {
            ret_deep = deep;
            ret = rt->val;
        }

        if(rt->left) dfs(rt->left, deep + 1);
        if(rt->right) dfs(rt->right, deep + 1);
    }
public:
    int findBottomLeftValue(TreeNode* root) {
        ret_deep = -1;
        dfs(root, 0);

        return ret;
    }
};


int main() {
    Timer timer("Execute timer");

    // Solution s;


    timer.log("Program execute");

}