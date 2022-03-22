#include<bits/stdc++.h>
using namespace std;

// Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Codec {
private:
    void append(vector<char>& res, int num) {
        if(num == 0) {
            res.push_back('0');
        } else {
            int count = 0;
            while(num) {
                res.push_back(num % 10 + '0');
                num /= 10;
                count ++;
            }
            reverse(res.end() - count, res.end());
        }
        res.push_back(',');
    }
    void serializeDfs(vector<char>& res, TreeNode* rt) {
        if(rt == NULL) {
            res.push_back('x');
            res.push_back(',');
            return ;
        } else {
            append(res, rt->val + 1000);
        }
        
        serializeDfs(res, rt->left);
        serializeDfs(res, rt->right);
    }

    int ind;
    string data;

    // pair first: index; pair second: value;
    int read() {
        if(data[ind] == 'x') {
            ind ++; ind ++;
            return -1;
        }
        int res = 0;
        while(data[ind] != ',') {
            res = res * 10 + (data[ind ++] - '0');
        }
        ind ++;

        return res;
    }

    void deserializeDfs(TreeNode* rt) {
        // cout << ind << " ";
        int left = read();
        // cout << left << " - ";
        if(left != -1) {
            TreeNode *l = new TreeNode(left - 1000);
            rt -> left = l;
            deserializeDfs(rt -> left);
        }
                // cout << ind << " ";
        int right = read();
        // cout << right << endl;;
        if(right != -1) {
            TreeNode *r = new TreeNode(right - 1000);
            rt -> right = r;
            deserializeDfs(rt -> right);
        }
    }
public:

    // Encodes a tree to a single string.
    // split by ','
    string serialize(TreeNode* root) {
        if(root == NULL) return "";
        vector<char> res;

        serializeDfs(res, root);

        cout << string(res.begin(), res.end()) << endl;
        return string(res.begin(), res.end());
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string d) {
        if(d.size() == 0) return NULL;

        ind = 0;
        data = d;

        TreeNode *root = new TreeNode(read() - 1000);
        deserializeDfs(root);

        return root;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec ser, deser;
// TreeNode* ans = deser.deserialize(ser.serialize(root));

int main() {
    
}