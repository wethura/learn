#include<bits/stdc++.h>
#include "time.h"
#include "logger.h"
using namespace std;

/**
 * coding here
 */
class Node {
public:
    int val;
    vector<Node*> neighbors;
    Node() {
        val = 0;
        neighbors = vector<Node*>();
    }
    Node(int _val) {
        val = _val;
        neighbors = vector<Node*>();
    }
    Node(int _val, vector<Node*> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};

class Solution {
private:
    map<int, Node*> nodes;
    set<int> neighborIsOk;

    void clone(Node* from, Node* to) {
        
        neighborIsOk.insert(from->val);
        
        for(Node* f : from->neighbors) {
            Node* t;
            if (nodes.count(f->val)) {
                t = nodes[f->val];
            } else {
                t = new Node(f->val);
                nodes[f->val] = t;
            }
            to->neighbors.push_back(t);

            if (!neighborIsOk.count(f->val)) {
                clone(f, t);
            }
        }
    }
public:
    Node* cloneGraph(Node* from) {

        if(from == NULL) {
            return NULL;
        }

        neighborIsOk.clear();
        nodes.clear();

        Node* target = new Node(from->val);
        nodes[from->val] = target;

        clone(from, target);

        return target;
    }
};


int main() {
    Timer timer("Execute timer");

    // Solution s;


    timer.log("Program execute");

}