#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

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

        Node* nodes[2][15];
        int turn[2], now = 0, i;

        nodes[now][0] = root;
        turn[now] = 1;

        while(turn[now]) {
            turn[!now] = 0;

            for(i = 0; i < turn[now]; i ++) {
                if(nodes[now][i]->left) {
                    nodes[!now][turn[!now]++] = nodes[now][i]->left;
                }
                if(nodes[now][i]->right) {
                    nodes[!now][turn[!now]++] = nodes[now][i]->right;
                }
            }

            for(i = 1; i < turn[!now]; i ++) {
                nodes[!now][i - 1]->next = nodes[!now][i];
            }

            now = !now;
        }

        return root;
    }
};