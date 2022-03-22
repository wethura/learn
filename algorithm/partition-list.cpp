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

    // Solution s;

    // vector<int> arr = {1, 2, 3, 4};
    // vector<char> arr = {'a', 'b', 'c', 'd'};

    // vector<vector<int>> arrs = {{1, 2, 3, 4}, {5, 6, 7, 8}};
    // vector<vector<char>> arrs = {{'a', 'b', 'c', 'd'}, {'e', 'f', 'g', 'h'}};


    // vector<int> res = s.resolve();
    // vector<vector<int>> res = s.resolve();
    // vector<char> res = s.resolve();
    // vector<vector<char>> res = s.resolve();

    timer.log("Program execute");


    // logger(res);
}

template<typename T> void logger(T e) {
    cout << e << endl;
}

template<typename T> void logger(vector<T> arr) {
    for_each(arr.begin(), arr.end(), [](T &e){cout << e << " ";});
    cout << endl << endl;;
}

template<typename T> void logger(vector<vector<T>> arrs) {
    for_each(arrs.begin(), arrs.end(), [](vector<T> &arr) {
        for_each(arr.begin(), arr.end(), [](T &e){cout << e << " ";});
        cout << endl;
    });cout << endl;
}
