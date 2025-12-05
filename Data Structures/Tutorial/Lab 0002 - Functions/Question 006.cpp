#include <iostream>   // Needed for cout (print to screen) and cin (read from keyboard)
#include <string>     // Needed to use string type for month name
#include <cctype>     // Needed for toupper() and tolower() to change letter case
using namespace std;  // So we do not need to write std::cout, std::cin, etc.


// ----------------------------------------------------------
// Function Prototypes (just telling the compiler they exist)
// ----------------------------------------------------------

// This function will change the month string so that
// the first letter is uppercase and the rest are lowercase.
// Example: "aUGust"  -> "August"
void ConvertLetterString(string &monthName);

// This function will look at the month name and return
// how many days are in that month.
// Example: "February" -> 28, "June" -> 30, "July" -> 31
int FindDaysInMonth(const string &monthName);

// This function will ask the user, day by day, to enter
// H (hot), R (rainy), or C (cloudy).
// It will count how many days are hot, rainy and cloudy.
// The counts are returned using "reference" parameters (&).
void weatherReport(int totalDays,
                   int &hotDays, int &rainyDays, int &cloudyDays);

// This function will show the final report:
// - number of hot, rainy and cloudy days
// - a simple graph using stars (*)
void displayReport(const string &monthName, int totalDays,
                   int hotDays, int rainyDays, int cloudyDays);


// ----------------------------------------------------------
// main() function - the starting point of the program
// ----------------------------------------------------------
int main()
{
    string month;        // This will store the month name entered by the user.
    int daysInMonth;     // This will store how many days are in that month.
    int hotDays;         // Number of hot days in that month.
    int rainyDays;       // Number of rainy days in that month.
    int cloudyDays;      // Number of cloudy days in that month.
    char again;          // To ask if the user wants to process another month.

    do                   // "do ... while" loop so it runs at least one time.
    {
        // ----------------------------------------------------------
        // 1. Get month name from the user
        // ----------------------------------------------------------
        cout << "Enter your month (example: August): ";
        // Use getline so the user can type a full word with spaces if they want
        // (though month usually has no spaces).
        getline(cin, month);

        // If previous cin left a newline in the buffer, getline might read it.
        // So if the user accidentally hits Enter extra, we handle empty string.
        while (month == "")          // If nothing was typed (empty string)...
        {
            cout << "Please enter a month name: ";
            getline(cin, month);     // Ask again.
        }

        // Make the month look nice: first letter uppercase, others lowercase.
        ConvertLetterString(month);

        // ----------------------------------------------------------
        // 2. Find number of days in that month
        // ----------------------------------------------------------
        daysInMonth = FindDaysInMonth(month);  // Call our function.

        // If daysInMonth is 0, that means the month name was not recognised.
        if (daysInMonth == 0)
        {
            cout << "Invalid month name. Please try again." << endl << endl;
            // We skip to the next loop cycle and ask for month again.
            continue;  // Go back to the beginning of the do-while loop.
        }

        // ----------------------------------------------------------
        // 3. Ask for weather for each day and count hot/rainy/cloudy
        // ----------------------------------------------------------
        weatherReport(daysInMonth, hotDays, rainyDays, cloudyDays);

        // ----------------------------------------------------------
        // 4. Display the final report and the graph
        // ----------------------------------------------------------
        displayReport(month, daysInMonth, hotDays, rainyDays, cloudyDays);

        // ----------------------------------------------------------
        // 5. Ask user if they want to enter another month
        // ----------------------------------------------------------
        cout << endl;
        cout << "Do you want to enter another month? (Y = Yes, any other key = No): ";
        cin >> again;          // Read a single character.
        cin.ignore(1000, '\n'); // Remove the rest of the line from the input buffer.

        cout << endl;          // Print a blank line for better spacing.

    } while (again == 'Y' || again == 'y');   // Repeat if user typed Y or y.

    // When we get here, the user chose not to continue.
    cout << "Thank you for using the weather report program." << endl;

    return 0;  // End of program. Return 0 means "no error".
}



// ----------------------------------------------------------
// ConvertLetterString()
// This function changes the month string so that:
//   - the first character is uppercase
//   - all other characters are lowercase
// Example: "fEBruARy" becomes "February"
// We pass monthName by reference (&) so we can modify it directly.
// ----------------------------------------------------------
void ConvertLetterString(string &monthName)
{
    if (monthName.length() == 0) // If the string is empty, nothing to do.
        return;

    // Convert the first character to uppercase.
    monthName[0] = toupper(monthName[0]);

    // Convert the rest of the characters to lowercase.
    for (size_t i = 1; i < monthName.length(); ++i)
    {
        monthName[i] = tolower(monthName[i]);
    }
}



