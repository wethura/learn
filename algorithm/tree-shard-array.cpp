#include<bits/stdc++.h>
using namespace std;

class TreeShardArray {
private:
    int n;
    vector<int> tree;
    constexpr int lower_bit(int x) {
        return x & (-x);
    }
public:
    TreeShardArray(int size):n(size), tree(size + 1){}

    void add(int index, int add) {
        while(index < n) {
            tree[index] += add;
            index += lower_bit(index);
        }
    }

    int query(int index) {
        int res = 0;
        while(index) {
            res += tree[index];
            index -= lower_bit(index);
        }
        return res;
    }
};

int main() {
    TreeShardArray bsa(100);
    for(int i = 1; i < 100; i ++) {
        bsa.add(i, i);
    }
    assert(bsa.query(10) == 55); 
}