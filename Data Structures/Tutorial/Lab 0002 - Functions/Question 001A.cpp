#include <iostream>     // allows us to use cout and cin
using namespace std;    // allows us to skip writing std::

/* ===== Function Prototypes =====
   These tell the compiler that these functions exist below.
*/
void add1();             // Type 1: no return, no parameters
int add2(int, int);      // Type 2: return, with parameters
void add3(int, int);     // Type 3: no return, with parameters
int add4();              // Type 4: return, no parameters


int main() {

    // Variables to store numbers for functions with parameters
    int x, y;

    cout << "Enter two integers for function with parameters: ";
    cin >> x >> y;

    cout << endl;



    /* ===== TYPE 1 ===== */
    cout << "Type 1: Function without return and without parameters\n";
    add1();      // This function will ask user for numbers inside itself
    cout << endl;

    /* ===== TYPE 2 ===== */
    cout << "Type 2: Function with return and with parameters\n";
    int result2 = add2(x, y);   // The function returns the sum
    cout << "Sum = " << result2 << endl;
    cout << endl;

    /* ===== TYPE 3 ===== */
    cout << "Type 3: Function without return and with parameters\n";
    add3(x, y);     // Function prints sum itself
    cout << endl;

    /* ===== TYPE 4 ===== */
    cout << "Type 4: Function with return and without parameters\n";
    int result4 = add4();       // Function returns sum
    cout << "Sum = " << result4 << endl;
    cout << endl;

    return 0;     // End of program
}


/* ================================================================
   TYPE 1: Function without return AND without parameters
   - Does NOT get numbers from main()
   - Does NOT return a value
   - It will ask user for two numbers inside the function
================================================================ */
void add1() {
    int a, b, sum;

    cout << "Enter first number: ";
    cin >> a;
    cout << "Enter second number: ";
    cin >> b;

    sum = a + b;            // do the addition
    cout << "Sum = " << sum << endl;   // print result
}




/* ================================================================
   TYPE 2: Function WITH return AND WITH parameters
   - main() gives two numbers (parameters)
   - function returns the sum back to main()
================================================================ */
int add2(int a, int b) {
    int sum = a + b;        // calculate sum
    return sum;             // send result back to main()
}


/* ================================================================
   TYPE 3: Function WITHOUT return BUT WITH parameters
   - main() gives two numbers
   - function calculates and PRINTS the result
================================================================ */
void add3(int a, int b) {
    int sum = a + b;        // calculate sum
    cout << "Sum = " << sum << endl;   // print inside function
}


/* ================================================================
   TYPE 4: Function WITH return BUT WITHOUT parameters
   - function will ask user for numbers
   - then return the result back to main()
================================================================ */
int add4() {
    int a, b;

    cout << "Enter first number: ";
    cin >> a;
    cout << "Enter second number: ";
    cin >> b;

    return a + b;           // return the sum
}
