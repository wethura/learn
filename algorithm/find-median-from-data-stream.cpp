#include<bits/stdc++.h>

using namespace std;

class MedianFinder {
private:
    priority_queue<int> small;
    priority_queue<int, vector<int>, greater<int>> big;
    int count;
public:
    MedianFinder() {
        while(!small.empty()) small.pop();
        while(!big.empty()) big.pop();
        count = 0;
    }
    
    void addNum(int num) {
        count ++;

        if(small.empty()) {
            small.push(num);
            return;
        }

        if(small.top() >= num) small.push(num);
        else big.push(num);

        if(small.size() > big.size() + 1) {
            big.push(small.top());
            small.pop();    
        }
        if(big.size() > small.size() + 1) {
            small.push(big.top());
            big.pop();
        } 
    }
    
    double findMedian() {
        // cout << "small size: " << small.size() << " big size: " << big.size() << endl;
        if(count % 2 == 0) {
            return 0.5f * (small.top() + big.top());
        } else {
            return small.size() > big.size() ? small.top() : big.top();
        }
    }
};
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder* obj = new MedianFinder();
 * obj->addNum(num);
 * double param_2 = obj->findMedian();
 */

int main() {
    MedianFinder finder;
    finder.addNum(1);
    cout << finder.findMedian() << endl;
    finder.addNum(2);
    cout << finder.findMedian() << endl;
    finder.addNum(3);
    cout << finder.findMedian() << endl;
    finder.addNum(4);
    cout << finder.findMedian() << endl;
    finder.addNum(5);
    cout << finder.findMedian() << endl;
    finder.addNum(6);
    cout << finder.findMedian() << endl;
}