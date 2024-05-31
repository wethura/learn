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
    TreeNode* pre;

    void dfs(TreeNode* rt) {
        pre->left = rt;
        pre = rt;
        
        if(rt->left) {
            dfs(rt->left);
        }

        if(rt->right) {
            dfs(rt->left);
        }
    }
public:
    void flatten(TreeNode* root) {
        pre = root;
        dfs(root);

        pre = root;
        while(pre->left) {
            pre->right = pre->left;
            pre->left = nullptr;
            pre =pre->right;
        }
    }
};


int main() {
    Timer timer("Execute timer");

    // Solution s;


    timer.log("Program execute");

}