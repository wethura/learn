#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
private:
    int OFFSET = 10;
    vector<int> counter = vector<int>(21, 0);
    void dfs(int index, vector<vector<int>> &results, vector<int> &tmp) {
        if(index == 21) {
            results.push_back(tmp);
            return ;
        }

        // don't push
        dfs(index + 1, results, tmp);
        for(int i = 0; i < counter[index]; i ++) {
            // push
            tmp.push_back(index - OFFSET);
            dfs(index + 1, results, tmp);
        }
        for(int i = 0; i < counter[index]; i ++) 
            tmp.pop_back();
    }
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        for(int n: nums) {
            counter[n + OFFSET] ++;
        }

        vector<vector<int>> results;
        vector<int> tmp;

        dfs(0, results, tmp);

        return results;
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;

    vector<int> arr = {1, 2, 2, 2, 2, 2};

    logger(s.subsetsWithDup(arr));


    timer.log("Program execute");


    // logger(res);
}