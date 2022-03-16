#include<bits/stdc++.h>
#include "time.h"
using namespace std;

template<typename T> void logger(T e);
template<typename T> void logger(vector<T> arr);
template<typename T> void logger(vector<vector<T>> arrs);


class AllOne {
private:
    // 用于存储当前字符串的数量以及位置
    map<string, pair<int, int>> keyInfos;
    // 从数量多到数量少的字符串
    string keys[50004];
    // 用于描述数量为 index 的字符串的开始到结束的地址
    int start[50004], end[50004];
    // 当前字符串的数量
    int size = 1;
public:
    AllOne() {

    }
    
    void inc(string key) {
        
        if(keyInfos.find(key) == keyInfos.end()) { // 当前key未存在
            keyInfos[key] = make_pair(1, size);
            keys[size] = key;
            if(start[1] == 0 && end[1] == 0) {
                start[1] = end[1] = size;
            } else {
                end[1] = end[1] + 1;
            }
            size ++;
        } else { // 当前key已存在
            // 移动到当前 数量的最前面
            int ind = keyInfos[key].second, num = keyInfos[key].first;
            int ind_change = keyInfos[keys[start[num]]].second, num_change = keyInfos[keys[start[num]]].first;

            // cout << "move: " << ind << "-" << num << " " << ind_change << "-" << num_change << endl;
            // 更新交换位置的字符串的位置信息
            keyInfos[keys[start[num]]] = make_pair(num_change, ind);
            keyInfos[key] = make_pair(num + 1, ind_change);
            // 交换位置

            swap(keys[ind], keys[start[num]]);
            // 大本营没人了
            if(start[num] == end[num]) {
                start[num] = 0;
                end[num] = 0;
            } else {
                start[num] ++;
            }
            // 新贵，哈哈哈
            if(start[num + 1] == 0 && end[num + 1] == 0) {
                start[num + 1] =  end[num + 1] = ind_change;
            } else {
                end[num + 1] ++; // 前面的位置往后+1格
            }
        }
    }
    
    void dec(string key) {
        // assert keyInfos.find(key) != null;

        int ind = keyInfos[key].second, num = keyInfos[key].first;
        int ind_change = keyInfos[keys[end[num]]].second, num_change = keyInfos[keys[end[num]]].first;
        if(num == 1) {
            // 删除该key
            keyInfos.erase(key);
        } else {
            // 只是移动并调整位置
            keyInfos[keys[end[num]]] = make_pair(num_change, ind);
            keyInfos[key] = make_pair(num - 1, ind_change);

        }
        swap(keys[ind], keys[ind_change]);
        if(num == 1) {
            keys[ind_change] = "";
            size --;
            end[1] --;
        } else {
            // 大本营没人了
            if(start[num] == end[num]) {
                start[num] = end[num] = 0;
            } else {
                // 大本营减人
                end[num] --;
            }

            // 新贵，哈哈哈
            if(start[num - 1] == 0 && end[num - 1] == 0) {
                start[num - 1] = end[num - 1] = ind_change;
            } else {
                start[num - 1] --; // 往前扩张1格
            }
        }

    }
    
    string getMaxKey() {
        if(size == 1) return "";
        else return keys[1];
    }
    
    string getMinKey() {
        if(size == 1) return "";
        else return keys[size - 1];
    }

    void log() {
        for(int i = 1; i < 5; i ++) {
            cout << "(" << start[i] << ", " << end[i] << ")";
        } cout << endl;
        for(int i = 1; i < 5; i ++) {
            cout << keys[i] << " - ";
        } cout << endl;
        cout << size - 1 << endl;
    }
};

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne* obj = new AllOne();
 * obj->inc(key);
 * obj->dec(key);
 * string param_3 = obj->getMaxKey();
 * string param_4 = obj->getMinKey();
 */

int main() {
    Timer timer("Execute timer");
    timer.restart();

    AllOne* obj = new AllOne();
    obj->inc("key-1");
    obj->log();
    obj->inc("key");
    obj->log();
    obj->inc("key");
    obj->log();
    obj->inc("key");
    obj->log();
    obj->inc("key-2");
    obj->log();
    obj->inc("key-2");
    obj->log();
    obj->inc("key-2");
    obj->log();
    obj->dec("key");
    obj->log();
    obj->dec("key");
    obj->log();
    obj->dec("key");
    obj->log();
    obj->dec("key-2");
    obj->log();

    // obj->dec("key");
    cout << obj->getMaxKey() << endl;
    cout << obj->getMinKey() << endl;

    // vector<int> arr = {};
    // vector<char> arr = {};

    // vector<vector<int>> arr = {};
    // vector<vector<char>> arr = {};
    


    // vector<int> res = 
    // vector<vector<int>> res = 
    timer.log("Program execute");

    // vector<char> res = 
    // vector<vector<char>> res = 


    // logger(res);
}

template<typename T> void logger(T e) {
    cout << e << endl;
}

template<typename T> void logger(vector<T> arr) {
    for_each(arr.begin(), arr.end(), logger); 
    cout << endl;
}

template<typename T> void logger(vector<vector<T>> arrs) {
    for_each(arrs.begin(), arrs.end(), logger);
    cout << endl;
}
