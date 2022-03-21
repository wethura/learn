#include<bits/stdc++.h>
#include "time.h"
using namespace std;

template<typename T> void logger(T e);
template<typename T> void logger(vector<T> arr);
template<typename T> void logger(vector<vector<T>> arrs);

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution {
public:
    bool findTarget(TreeNode* root, int k) {
        if(!root) return false;
        int minn, maxn;
        stack<TreeNode*> preorderTravel, postorderTravel;
        preorderTravel.push(root);
        postorderTravel.push(root);

        while(preorderTravel.top()->left) {
            preorderTravel.push(preorderTravel.top()->left);
            minn = preorderTravel.top()->val;
        }
        while(postorderTravel.top()->right) {
            postorderTravel.push(postorderTravel.top()->right);
            maxn = postorderTravel.top()->val;
        }

        while(minn < maxn) {
            if(minn + maxn == k) return true;
            if(minn * 2 >= k) return false;
            if(maxn * 2 <= k) return false;

            if(minn + maxn > k) {
                if(postorderTravel.top()->left) {
                    postorderTravel.push(postorderTravel.top()->left);
                    while(postorderTravel.top()->right) {
                        postorderTravel.push(postorderTravel.top()->right);
                    }
                } else {
                    while(postorderTravel.top()->val >= maxn) {
                        postorderTravel.pop();
                    }
                }
                maxn = postorderTravel.top()->val;
            } else {
                if(preorderTravel.top()->right) {
                    preorderTravel.push(preorderTravel.top()->right);
                    while(preorderTravel.top()->left) {
                        preorderTravel.push(preorderTravel.top()->left);
                    }
                } else {
                    while(preorderTravel.top()->val <= minn) {
                        preorderTravel.pop();
                    }
                }
            }
        }
        return false;
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
