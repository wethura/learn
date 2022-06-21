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

    void dfs(TreeNode* rt, int l, int r, vector<int>& inorder, vector<int>& postorder) {
        rt->val = postorder[cnt --];
        
        if(l == r) {
            return;
        }

        if(index[rt->val] < r) {
            rt->right = new TreeNode();
            dfs(rt->right, index[rt->val] + 1, r, inorder, postorder);
        }

        if(index[rt->val] > l) {
            rt->left = new TreeNode();
            dfs(rt->left, l, index[rt->val] - 1, inorder, postorder);
        }

    }
public:
    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        TreeNode *root = new TreeNode();
        index.clear();

        int len = inorder.size();
        cnt = len - 1;
        for(int i = 0; i < len; i ++) {
            index[inorder[i]] = i;
        }

        // execute.
        dfs(root, 0, len - 1, inorder, postorder);

        return root;
    }
};


int main() {
    Timer timer("Execute timer");

    // Solution s;


    timer.log("Program execute");

}