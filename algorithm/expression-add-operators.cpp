#include<bits/stdc++.h>
using namespace std;

class Solution {
private: 
    long long compute(vector<char>& expr) {
        long long res = 0, mul = 1, tmp = 0;
        int op = 0, l = expr.size(); /*0:+ 1:-*/
        for(int i = 0; i < l; i ++) {
            if(expr[i] >= '0' && expr[i] <= '9') {
                tmp = tmp * 10 + (expr[i] - '0');
            } else if(expr[i] == '*') {
                mul *= tmp;
                tmp = 0;
            } else {
                res = res + ((op == 0) ? (mul * tmp) : (- mul * tmp));
                op = (expr[i] == '+') ? 0 : 1;
                mul = 1;
                tmp = 0;
            }
        }

        res = res +((op == 0) ? (mul * tmp) : (-mul * tmp));

        return res;
    }

    void computeDfs(string num, vector<char>& expr, int ind, long long target, vector<string>& res) {
        if(ind == num.size()) {
            if(compute(expr) == target) {
                res.push_back(string(expr.begin(), expr.end()));
                cout << string(expr.begin(), expr.end()) << " - " << compute(expr) << " - " << target << endl;
            }
            return ;
        }

        expr.push_back('*');expr.push_back(num[ind]);
        computeDfs(num, expr, ind + 1, target, res);
        expr.pop_back();expr.pop_back();

        expr.push_back('+');expr.push_back(num[ind]);
        computeDfs(num, expr, ind + 1, target, res);
        expr.pop_back();expr.pop_back();

        expr.push_back('-');expr.push_back(num[ind]);
        computeDfs(num, expr, ind + 1, target, res);
        expr.pop_back();expr.pop_back();


        if(expr[expr.size() - 1] != '0' || (expr[expr.size() - 1] == '0' && (expr.size() > 1 && expr[expr.size() - 2] != '+' && expr[expr.size() - 2] != '-' && expr[expr.size() - 2] != '*'))) {
            expr.push_back(num[ind]);
            computeDfs(num, expr, ind + 1, target, res);
            expr.pop_back();
        }


    }
public:
    vector<string> addOperators(string num, int target) {
        vector<string> res;
        vector<char> expr{num[0]};
        computeDfs(num, expr, 1, target, res);
        return res;
    }
};

int main() {
    Solution s;
    vector<string> res = s.addOperators("232", 8);
    for(string e: res) {
        cout << e << endl;
    }
}