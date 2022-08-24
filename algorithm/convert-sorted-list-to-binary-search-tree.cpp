#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
private: 
    ListNode* current;

    TreeNode* dfs(int step) {
        int l = step / 2;
        int r = step - l - 1;

        TreeNode* now = new TreeNode();

        if(l) {
            now->left = dfs(l);
        } 
        now->val = current->val;
        current = current ->next;
        if(r) {
            now->right = dfs(r);
        } 

        return now;
    }
public:
    TreeNode* sortedListToBST(ListNode* head) {
        if(head == nullptr) return nullptr;

        int count = 1;
        ListNode* tmp = head;
        current = head;

        while(tmp = tmp->next) count ++;
        // cout<<count<<endl;

        return dfs(count);
    }
};

int main() {
    Timer timer("Execute timer");

    // Solution s;


    timer.log("Program execute");

}