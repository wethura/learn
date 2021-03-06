#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
public:
    int networkBecomesIdle(vector<vector<int>>& edges, vector<int>& patience) {
        vector<vector<int>> es(patience.size(), vector<int>(0));
        // build edges
        for(vector<int> e: edges) {
            es[e[0]].push_back(e[1]);
            es[e[1]].push_back(e[0]);
        }

        // initial.
        vector<int> steps(patience.size(), -1);
        queue<int> que;
        steps[0] = 0;
        que.push(0);

        while(!que.empty()) {
            int start = que.front(); que.pop();

            for(int dest: es[start]) {
                if(steps[dest] == -1) {
                    steps[dest] = steps[start] + 1;
                    que.push(dest);
                }
            }
        }

        log.info(steps);
        logger(patience);

        int res = 0;
        for(int i = 1; i < patience.size(); i ++) {
            int step = steps[i] * 2;
            if(step % patience[i] == 0) {
                res = max(res, step * 2 - patience[i]);
            } else {
                res = max(res, step + (step - step % patience[i]) * (step / patience[i] > 0));
            }

            logger(res);
        }

        return res + 1;
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;


    vector<vector<int>> edges = {{3,8},{4,13},{0,7},{0,4},{1,8},{14,1},{7,2},{13,10},{9,11},{12,14},{0,6},{2,12},{11,5},{6,9},{10,3}};
    vector<int> patience = {0,3,2,1,5,1,5,5,3,1,2,2,2,2,1}  ;

    logger(s.networkBecomesIdle(edges, patience));

    timer.log("Program execute");


    // logger(res);
}