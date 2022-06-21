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
    map<int, int> index;
    int cnt = 0;

    void dfs(TreeNode* rt, int l, int r, vector<int>& preorder, vector<int>& inorder) {
        rt->val = preorder[cnt ++];
        
        if(l == r) {
            return;
        }

        if(index[rt->val] > l) {
            rt->left = new TreeNode();
            dfs(rt->left, l, index[rt->val] - 1, preorder, inorder);
        }

        if(index[rt->val] < r) {
            rt->right = new TreeNode();
            dfs(rt->right, index[rt->val] + 1, r, preorder, inorder);
        }
    }
public:
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        TreeNode *root = new TreeNode();
        index.clear();

        int len = preorder.size();
        for(int i = 0; i < len; i ++) {
            index[inorder[i]] = i;
        }

        // execute.
        dfs(root, 0, len - 1, preorder, inorder);

        return root;
    }
};


int main() {
    Timer timer("Execute timer");

    // Solution s;


    timer.log("Program execute");

}