#include<bits/stdc++.h>
using namespace std;

class Solution {
private:
    vector<int> maxSub(vector<int> vec, int k) {
        stack<int> sta;
        int len = vec.size();
        for(int i = 0; i < len; i ++) {
            while(!sta.empty() && vec[i] > sta.top() && len - i + sta.size() > k) sta.pop();
            sta.push(vec[i]);
        }

        return vector<int>(&sta.top() + 1 - sta.size(), &sta.top() + 1); 
    }
    int compare(vector<int>& v1, int ind1, vector<int>& v2, int ind2) {
        while(ind1 < v1.size() && ind2 < v2.size()) {
            int t = v1[ind1 ++] - v2[ind2 ++];
            if(t != 0) return t;
        }
        return v1.size() - ind1 - v2.size() + ind2;
    }
    vector<int> merge(vector<int> vec1, vector<int> vec2, int k) {
        // cout << "Pre-Merge: " << endl;
        // for(int e: vec1) cout << e << " "; cout << endl;
        // for(int e: vec2) cout << e << " "; cout << endl;

        vector<int> res;
        int ind1 = 0, ind2 = 0;
        while(res.size() < k && (ind1 < vec1.size() || ind2 < vec2.size())) {
            if(compare(vec1, ind1, vec2, ind2) > 0) res.push_back(vec1[ind1 ++]);
            else res.push_back(vec2[ind2 ++]);
        }
        // cout << "Merged: " << endl;
        // for(int e: res) cout << e << " "; cout << endl;
        return res;
    }
public:
    vector<int> maxNumber(vector<int>& nums1, vector<int>& nums2, int k) {
        vector<int> res(k, 0), n1, n2, tmp;

        for(int i = ((k <= nums2.size()) ? 0 : k - nums2.size()); i <= ((k > nums1.size()) ? nums1.size() : k); i ++) {
            cout << "A: " << i << " B: " << k - i << endl;
            tmp = merge(maxSub(nums1, i), maxSub(nums2, k - i), k);
            cout << "A: " << i << " B: " << k - i << endl;
            if(compare(tmp, 0, res, 0) > 0) swap(tmp, res);
        }

        return res;
    }
};

int main() {

// [7,6,4,0,5,8,2,2,6,8,2,0,5,3,0,8,7,7,7,3,3,2,0,8,4,0,1,2]
// [1,9,2,3,9,6,1,5,8,7,9,9,1,5,4,1,7,2,2,2,1,1,7,2,8,1,2,6,6,6,5,1,9,9,8,1,4,6,3,3,1,5,0,0,0,5,6,6,2,3,6,6,0,3,5,4,2,4,0,8,2,4,6,1,0,1,1,4,0,3,0,9,2,5,6,3,4,7,0,7,2,4,1,0,6,6,9,6,5,7,2,4,7,5,7,1,9,3,5,8,8,7,2,4,7,9,7,4,5,1,6,3,5,5,8,8,6,2,8,0,9,9,0,3,8,1,3,0,3,5,7,7,6,4,2,5,8,3,3,5]
// 140
    vector<int> arr1 = {7,6,4,0,5,8,2,2,6,8,2,0,5,3,0,8,7,7,7,3,3,2,0,8,4,0,1,2};
    vector<int> arr2 = {1,9,2,3,9,6,1,5,8,7,9,9,1,5,4,1,7,2,2,2,1,1,7,2,8,1,2,6,6,6,5,1,9,9,8,1,4,6,3,3,1,5,0,0,0,5,6,6,2,3,6,6,0,3,5,4,2,4,0,8,2,4,6,1,0,1,1,4,0,3,0,9,2,5,6,3,4,7,0,7,2,4,1,0,6,6,9,6,5,7,2,4,7,5,7,1,9,3,5,8,8,7,2,4,7,9,7,4,5,1,6,3,5,5,8,8,6,2,8,0,9,9,0,3,8,1,3,0,3,5,7,7,6,4,2,5,8,3,3,5};
    int k = 140;
    Solution s;
    vector<int> res = s.maxNumber(arr1, arr2, k);
    for(int e: res) cout << e << " ";
}