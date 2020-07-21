#include <iostream>
#include <set>
#include <utility>

using namespace std;

constexpr int inf = 1e9;

/*
void debugger(const set< pair < int, int > >& s) {
    cout<<"print current set\n";
    for(auto it: s) {
        cout<<it.first<<" "<<it.second<<'\n';
    }
    return;
}
*/
int main(int argc, char const *argv[]) {
    int n, k, a;
    cin>>n>>k>>a;
    int m;
    cin>>m;
    int possible = n / a;
    int step = 0;
    set< pair< int, int > > s {{1,n}};
    while(m--) {
        step += 1;
        int x;
        cin>>x;
        // lower_bound returns an iterator pointing to the first element that is not less than (i.e. greater or equal to) key.
        auto it = prev(s.lower_bound({x, inf}));
        s.erase(it);
        int l = (*it).first, r = (*it).second;
        possible -= (r - l + 1) / a;
        int l1 = l, r1 = x - 1;
        if(r1 >= l1) {
            possible += (r1 - l1 + 1) / a;
            s.insert({l1, r1});
        }
        int l2 = x + 1, r2 = r;
        if(r2 >= l2) {
            possible += (r2 - l2 + 1) / a;
            s.insert({l2, r2}); 
        }
        if (possible < k) {
            cout<<step<<'\n';
            return 0;
        }
    }
    cout<<"-1\n";
    return 0;
}