// ----------------------------------------------------------
// FindDaysInMonth()
// This function checks the monthName and returns how many days
// that month normally has (leap year is ignored for simplicity).
//
// If the month name is not recognised, it returns 0.
// ----------------------------------------------------------
int FindDaysInMonth(const string &monthName)
{
    // Months that have 31 days.
    if (monthName == "January"  || monthName == "March" ||
        monthName == "May"      || monthName == "July"  ||
        monthName == "August"   || monthName == "October" ||
        monthName == "December")
    {
        return 31;
    }
    // Months that have 30 days.
    else if (monthName == "April" || monthName == "June" ||
             monthName == "September" || monthName == "November")
    {
        return 30;
    }
    // February – we use 28 days for this assignment (no leap year check here).
    else if (monthName == "February")
    {
        return 28;
    }
    else
    {
        // If it is not any of the known month names, return 0.
        // main() will treat 0 as invalid month.
        return 0;
    }
}



// ----------------------------------------------------------
// weatherReport()
// This function asks the user to enter the weather for each day
// of the month. For each day, user should type:
//
//   H or h  -> Hot
//   R or r  -> Rainy
//   C or c  -> Cloudy
//
// It will count how many days are hot, rainy, and cloudy.
//
// totalDays:  number of days in the month
// hotDays, rainyDays, cloudyDays: passed by reference, so we can
//                                 change the values in the caller.
// ----------------------------------------------------------
void weatherReport(int totalDays,
                   int &hotDays, int &rainyDays, int &cloudyDays)
{
    char code;  // To store the letter H, R, or C typed by the user.

    // Start all counts at 0.
    hotDays    = 0;
    rainyDays  = 0;
    cloudyDays = 0;

    cout << endl;
    cout << "Please enter the weather for each day:" << endl;
    cout << "Use 'H' for Hot, 'R' for Rainy, 'C' for Cloudy." << endl;
    cout << "Example input for Day 1: h" << endl << endl;

    // Loop from day 1 to the last day of the month.
    for (int day = 1; day <= totalDays; ++day)
    {
        while (true)  // Loop until user gives a valid letter for that day.
        {
            cout << "Day " << day
                 << " - Enter 'H' for Hot, 'R' for Rainy, 'C' for Cloudy: ";
            cin >> code;             // Read a single character from the user.

            // Convert code to uppercase so we only check 'H', 'R', 'C'.
            code = toupper(code);

            if (code == 'H')
            {
                hotDays++;          // Add 1 to hotDays.
                break;              // Exit the inner while loop and go to next day.
            }
            else if (code == 'R')
            {
                rainyDays++;        // Add 1 to rainyDays.
                break;
            }
            else if (code == 'C')
            {
                cloudyDays++;       // Add 1 to cloudyDays.
                break;
            }
            else
            {
                // If the user types something else (like X or 5),
                // show a message and ask again for the same day.
                cout << "Invalid input. Please enter only H, R, or C." << endl;
            }
        }
    }

    cout << endl;  // Blank line after reading all days.
}



// ----------------------------------------------------------
// displayReport()
// This function shows:
//   - Total days in the month
//   - Number of hot, rainy and cloudy days
//   - A simple star graph
//
// For the graph, each star (*) represents 1 day.
// Example: 5 hot days -> "Hot    : *****"
// ----------------------------------------------------------
void displayReport(const string &monthName, int totalDays,
                   int hotDays, int rainyDays, int cloudyDays)
{
    cout << "--------------------------------------------------" << endl;
    cout << "Weather report for " << monthName
         << " (" << totalDays << " days)" << endl;
    cout << "--------------------------------------------------" << endl;

    // Print numeric summary.
    cout << "Number of hot days this month    : " << hotDays    << endl;
    cout << "Number of rainy days this month  : " << rainyDays  << endl;
    cout << "Number of cloudy days this month : " << cloudyDays << endl;
    cout << endl;

    // Now display a simple star graph.
    cout << "Weather analysis (each * = 1 day):" << endl;

    // --- Hot days line ---
    cout << "Hot    : ";
    for (int i = 0; i < hotDays; ++i)   // Print one * for each hot day.
    {
        cout << "*";
    }
    cout << endl;

    // --- Rainy days line ---
    cout << "Rainy  : ";
    for (int i = 0; i < rainyDays; ++i) // Print one * for each rainy day.
    {
        cout << "*";
    }
    cout << endl;

    // --- Cloudy days line ---
    cout << "Cloudy : ";
    for (int i = 0; i < cloudyDays; ++i) // Print one * for each cloudy day.
    {
        cout << "*";
    }
    cout << endl;

    cout << "--------------------------------------------------" << endl;
}
