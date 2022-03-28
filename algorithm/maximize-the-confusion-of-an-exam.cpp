#include<bits/stdc++.h>
#include "time.h"
using namespace std;

template<typename T> void logger(T e);
template<typename T> void logger(vector<T> arr);
template<typename T> void logger(vector<vector<T>> arrs);

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

    // vector<int> arr = {1, 2, 3, 4};
    // vector<char> arr = {'a', 'b', 'c', 'd'};

    // vector<vector<int>> arrs = {{1, 2, 3, 4}, {5, 6, 7, 8}};
    // vector<vector<char>> arrs = {{'a', 'b', 'c', 'd'}, {'e', 'f', 'g', 'h'}};


    // vector<int> res = s.resolve();
    // vector<vector<int>> res = s.resolve();
    // vector<char> res = s.resolve();
    // vector<vector<char>> res = s.resolve();

    timer.log("Program execute");


    // logger(res);
}

template<typename T> void logger(T e) {
    cout << e << endl;
}

template<typename T> void logger(vector<T> arr) {
    for_each(arr.begin(), arr.end(), [](T &e){cout << e << " ";});
    cout << endl << endl;;
}

template<typename T> void logger(vector<vector<T>> arrs) {
    for_each(arrs.begin(), arrs.end(), [](vector<T> &arr) {
        for_each(arr.begin(), arr.end(), [](T &e){cout << e << " ";});
        cout << endl;
    });cout << endl;
}
