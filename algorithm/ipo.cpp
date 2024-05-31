#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
struct Ipo {
    int cost;
    int value;

    bool operator < (const Ipo& ipo) const {
        return value < ipo.value;
    }
};

class Solution {
private:
    priority_queue<Ipo> ipos;
public:
    int findMaximizedCapital(int k, int w, vector<int>& profits, vector<int>& capital) {
        vector<Ipo> choses = vector<Ipo>();
        for(int i = 0; i < profits.size(); i++) {
            Ipo ipo = Ipo();
            ipo.cost = capital[i];
            ipo.value = profits[i];
            choses.push_back(ipo);
        }

        sort(choses.begin(), choses.end(), [](const Ipo &a, const Ipo &b) {
            return a.cost < b.cost;
        });

        int index = 0, size = choses.size();
        while(index < size && choses[index].cost <= w) {
            ipos.push(choses[index++]);
        }

        int earnMoney = w;

        while(k-- > 0 && !ipos.empty()) {
            earnMoney += ipos.top().value;

            // printf("earn: %d\n", ipos.top().value);
            ipos.pop();

            while(index < size && choses[index].cost <= earnMoney) {
                // printf("add: %d\n", choses[index].value);
                ipos.push(choses[index++]);
            }
        }

        return earnMoney - w;
    }
};


int main() {
    Timer timer("Execute timer");

    Solution s;


    timer.log("Program execute");

    // int k = 2, w = 0;
    // vector<int> profits = {1,2,3};
    // vector<int> capital = {0,1,1};

    int k = 1, w = 2;
    vector<int> profits = {1,2,3};
    vector<int> capital = {1,1,2};

    logger(s.findMaximizedCapital(k, w, profits, capital));
}