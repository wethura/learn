#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;


/**
 * coding here
 */
class Solution {
private:
    int expectMaxLength(string &answerKey, int k, char expect) {
        int left = 0, result = 0, used = 0, len = answerKey.length();
        for(int i = 0; i < len; i ++) {
            if(answerKey[i] != expect) {
                used ++;
                if(used > k) {
                    while(answerKey[left ++] == expect);
                    used --;
                }
            }
            result = max(result, i - left + 1);
        }

        return result;
    }
public:
    int maxConsecutiveAnswers(string answerKey, int k) {
        return max(expectMaxLength(answerKey, k, 'T'), expectMaxLength(answerKey, k, 'F'));
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;
    logger(s.maxConsecutiveAnswers("TTFTTFTT", 1));
    logger(s.maxConsecutiveAnswers("TTFTTFTT", 2));
    logger(s.maxConsecutiveAnswers("TFFT", 1));
    logger(s.maxConsecutiveAnswers("TTFF", 2));
    logger(s.maxConsecutiveAnswers("T", 2));
    logger(s.maxConsecutiveAnswers("TTTT", 2));


    timer.log("Program execute");
}