#include<bits/stdc++.h>

using namespace std;

class Solution {
private: 
    long long num[22], dp[22][22], decimal[22];
    int dfs(int pos, int sum, bool limit) {
        if(pos == -1) return sum;
        if(!limit && dp[pos][sum] != -1) return dp[pos][sum];

        int ans = 0;
        int upset = limit ? num[pos] : 9;
        for(int i = 0; i <= upset; i ++) {
            ans += dfs(pos - 1, sum + (i == 1), limit && i == upset);
        }

        if(!limit) dp[pos][sum] = ans;

        return ans;
    }
public:
    int countDigitOne(int n) {
        int digits = 0, copy_n = n;
        while(copy_n) {
            num[digits++] = copy_n % 10;
            copy_n /= 10;
        }

        decimal[1] = decimal[0] = 1;
        for(int i = 1; i < 21; i ++) {
            dp[i - 1][0] = dp[i - 1][1] = -1;
            decimal[i] = decimal[i - 1] * 10; 
        }

        return dfs(digits - 1, 0, true);
    }
};

int main() {
    Solution s;
    for(int i = 1; i <= 20; i ++) {
        cout << i << " " << s.countDigitOne(i) << endl;
    }
}