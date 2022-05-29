#include<bits/stdc++.h>
#include "time.h"
using namespace std;

template<typename T> void logger(T e);
template<typename T> void logger(vector<T> arr);
template<typename T> void logger(vector<vector<T>> arrs);


/**
 * Definition for singly-linked list.
 */
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* partition(ListNode* head, int x) {
        if(!head) return head;

        ListNode *pre = new ListNode(-1), *aft = new ListNode(-1);
        ListNode *preCur = pre, *aftCur = aft;

        ListNode *cur = new ListNode(-1, head);

        while(cur->next) {
            if(cur->next->val < x) {
                preCur->next = cur->next;
                preCur = preCur->next;
            } else {
                aftCur->next = cur->next;
                aftCur = aftCur->next;
            }
            cur = cur->next;
        }


        if(pre->next && aft->next) {
            preCur->next = aft->next;
            aftCur->next = nullptr;
            return pre->next;
        } else if(pre->next) {
            preCur->next = nullptr;
            return pre->next;
        } else {
            aftCur->next = nullptr;
            return aft->next;
        }
    }
};

int main() {
    Timer timer("Execute timer");
    timer.restart();

    timer.log("Program execute");
}