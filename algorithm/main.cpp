#include<bits/stdc++.h>
#include "logger.h"
#include "time.h"

using namespace std;

int main() {

    cout << "Hello World!" << endl;
    string str = "single_word";
    logger(str);

    vector<string> arr = {"arr_1", "arr_2"};
    logger(arr);

    vector<vector<string>> arrs = {{"arr_1_1", "arr_1_2"}, {"arr_2_1", "arr_2_2"}};
    logger(arrs);

    return 0;
}