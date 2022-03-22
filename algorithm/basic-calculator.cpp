#include<bits/stdc++.h>
using namespace std;

class Solution {
private:
    int ind;
    string s;
public:
    int compute(int a, int b, char op) {
        if(op == '-') return a - b;
        else return a + b;
    }

    int dfs() {
        char op = '+';
        int res = 0, current = 0;

        while(true) {
            if(s[ind] == '(') {
                ind ++; 
                current = dfs();
            } else if(s[ind] == ')') {
                ind ++;
                return compute(res, current, op);
            } else if(s[ind] >= '0' && s[ind] <= '9') {
                current = current * 10 + (s[ind] - '0');
                ind ++;
            } else if(s[ind] == '+' || s[ind] == '-') {
                res = compute(res, current, op);
                current = 0;
                op = s[ind ++];
            } else ind ++;
        }

        return compute(res, current, op);
    }

    int calculate(string rs) {
        s = rs + ")";
        ind = 0;
        return dfs();
    }
};

int main() {
    Solution s;
    cout << s.calculate("(1+(4+5- 22 )-3)+(6+8)") << endl;
}