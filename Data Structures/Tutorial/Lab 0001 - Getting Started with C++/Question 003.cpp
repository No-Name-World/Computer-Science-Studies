#include <iostream>   // allows input and output (cin, cout)
#include <iomanip>    // allows formatting (not really used here, but okay)
#include <string>     // allows string type (not used here)
using namespace std;

int main() {
    int HH, MM, SS;   // variables to store hours, minutes, seconds
    char c1, c2;      // variables to store the ':' characters

    while (true) {    // repeat until user enters correct format
        cout << "Please enter your elapsed time (in HH:MM:SS format) = ";
        cin >> HH >> c1 >> MM >> c2 >> SS;
        // Example input: 02:15:30
        // HH = 2 , c1=':', MM=15 , c2=':', SS=30

        // Check format is correct (must be HH:MM:SS)
        if (cin.fail() || c1 != ':' || c2 != ':') {
            cin.clear();              // remove error state (fix fail state)
            cin.ignore(1000, '\n');   // ignore wrong input in buffer
            cout << "Invalid input!" << endl;
            continue;                 // go back and ask again
        }

        // Check valid ranges of time
        if (HH < 0 || HH > 23 ||     // hours must be 0–23
            MM < 0 || MM > 59 ||     // minutes must be 0–59
            SS < 0 || SS > 59) {     // seconds must be 0–59
            cout << "Invalid input!" << endl;
            continue;                // go back and ask again
        }

        // Input is valid → exit the loop
        break;
    }

    // Convert HH:MM:SS into total seconds
    int seconds = HH * 3600 + MM * 60 + SS;

    // Show the result
    cout << "Elapsed time in seconds = " << seconds << " seconds." << endl;

    return 0;   // end the program
}
