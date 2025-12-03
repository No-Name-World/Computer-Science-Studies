#include <iostream>     // Allows input and output (cin, cout)
#include <iomanip>      // Allows formatting (setw, left, right, setprecision)
#include <string>       // Allows using string type
using namespace std;

int main() {
    // Declare variables to store user data
    string name, gender, address;
    int age;
    double ewallet;

    // ------------------------------
    // User Input Section
    // ------------------------------

    // Ask for student name, format text to the left with width 30
    cout << left << setw(30) << "Student Name" << ":";
    getline(cin, name);   // Read full name including spaces

    // Ask for gender
    cout << left << setw(30) << "Student Gender" << ":";
    cin >> gender;        // Read one word (e.g., Male/Female)

    // Ask for age
    cout << left << setw(30) << "Student Age" << ":";
    cin >> age;

    // Ask for home address
    cout << left << setw(30) << "Student Home Address" << ":";
    cin.ignore();         // Clear leftover newline from previous input
    getline(cin, address); // Read full address including spaces

    // Ask for e-wallet amount
    cout << left << setw(30) << "E-wallet Amount" << ":RM ";
    cin >> ewallet;

    cout << endl; // Just print a blank line

    // ------------------------------
    // Output Table Section
    // ------------------------------

    // Print top border of the table
    cout << "=================================================================" << endl;
    cout << "| Student Details as below:                                     |" << endl;
    cout << "=================================================================" << endl;

    // Print each detail in table format
    cout << "| Name     : "
         << setw(50) << right << name    // Align name to the right within 50 characters
         << " |" << endl;

    cout << "| Age      : "
         << setw(50) << right << age
         << " |" << endl;

    cout << "| Gender   : "
         << setw(50) << right << gender
         << " |" << endl;

    cout << "| Address  : "
         << setw(50) << right << address
         << " |" << endl;

    // Print e-wallet with 2 decimal places
    cout << "| E-Wallet : RM"
         << setw(48) << right << fixed << setprecision(2) << ewallet
         << " |" << endl;

    // Print bottom border
    cout << "=================================================================" << endl;

    return 0;   // End of program
}
