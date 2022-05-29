#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

template<typename T> void logger(T e);
template<typename T> void logger(vector<T> arr);
template<typename T> void logger(vector<vector<T>> arrs);

struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* deleteDuplicates(ListNode* head) {

        if(!head) return head;

        ListNode *root = new ListNode(-1, head);
        ListNode *cur = root;

        while(cur->next && cur->next->next) {
            if(cur->next->val == cur->next->next->val) {
                int v = cur->next->val;
                while(cur->next && cur->next->val == v) {
                    cur->next = cur->next->next;
                }
            } else {
                cur = cur->next;
            }
        }

        return root->next;
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    timer.log("Program execute");


    // logger(res);
}
