#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
private:
    int count(int n, int m, int compare) {
        int ret = 0, tmp;
        for(int i = n; i >= 1; i --) {
            tmp = compare / i;
            if(tmp >= m) {
                ret += i * m;
                return ret;
            } else {
                ret += tmp;
            }
        }

        return ret;
    }
public:
    int findKthNumber(int m, int n, int k) {
        if(n > m) {
            swap(n, m);
        }

        int l = 1, r = m * n, c;
        while(l < r) {
            c = (l + r) / 2;
            if(count(n, m, c) >= k) {
                r = c;
            } else {
                l = c + 1;
            }
        }

        return l;
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;
    logger(s.findKthNumber(3, 3, 8));

    timer.log("Program execute");
}
