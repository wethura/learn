#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
private:
    vector<long> arr = {1};
    int aindex = 0, bindex = 0, cindex = 0;
public:
    int getKthMagicNumber(int k) {
        if(k == 1) return 1;

        while(arr.size() < k) {
            if(arr[aindex] * 3 <= arr[bindex] * 5 && arr[aindex] * 3 <= arr[cindex] * 7) {
                if(arr.back() != arr[aindex] * 3) {
                    arr.push_back(arr[aindex ++] * 3);
                } else {
                    aindex ++;
                }
            } else if(arr[bindex] * 5 < arr[aindex] * 3 && arr[bindex] * 5 < arr[cindex] * 7) {
                if(arr.back() != arr[bindex] * 5) {
                    arr.push_back(arr[bindex ++] * 5);
                } else {
                    bindex ++;
                }
            } else {
                if(arr.back() != arr[cindex] * 7) {
                    arr.push_back(arr[cindex ++] * 7);
                } else {
                    cindex ++;
                }
            }
        }

        return arr[k - 1];
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;
    logger(s.getKthMagicNumber(1));
    logger(s.getKthMagicNumber(2));
    logger(s.getKthMagicNumber(3));
    logger(s.getKthMagicNumber(4));
    logger(s.getKthMagicNumber(5));
    logger(s.getKthMagicNumber(6));
    logger(s.getKthMagicNumber(7));
    logger(s.getKthMagicNumber(8));
    logger(s.getKthMagicNumber(9));
    logger(s.getKthMagicNumber(10));
    logger(s.getKthMagicNumber(11));
    logger(s.getKthMagicNumber(20));


    timer.log("Program execute");

}