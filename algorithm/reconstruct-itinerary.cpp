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
    // string: vector<string> 从高到低排序的优先数组. 遍历时优先数组尾部，方便删除
    unordered_map<string, priority_queue<string, vector<string>, std::greater<string>>> vec;
    vector<string> res;

    void dfs(const string &current) {
        // 当前的 while 最多只会进来俩次; 第一次之后还有剩下的，说明第一次走的并不是一个完整的通路，再走一次即可: 由于必有答案，所以第二次. 必成功.
        while(vec.count(current) && vec[current].size() > 0) {
            string tmp = vec[current].top(); vec[current].pop();
            dfs(move(tmp));
        }
        res.emplace_back(current);
    }

public:
    vector<string> findItinerary(vector<vector<string>>& tickets) {
        for(auto &it : tickets) {
            vec[it[0]].emplace(it[1]);
        }

        dfs("JFK");

        reverse(res.begin(), res.end());

        return res;
    }
};

int main() {
    Timer timer("Execute timer");
    timer.restart();

    Solution s;

    // vector<int> arr = {1, 2, 3, 4};
    // vector<char> arr = {'a', 'b', 'c', 'd'};

    // vector<vector<int>> arrs = {{1, 2, 3, 4}, {5, 6, 7, 8}};
    // vector<vector<char>> arrs = {{'a', 'b', 'c', 'd'}, {'e', 'f', 'g', 'h'}};


    // vector<int> res = s.resolve();
    // vector<vector<int>> res = s.resolve();
    // vector<char> res = s.resolve();
    // vector<vector<char>> res = s.resolve();

    
    // vector<vector<string>> edges = {{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
    vector<vector<string>> edges = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"SFO","JFK"},{"ATL","SFO"}};
    logger(s.findItinerary(edges));

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
