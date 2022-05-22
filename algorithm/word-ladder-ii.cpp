#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Solution {
private: 
    vector<vector<int>> edges;
    vector<int> distances;
    vector<string> wordList;
    
    void recordResult(vector<vector<string>> &results, vector<string> &result, int cur, int dest, int step) {
        if(cur == dest) {
            results.push_back(result);
            return;
        }

        if(step > distances[dest]) return;

        for(int e: edges[cur]) {
            if(distances[e] == step) {
                result[step] = wordList[e];
                recordResult(results, result, e, dest, step + 1);
            }
        }
    }
public:
    vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        wordList.push_back(beginWord);
        wordList.push_back(endWord);

        this->wordList = wordList;

        int orig = wordList.size() - 2, dest = wordList.size() - 1;
        edges = vector<vector<int>>(wordList.size() + 2, vector<int>{});

        map<string, int> wordIndex;
        for(int i = 0; i < wordList.size() - 2; i ++) {
            wordIndex[wordList[i]] = i;
        }

        if(wordIndex.find(endWord) == wordIndex.end()) return {};

        wordIndex[beginWord] = wordList.size() - 2;
        wordIndex[endWord] = wordList.size() - 1;


        // build edges.
        for(int i = 0; i < wordList.size(); i ++) {
            string word = wordList[i];
            vector<char> chars(word.begin(), word.end());
            for(int j = 0; j < word.length(); j ++) {
                char tmpChar = chars[j];
                for(int k = 0; k < 26; k ++) {
                    if(tmpChar != k + 'a') {
                        chars[j] = k + 'a';
                        string str = string(chars.begin(), chars.end());
                        if(wordIndex.find(str) != wordIndex.end()) {
                            edges[i].push_back(wordIndex[str]);
                        }
                    }
                }
                chars[j] = tmpChar;
            }
        }

        distances = vector<int>(wordList.size(), -1);
        distances[orig] = 0;
        queue<int> que;
        que.push(orig);

        while (!que.empty()){
            int now = que.front(); que.pop();
            for(int d:edges[now]) {
                if(distances[d] == -1) {
                    distances[d] = distances[now] + 1;
                    que.push(d);
                }
            }
        }

        if(distances[dest] == -1) return {};

        vector<vector<string>> res;
        vector<string> tmp(distances[dest] + 1, beginWord);

        recordResult(res, tmp, orig, dest, 1);

        return res;
    }
};


int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;

    vector<string> worldList = {"hot","dot","dog","lot","log"};
    log.info(s.findLadders("hit", "cog", worldList));



    timer.log("Program execute");
}
