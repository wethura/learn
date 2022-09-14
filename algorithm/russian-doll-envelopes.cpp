#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
public:
    int maxEnvelopes(vector<vector<int>>& envelopes) {
        if (envelopes.empty()) {
            return 0;
        }

        sort(envelopes.begin(), envelopes.end(), [](const vector<int>& a, const vector<int>& b){
            if(a[0] < b[0] || (a[0] == b[0] && a[1] > b[1])) {
                return true;
            }
            return false;
        });

        int cnt = 0;
        vector<vector<int>> ascensions;
        
        for(auto envelope: envelopes) {
            if(ascensions.empty() || (envelope[0] > ascensions[cnt - 1][0] && envelope[1] > ascensions[cnt - 1][1])) {
                ascensions.push_back(envelope);
                cnt ++;
            } else {
                // find address, which bigger a little.
                int index = lower_bound(ascensions.begin(), ascensions.end(), envelope, [](const vector<int>& a, const vector<int>& b) {
                    if(a[1] < b[1]) return true;
                    return false;
                }) - ascensions.begin();
                if(index < cnt) {
                    ascensions[index][0] = envelope[0];
                    ascensions[index][1] = envelope[1];
                }
            }
        }

        return cnt;
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;
    // vector<vector<int>> arr = {{1,1}, {1,1}, {2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
    vector<vector<int>> arr = {{15,8},{2,20},{2,14},{4,17},{8,19},{8,9},{5,7},{11,19},{8,11},{13,11},{2,13},{11,19},{8,11},{13,11},{2,13},{11,19},{16,1},{18,13},{14,17},{18,19}};
    logger(s.maxEnvelopes(arr));

    timer.log("Program execute");

}