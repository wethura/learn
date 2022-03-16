#include<bits/stdc++.h>
using namespace std;

class Solution {
private:
    void dfs(vector<vector<int>> &res, vector<int>& tmp, int& n, int step, int& k) {
        if(step > n) return;
        if(tmp.size() == k) {
            res.push_back(tmp);
            return ;
        }

        // ignored.
        dfs(res, tmp, n, step + 1, k);
        // add 
        tmp.push_back(step);
        dfs(res, tmp, n, step + 1, k);
    }
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> res;
        vector<int> tmp;
        dfs(res, tmp, n, 1, k);
    }
};

int main() {

}