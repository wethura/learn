#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {

        int i, j, size = triangle.size();

        for(i = 1; i < size; i ++ ) {
            for(j = 0; j < i + 1; j ++) {
                if(j == 0) {
                    triangle[i][0] += triangle[i - 1][0];
                } else if(j == i) {
                    triangle[i][j] += triangle[i - 1][j - 1];
                } else {
                    triangle[i][j] = min(triangle[i][j] + triangle[i - 1][j], 
                                        triangle[i][j] + triangle[i - 1][j - 1]);
                }
            }
        }

        int ans = triangle[size - 1][0];
        for(i = 1; i < size; i ++) {
            ans = min(ans, triangle[size - 1][i]);
        }

        return ans;
    }
};

int main(int argc, char const *argv[])
{
    vector<vector<int>> triangle = {{2}, {3, 4}, {6,5,7}, {4,1,8,3}};
    Solution s;
    
    logger(s.minimumTotal(triangle));

    return 0;
}
