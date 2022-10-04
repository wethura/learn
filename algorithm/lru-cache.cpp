#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class LRUCache {
private: 
    int cap, size;
    unordered_map<int, int> store, counter;
    queue<int> que;

    void purgeOne() {
        while(!que.empty()) {
            int e = que.front();
            que.pop();

            if(counter[e] <= 1) {
                counter.erase(e);
                store.erase(e);
                size --;
                return;
            } else {
                counter[e] = counter[e] - 1;
            }
        }
    }
public:
    LRUCache(int capacity) {
        cap = capacity;
        size = 0;

        store.clear();
        counter.clear();

        que = queue<int>();
    }
    
    int get(int key) {
        if(counter.find(key) != store.end()) {
            counter[key] = counter[key] + 1;
            que.push(key);

            return store[key];
        } else {
            return -1;
        }
    }
    
    void put(int key, int value) {
        que.push(key);

        store[key] = value;

        if(counter.find(key) == counter.end()) {
            counter[key] = 1;
            size ++;
        } else {
            counter[key] = counter[key] + 1;
        }

        if(size > cap) {
            purgeOne();
        }
    }
};


int main() {
    Timer timer("Execute timer");

// ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
// [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]

    LFUCache cache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    logger(cache.get(1));
    cout << endl;

    cache.put(3, 3);
    logger(cache.get(1));
    logger(cache.get(3));
    cout << endl;

    cache.put(4, 4);

    logger(cache.get(1));
    logger(cache.get(3));
    logger(cache.get(4));


    timer.log("Program execute");

}