#include <iostream>   // Allows input and output (cin, cout)
#include <iomanip>    // Allows formatting (setw, left, right, setprecision)
#include <string>     // Allows use of string type
using namespace std;

int main() {
    // Declare variables to store user information
    string name, gender, address;
    int age;
    double ewallet;

    // ===============================
    //          USER INPUT
    // ===============================

    cout << left << setw(30) << "Student Name" << ":";
    // Ask for student name. getline() reads the whole line, including spaces.
    getline(cin, name);

    cout << left << setw(30) << "Student Gender" << ":";
    // cin >> reads only one word (no spaces)
    cin >> gender;

    cout << left << setw(30) << "Student Age" << ":";
    // Read student's age (integer)
    cin >> age;

    cout << left << setw(30) << "Student Home Address" << ":";
    cin.ignore();
    // Clear leftover newline before using getline again
    getline(cin, address);
    // Reads full address with spaces

    cout << left << setw(30) << "E-wallet Amount" << ":RM ";
    // Read e-wallet balance
    cin >> ewallet;

    cout << endl;
    cout << "Student details as below: " << endl;

    // ===============================
    //       PRINT SEPARATOR LINE
    // ===============================
    for (int i = 0; i <= 101; i++)
        cout << "=";      // Print 102 '=' symbols
    cout << endl;

    // ===============================
    //       PRINT TABLE HEADERS
    // ===============================
    cout << left << setw(20) << "| Name"
         << setw(20) << "| Age"
         << setw(20) << "| Gender"
         << setw(20) << "| Address"
         << setw(20) << "| E-wallet Amount"
         << " |" << endl;

    // Print another separator line
    for (int i = 0; i <= 101; i++)
        cout << "=";
    cout << endl;

    // ===============================
    //   PRINT STUDENT INFORMATION
    // ===============================
    cout << left << setw(20) << ("| " + name)             // Print name
         << setw(20) << ("| " + to_string(age))          // Convert age (int) to string
         << setw(20) << ("| " + gender)                  // Print gender
         << setw(20) << ("| " + address)                 // Print address
         // Print e-wallet value with RM + 2 decimal places
         << "| RM " << fixed << setprecision(2) << setw(16) << ewallet
         << "|" << endl;

    // Final separator line
    for (int i = 0; i <= 101; i++)
        cout << "=";
    cout << endl;

    return 0;   // End program
}
