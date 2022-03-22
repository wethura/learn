#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    string simplifyPath(string path) {
        stack<char> sta;
        int len = path.size(), state, j;
        for(int i = 0; i < len; i ++) {
            if(path[i] == '/') {
                if(sta.empty() || sta.top() != '/') sta.push('/');
            } else {
                j = i;
                while(j < len && path[j] != '/') {j ++;}
                // cout << j - i << endl;

                if(j - i > 2 || (j - i == 2 && (path[i] != '.' || path[i + 1] != '.')) || (j - i == 1 && path[i] != '.')) {
                    // cout << "push: " << i << " - " << j << endl;
                    while(i < j) sta.push(path[i++]); i--;
                } else if(j - i == 2 && path[i] == '.' && path[i + 1] == '.') {
                    // cout << "backspace: " << j << " size: " << sta.size() << " top:" << sta.top() << endl;
                    if(sta.size() > 1) {
                        sta.pop(); while(sta.size() > 1 && sta.top() != '/') {
                            // cout << "top:" << sta.top() << endl;
                            sta.pop();
                        } 
                    }
                    i += 1;
                }
            }
        }

        while(sta.size() > 1 && sta.top() == '/') sta.pop();
        if(sta.empty()) return "/";

        vector<char> tmp;
        while(!sta.empty()) {
            tmp.push_back(sta.top());
            sta.pop();
        }
        string rev_res = string(tmp.begin(), tmp.end());
        reverse(rev_res.begin(), rev_res.end());

        return rev_res;
    }
};

int main() {
    Solution s;
    cout << s.simplifyPath("/a/c/d//././/..") << endl;
}