#include<bits/stdc++.h>

using namespace std;

class Solution {
private: 
    int maxMatched(string str) {
        int counter = 0;
        int len = str.length(), l = 0;
        for(int i = 0; i < len; i ++) {
            if(str[i] == '(') l ++;
            else if(str[i] == ')') {
                counter += (l > 0);
                l = max(l - 1, 0);
            }
        }
        return counter;
    }

    void dfs(set<string>& res, vector<char>& buffer, string &s, int ind, int l, int matched, int expectedMatched) {

        // cout << "ind: " << ind << endl;

        if(ind >= s.length()) {
            if(l == 0 && matched == expectedMatched) {
                res.insert(string(buffer.begin(), buffer.end()));
            }
            return ;
        }

        // 剪枝 1. 所剩下的长度不足以匹配右括号 2. 假设剩下的刚好都匹配上了仍旧不足以达到期望匹配数量
        if(l > s.length() - ind) return;
        if(matched + (l + s.length() - ind) / 2 < expectedMatched) return ;

        if(s[ind] == '(') {
            // add
            buffer.push_back('(');
            dfs(res, buffer, s, ind + 1, l + 1, matched, expectedMatched);
            buffer.pop_back();
            // ignored
            dfs(res, buffer, s, ind + 1, l, matched, expectedMatched);
        } else if(s[ind] == ')') {
            if(l > 0) {
                buffer.push_back(')');
                dfs(res, buffer, s, ind + 1, l - 1, matched + 1, expectedMatched);
                buffer.pop_back();
            }
            dfs(res, buffer, s, ind + 1, l, matched, expectedMatched);
        } else {
            buffer.push_back(s[ind]);
            dfs(res, buffer, s, ind + 1, l, matched, expectedMatched);
            buffer.pop_back();
        }
    }
public:
    vector<string> removeInvalidParentheses(string s) {
        set<string> res;
        vector<char> buffer;

        dfs(res, buffer, s, 0, 0, 0, maxMatched(s));

        return vector<string>(res.begin(), res.end());
    }
};

int main() {
    Solution s;

    auto startTime = std::chrono::high_resolution_clock::now();
    
    vector<string> res = s.removeInvalidParentheses("(((((((((((((x)))))))))))))");
    
    auto endTime = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double, std::milli> fp_ms = endTime - startTime;

    std::cout << fp_ms.count() << std::endl;
    return 0;
    for(string e: res) {
        cout << e << endl;
    }
}