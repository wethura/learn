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
    int count(int n, int m, int compare) {
        int ret = 0, tmp;
        for(int i = n; i >= 1; i --) {
            tmp = compare / i;
            if(tmp >= m) {
                ret += i * m;
                return ret;
            } else {
                ret += tmp;
            }
        }

        return ret;
    }
public:
    int findKthNumber(int m, int n, int k) {
        if(n > m) {
            swap(n, m);
        }

        int l = 1, r = m * n, c;
        while(l < r) {
            c = (l + r) / 2;
            if(count(n, m, c) >= k) {
                r = c;
            } else {
                l = c + 1;
            }
        }

        return l;
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

    logger(s.findKthNumber(3, 3, 8));

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
