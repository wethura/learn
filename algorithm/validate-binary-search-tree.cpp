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
    bool dfs(TreeNode* rt, long &cnt) {
        if(rt -> left) {
            if(!dfs(rt -> left, cnt)) {
                return false;
            }
        }

        if(cnt >= rt->val) {
            return false;
        } else {
            cnt = rt -> val;
        }

        if(rt -> right) {
            if(!dfs(rt -> right, cnt)) {
                return false;
            }
        }

        return true;
    }
public:
    bool isValidBST(TreeNode* root) {
        if(!root) return true;
        long tmp = -(1 << 32);
        return dfs(root, tmp);
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    timer.log("Program execute");
}