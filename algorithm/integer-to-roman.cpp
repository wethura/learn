#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
private: 
    string number[4][10] = {
        {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
        {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
        {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
        {"", "M", "MM", "MMM"}
    };
public:
    string intToRoman(int num) {
        string ret = "";
        int base = 0;
        while(num) {
            ret = number[base++][num%10] + ret;
            num /= 10;
        }

        return ret;
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;
    logger(s.intToRoman(3));
    logger(s.intToRoman(4));
    logger(s.intToRoman(9));
    logger(s.intToRoman(58));
    logger(s.intToRoman(1994));
    logger(s.intToRoman(3999));
    logger(s.intToRoman(2111));
    logger(s.intToRoman(199));


    timer.log("Program execute");

}