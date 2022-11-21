#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * 
 * coding here
 */
struct Node {
    int key, val, freq;
    Node(int _key, int _val, int _freq): key(_key), val(_val), freq(_freq){}
};

class LFUCache {
private:
    int minfreq, capacity;
    unordered_map<int, list<Node>> frequent;
    unordered_map<int, list<Node>::iterator> kvs;
public:
    LFUCache(int cap) {
        frequent.clear();
        kvs.clear();
        minfreq = 0;
        capacity = cap;
    }
    
    int get(int key) {
        if(capacity == 0) return -1;

        auto it = kvs.find(key);
        if(it == kvs.end()) {
            return -1;
        }

        list<Node>::iterator node = it -> second;
        int freq = node->freq; 
        int val = node->val;
        
        frequent[freq].erase(node);
        if(frequent[freq].size() == 0) {
            frequent.erase(freq);
            if(minfreq == freq) minfreq += 1;
        }
        frequent[freq + 1].push_front(Node(key, val, freq + 1));
        kvs[key] = frequent[freq + 1].begin();

        return val;
    }
    
    void put(int key, int val) {
        if(capacity == 0) return;

        auto it = kvs.find(key);
        if(it == kvs.end()) {
            if(capacity == kvs.size()) {
                // delete a member in minFreq
                Node deleteNode = frequent[minfreq].back();
                kvs.erase(deleteNode.key);
                frequent[minfreq].pop_back();
            }

            frequent[1].push_front(Node(key, val, 1));
            kvs[key] = frequent[1].begin();
            minfreq = 1;
        } else {
            list<Node>::iterator node = it -> second;
            int freq = node->freq;
            frequent[freq].erase(node);
            if(frequent[freq].size() == 0) {
                frequent.erase(freq);
                if(minfreq == freq) minfreq += 1;
            }
            frequent[freq + 1].push_front(Node(key, val, freq + 1));
            kvs[key] = frequent[freq + 1].begin();
        }
    }
};


int main() {
    Timer timer("Execute timer");

    LFUCache cache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    logger(cache.get(1));
    logger("-------");

    cache.put(3, 3);
    logger(cache.get(2));
    logger(cache.get(3));
    logger("-------");

    cache.put(4, 4);
    logger(cache.get(1));
    logger(cache.get(3));
    logger(cache.get(3));
    logger(cache.get(3));
    logger(cache.get(3));
    logger(cache.get(4));


    timer.log("Program execute");

}