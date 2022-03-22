#include<bits/stdc++.h>

using namespace std;

struct Node {
public:
    map<char, shared_ptr<Node>> next;
    string word; /*mean a dictionary while not empty*/
};

void add(shared_ptr<Node> root, string word) {
    
    int l = word.size();
    for(int i = 0; i < l; i ++) {
        if(root->next.find(word[i]) == root->next.end()) {
            root->next[word[i]] = make_shared<Node>();
        }

        root = root->next[word[i]];
    }

    root->word = word;
}

class Solution {
private:
    int len_h, len_v;
    void dfs(int i, int j, set<string>& res, vector<vector<char>>& board, vector<vector<bool>>& sign, shared_ptr<Node> now) {
        // cout << i << " - " << j << endl;
        sign[i][j] = false;

        if(now -> word.size() > 0) {
            res.insert(now -> word);
        }

        if(now -> next.size() == 0) {
            sign[i][j] = true;
            return;
        }

        // up
        if(i > 0 && sign[i - 1][j] && now -> next.count(board[i - 1][j])) {
            dfs(i - 1, j, res, board, sign, now -> next[board[i - 1][j]]);
        }


        // down
        if(i < len_h - 1 && sign[i + 1][j] && now -> next.count(board[i + 1][j])) {
            dfs(i + 1, j, res, board, sign, now -> next[board[i + 1][j]]);
        }

        // left
        if(j > 0 && sign[i][j - 1] && now -> next.count(board[i][j - 1])) {
            dfs(i, j - 1, res, board, sign, now -> next[board[i][j - 1]]);
        }

        // right
        if(j < len_v - 1 && sign[i][j + 1] && now -> next.count(board[i][j + 1])) {
            dfs(i, j + 1, res, board, sign, now -> next[board[i][j + 1]]);
        }

        sign[i][j] = true;
    }
public:
    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        shared_ptr<Node> root(new Node());

        for(string word: words) {
            add(root, word);
        }

        len_h = board.size(), len_v = board[0].size();
        vector<vector<bool>> sign(len_h, vector<bool>(len_v, true));

        set<string> res;

        for(int i = 0; i < len_h; i ++) {
            for(int j = 0; j < len_v; j ++) {
                // cout << " ------------------------------- " << endl;
                if(root -> next.count(board[i][j]))
                    dfs(i, j, res, board, sign, root -> next[board[i][j]]);
                // cout << " ------------------------------- " << endl;
            }
        }

        return vector<string>(res.begin(), res.end());
    }
};

int main() {
    Solution s;
    vector<vector<char>> board = {
        {'a'}};
    vector<string> words = {"a"};
    vector<string> res = s.findWords(board, words);
    for(string w: res) {
        cout << w << endl;
    }
}