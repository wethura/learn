#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

struct TreeNode {
    int l = -1, r = -1;
    int val; /*只有叶子节点才有值*/
};


class MyCalendarThree {
private: 
    int cnt = 1, maxn = 0;
    int LM = 0, RM = 1000000000;
    vector<TreeNode> nodes;

    void dfs(int l, int r, int cur_l, int cur_r, int rt, int push_down) {
        if(l == cur_l && r == cur_r && nodes[rt].l == -1 && nodes[rt].r == -1) {
            nodes[rt].val += push_down + 1;
            maxn = max(maxn, nodes[rt].val);
            return;
        }

        int spilt_lr = (cur_l + cur_r) / 2;
        int spilt_rl = spilt_lr + 1;
        
        if()
    }
public:
    MyCalendarThree() {
        cnt = 1;
        while (!nodes.empty()){
            nodes.pop_back();
        }
    }
    
    int book(int start, int end) {


        return maxn;
    }
};

int main() {
    Timer timer("Execute timer");

    MyCalendarThree s;

    timer.log("Program execute");

}