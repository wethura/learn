#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Node {
public:
    int val;
    Node* left;
    Node* right;
    Node* next;

    Node() : val(0), left(NULL), right(NULL), next(NULL) {}

    Node(int _val) : val(_val), left(NULL), right(NULL), next(NULL) {}

    Node(int _val, Node* _left, Node* _right, Node* _next)
        : val(_val), left(_left), right(_right), next(_next) {}
};

class Solution {
public:
    Node* connect(Node* root) {
        if(!root) return root;

        Node* cur = NULL;
        Node* next = root;
        Node* pre = NULL;

        while(next) {
            cur = next;
            next = NULL;
            pre = NULL;

            while(cur) {
                
                if(cur->left) {
                    if(pre) {
                        pre->next = cur->left;
                    }
                    pre = cur->left;
                    if(!next) next = pre;
                }

                if(cur->right) {
                    if(pre) {
                        pre->next = cur->right;
                    }
                    pre = cur->right;
                    if(!next) next = pre;
                }

                cur = cur->next;
            }
        }
        return root;
    }
};

int main() {
    Timer timer("Execute timer");

    // Solution s;


    timer.log("Program execute");

}