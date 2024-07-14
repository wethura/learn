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
    int caculate(TreeNode* root, int current) {
        if(root->left == NULL && root->right == NULL) {
            return current * 10 + root->val;
        }

        int sum = 0;
        current = current * 10 + root->val;
        if (root->left != NULL) {
            sum += caculate(root->left, current);
        }
        if(root->right != NULL) {
            sum += caculate(root->right, current);
        }

        return sum;
    }
public:
    int sumNumbers(TreeNode* root) {
        return caculate(root, 0);
    }
};


int main() {
    Timer timer("Execute timer");

    // Solution s;


    timer.log("Program execute");

}