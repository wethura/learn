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
        // cout << "Index: " << index << endl;
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

class Solution {
public:
    int countRangeSum(vector<int>& nums, int lower, int upper) {
        int len = nums.size(), mapNumber = 2, res = 0;
        long long sum = 0;
        set<long long> hashNumberSet;
        unordered_map<long long, int> hashNumber;
        
        // 离散化有意义的数 1. 将需要离散化的数全部列出来
        for(int i = 0; i <= len; i ++) {
            if(i != 0) sum += nums[i - 1];
            hashNumberSet.insert(sum);
            hashNumberSet.insert(sum - lower);
            hashNumberSet.insert(sum - upper);
        }
        // 离散化有意义的数 2. 按顺序映射离散化的数
        for(long long number: hashNumberSet) {
            // cout << number << " - " << mapNumber << endl;
            hashNumber[number] = mapNumber ++;
        }

        sum = 0;
        TreeShardArray tsa(mapNumber);
        tsa.add(hashNumber[0], 1);
        for(int i = 0; i < len; i ++) {
            sum += nums[i];
            int r = hashNumber[sum - lower], l = hashNumber[sum - upper] - 1;
            res += tsa.query(r) - tsa.query(l);
            // cout << "Res: " << res << endl << " R " << r << " - " << tsa.query(r) << " L " << l << " - " << tsa.query(l) << endl; 
            tsa.add(hashNumber[sum], 1);
        } 
        // cout << endl;

        return res;
    }
};

int main() {
    Solution s;

    vector<int> arr = {-2,5,-1};

    cout << s.countRangeSum(arr, -2, 2) << endl;

}
