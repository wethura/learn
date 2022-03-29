#include<bits/stdc++.h>
using namespace std;

class Solution {
private:
    int dp[20];
    void init() {
        dp[0] = dp[1] = 1;
        for(int i = 2; i < 20; i ++) {
            for(int j = 0; j < i; j ++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        cout << "{"
        for(int i = 0; i < 20; i ++) {
            cout << dp[i] << ", ";
        } cout << "}" << endl;
    }
public:
    int numTrees(int n) {
        init();
        return dp[n];
    }
};

int main() {

}