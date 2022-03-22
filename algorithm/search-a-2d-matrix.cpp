#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int row = matrix.size(), col = matrix[0].size();
        int l = 0, r = row * col - 1, m, x, y;
        while(l < r) {
            m = (l + r) >> 1;
            x = m / row, y = m % row;
            if(matrix[x][y] == target) return true;
            else if(matrix[x][y] > target) r = m - 1;
            else l = m + 1;
        }
        return false;
    }
};

int main() {
    
}