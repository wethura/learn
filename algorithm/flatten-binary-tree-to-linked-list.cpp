#include<bits/stdc++.h>
using namespace std;

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
    TreeNode* pre = nullptr;

    void moveLeft(TreeNode* cur) {
        if(pre) {
            pre->left = cur;
        }
        pre = cur;

        if(cur->left) {
            moveLeft(cur->left);
        }

        if(cur->right) {
            moveLeft(cur->right);
        }
    }
public:
    void flatten(TreeNode* root) {
        if(!root) return;

        assert(root != NULL);
        moveLeft(root);

        TreeNode* n = root;

        assert(root != NULL);

        while(n->left) {
            n->right=n->left;
            n->left = nullptr;
            n = n->right;
        }
    }
};