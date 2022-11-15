#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
private:
    unordered_map<int, bool> win; 
    bool canIWinInState(int state, int maxChoosableInteger, int desiredTotal) {
        if(win.count(state)) {
            return win[state];
        }

        for(int i = 0; i < maxChoosableInteger; i ++) {
            if(((state >> i) & 1) == 1) continue;
            if(i + 1 >= desiredTotal || !canIWinInState(state | (1 << i), maxChoosableInteger, desiredTotal - i - 1)) {
                win[state] = true;
                return true;
            }
        }

        win[state] = false;
        return false;
    }
public:
    bool canIWin(int maxChoosableInteger, int desiredTotal) {
        win.clear();
        if((1+maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        return canIWinInState(0, maxChoosableInteger, desiredTotal);
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;
    logger(s.canIWin(10, 11));
    logger(s.canIWin(10, 13));
    logger(s.canIWin(10, 0));
    logger(s.canIWin(10, 1));


    timer.log("Program execute");

}