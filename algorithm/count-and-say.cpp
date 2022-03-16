#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    string countAndSay(int n) {
        vector<char> describe[2] = {{'1'}, {'1'}};
        int now = 1;

        while(n-- > 1) {
            now = !now;
            describe[now].clear();
            for(int i = 0; i < describe[!now].size();) {
                int count = 0;
                while(i < describe[!now].size() && describe[!now][i] == describe[!now][(i) - count]){ i ++; count ++;}
                describe[now].push_back('0' + count);
                describe[now].push_back(describe[!now][i - 1]);
            }
        }

        return string(describe[now].begin(), describe[now].end());
    }
};

int main() {
    Solution s;
    cout << s.countAndSay(1) << endl;
    cout << s.countAndSay(2) << endl;
    cout << s.countAndSay(3) << endl;
    cout << s.countAndSay(4) << endl;
    cout << s.countAndSay(5) << endl;
    cout << s.countAndSay(30) << endl;
}