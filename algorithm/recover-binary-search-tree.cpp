#include<bits/stdc++.h>
#include "time.h"
using namespace std;

template<typename T> void logger(T e);
template<typename T> void logger(vector<T> arr);
template<typename T> void logger(vector<vector<T>> arrs);

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
    int state = 0;
    long min_num = - (1L << 31) - 1;
    TreeNode *frist;
    TreeNode *second;
    TreeNode *previous;

    /*return the previous value*/
    void dfs(TreeNode* parent) {

        if(parent->left) {
            dfs(parent->left);
        }

        // middle order.
        if(previous != nullptr) {
            if(parent->val < previous->val) {
                if(++ state == 1) {
                    frist = previous;
                    second = parent;
                } else {
                    second = parent;
                }
            }   
        }
        previous = parent;
        cout << previous->val << " ";

        if(parent->right) {
            dfs(parent->right);
        }
    }
public:
    void recoverTree(TreeNode* root) {
        
        dfs(root);
        
        cout << endl << frist->val << " - " << second->val << endl;

        int tmp = second->val;
        second->val = frist->val;
        frist->val = tmp;
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
    logger("Hello World!");

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
