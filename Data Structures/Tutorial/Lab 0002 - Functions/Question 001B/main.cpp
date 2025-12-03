// main.cpp
#include <iostream>
using namespace std;

#include "functions.h"   // include header file

int main() {

    int x, y;

    cout << "Enter two integers for functions with parameters: ";
    cin >> x >> y;

    cout << "\n===== TYPE 1 =====\n";
    add1();        // function asks user for numbers

    cout << "\n===== TYPE 2 =====\n";
    int sum2 = add2(x, y);     // get returned value
    cout << "Sum = " << sum2 << endl;

    cout << "\n===== TYPE 3 =====\n";
    add3(x, y);     // prints inside function

    cout << "\n===== TYPE 4 =====\n";
    int sum4 = add4();         // get returned value
    cout << "Sum = " << sum4 << endl;

    return 0;
}
