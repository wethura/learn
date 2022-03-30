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
    void copy(TreeNode* from, TreeNode* to, int &start) {
        if(!from) return;

        if(from->left) {
            to->left = new TreeNode();
            copy(from->left, to->left, start);
        }

        to->val = start ++;
        
        if(from->right) {
            to->right = new TreeNode();
            copy(from->right, to->right, start);
        }
    }
public:
    vector<TreeNode*> generateTrees(int n) {
        vector<vector<TreeNode*>> dp(n + 1, vector<TreeNode*>(0));
        dp[0] = {nullptr};
        dp[1] = {new TreeNode(1)};

        // make sure that the logic of copy is right. 
        // TreeNode *left = new TreeNode(), *right = new TreeNode();
        // int cnt = 1;
        // copy(dp[1][0], left, cnt);
        // copy(dp[1][0], right, ++cnt);
        // return {new TreeNode(2, left, right)};

        for(int i = 2; i <= n; i ++) {
            for(int j = 0; j < i; j ++) {
                for(int k = 0; k < dp[j].size(); k ++) {
                    for(int l = 0; l < dp[i - j - 1].size(); l ++) {
                        TreeNode *left = new TreeNode(), *right = new TreeNode();
                        int cnt = 1;
                        if(j == 0) {
                            left = nullptr;
                        } else {
                            copy(dp[j][k], left, cnt);
                        }

                        TreeNode* tmp = new TreeNode(cnt ++);

                        if(i - j - 1 == 0) {
                            right = nullptr;
                        } else {
                            copy(dp[i - j - 1][l], right, cnt);
                        }
                        
                        tmp->left = left;
                        tmp->right = right;

                        dp[i].push_back(tmp);
                    }
                }
            }
        }

        return dp[n];
    }
};

int main() {
    
}