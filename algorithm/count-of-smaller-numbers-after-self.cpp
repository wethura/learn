#include<bits/stdc++.h>
using namespace std;

class Solution {
private:
    int bit[20007];  //bit[]为树状数组,num[]为原数组,n为数组长度;
    int OFFSET = 10003;

    int lowbit(int x) {
        return x & -x;
    }

    int query(int i) {  //查询前缀和,1,2,...,i;
        int sum = 0;
        while(i > 0) {
            sum += bit[i];
            i -= lowbit(i);
        }
        return sum;
    }

    int getSum(int i) {		//区间求和,求num[le,ri]的和;
        return query(i);
    }

    void add(int i,int x) {  //将num[i]的值加上x;
        while(i <= n) {
            bit[i] += x;
            i += lowbit(i);
        }
    }
public:
    vector<int> countSmaller(vector<int>& nums) {
        vector<int> res(nums.size(), 0);
        for(int i = nums.size() - 1; i >= 0; i --) {
            res[i] = getSum(nums[i] - 1 + OFFSET);
            add(nums[i] + OFFSET, 1);
        }
        return res;
    }
};

int main() {
    
}