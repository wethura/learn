#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

class SummaryRanges {
private: 
    map<int, int> intervals;
    map<int, bool> signal;
public:
    SummaryRanges() {
        intervals.clear();
        signal.clear();
    }
    
    void addNum(int val) {
        if(signal[val] != true) {
            intervals[val] = 1;
            signal[val] = true;
        }
    }
    
    vector<vector<int>> getIntervals() {
        vector<vector<int>> rets;
        map<int, int>::iterator cur = intervals.begin(), pre;
        int now = -1, cnt = 0;

        while(cur != intervals.end()){
            int key = cur->first;
            int step = cur->second;
            if(now < key) {
                pre = cur;
                rets.push_back({key, key + step - 1});
                now = key + step;
                cur ++;
                cnt ++;
            } else {
                now += step;
                pre->second = pre->second+step;
                rets[cnt - 1][1] = now - 1;

                cur = intervals.erase(cur);
            }
        }
        return rets;
    }
};


int main() {
    Timer timer("Execute timer");

    // Solution s;


    timer.log("Program execute");

}