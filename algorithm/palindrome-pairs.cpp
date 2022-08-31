#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
struct Node {
    int next[26], feature = -1;
    Node() {
        feature = -1;
        memset(next, 0, sizeof(next));
    }
};

class Solution {
private: 
    int cnt = 0, minWlen;
    const int INF = 0x3f3f3f3f;
    vector<Node> nodes;

    bool isPalindrome(string &word, int from, int to) {
        while(from < to) {
            if(word[from] != word[to]) return false;
            from ++;
            to --;
        }

        return true;
    }

    void initial() {
        cnt = 0;
        minWlen = INF;
        nodes.clear();
        nodes.push_back(Node());
    }

    void insert(string &word, int feature) {
        int cur = 0;

        for(char ch: word) {
            int chInd = ch - 'a';
            if(nodes[cur].next[chInd] == 0) {
                nodes.push_back(Node());
                nodes[cur].next[chInd] = ++cnt;
            }
            cur = nodes[cur].next[chInd];
        }

        nodes[cur].feature = feature;
    }

    // return -1 means not match.
    int existsWord(string &word, int from, int to, int notExpectedFeature) {
        int cur = 0;

        for(int i = to; i >= from; i --) {
            int chInd = word[i] - 'a';
            if(nodes[cur].next[chInd] != 0) {
                cur = nodes[cur].next[chInd];
            } else {
                return -1;
            }
        }

        return nodes[cur].feature == notExpectedFeature ? -1 : nodes[cur].feature;
    } 

    int _min(int a, int b) {
        return a > b ? b : a;
    }
    int _max(int a, int b) {
        return a < b ? b : a;
    }
public:
    vector<vector<int>> palindromePairs(vector<string>& words) {
        initial();

        // minWlen is the optimize options after tle, through the feature of data. (without this gay, code will be tle)
        int len = words.size(), wlen, matched;
        for(int i = 0; i < len; i ++) {
            insert(words[i], i);
            minWlen = _min(minWlen, words[i].size());
        }
        
        vector<vector<int>> res;
        for(int i = 0; i < len; i ++) {
            wlen = words[i].size();
            for(int j = 0; j <= wlen; j ++) {

                if(j && wlen - 1 - j + 1 >= minWlen && isPalindrome(words[i], 0, j - 1) && (matched = existsWord(words[i], j, wlen - 1, i)) != -1) {
                    res.push_back({matched, i});
                }

                if(j >= minWlen && isPalindrome(words[i], j, wlen - 1) && (matched = existsWord(words[i], 0, j - 1, i)) != -1) {
                    res.push_back({i, matched});
                }
            }
        }

        return res;
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;
    vector<string> words_01 = {"abcd","dcba","lls","s","sssll"};
    logger(s.palindromePairs(words_01));

    logger("------------");

    vector<string> words_02 = {"a",""};
    logger(s.palindromePairs(words_02));

    timer.log("Program execute");

}