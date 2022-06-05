#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
public:
    bool isSelfCrossing(vector<int>& distance) {
        int index = 2, len = distance.size();
        // 外卷
        while(len > index && distance[index] > distance[index - 2]) index ++;

        // cout << "cross: " <<index << endl;
        // 检查过渡边
        if( ++ index < len && index >= 4) {
            if(distance[index] + distance[index - 4] >= distance[index - 2] &&
            (distance[index - 1] + (index > 4 ? distance[index - 5] : 0) >= distance[index - 3])) {
                return true;
            }
        }

        // 内卷
        while(index < len && distance[index] < distance[index - 2]) index ++;

        // cout << index << " - " << len << endl;
        return index < len;
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;
    vector<int> arr_01 = {1,1,2,1,1};
    logger(s.isSelfCrossing(arr_01));
    vector<int> arr_02 = {1,1,2,2,1,1};
    logger(s.isSelfCrossing(arr_02));
    vector<int> arr_03 = {1,1,2,2,3,3,4,4,10,4,4,3,3,2,2,1,1};
    logger(s.isSelfCrossing(arr_03));
    vector<int> arr_04 = {1,1,3,2,1,1};
    logger(s.isSelfCrossing(arr_04));

    timer.log("Program execute");

}