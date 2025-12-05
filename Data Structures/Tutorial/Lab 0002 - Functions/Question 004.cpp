#include <iostream>   // This library lets us use cout (print) and cin (input).
#include <iomanip>    // This library lets us use setw() to control spacing when printing.
using namespace std;  // This line lets us write "cout" instead of "std::cout".

// ------------------------------------------------------------------
// Function declarations (prototypes)
// We tell the compiler that these functions exist and what they return.
// We will define them properly after main().
// ------------------------------------------------------------------
int yearInputValidation();                           // This will ask user for a year and check if it is valid.
int monthInputValidation();                          // This will ask user for a month and check if it is valid.
bool isLeapYear(int year);                           // This will check if a year is a leap year (true/false).
int findDaysInMonth(int month, int year, string &monthName);
// This will return the number of days in the month and also set monthName to "January", "February", etc.

int getStartDay(int year, int month);
// This will calculate which day of the week the 1st of that month falls on.
// 0 = Sunday, 1 = Monday, ..., 6 = Saturday.

void displayCalendar(int year, int month, string monthName, int daysInMonth, int startDay);
// This will print the monthly calendar on the screen.


// ------------------------------------------------------------------
// main() function
// The program starts running from here.
// ------------------------------------------------------------------
int main()
{
    int year;           // To store the year that the user enters.
    int month;          // To store the month that the user enters.
    int daysInMonth;    // To store how many days are in that month (28, 29, 30, 31).
    int startDay;       // To store which day of the week the 1st of the month starts on.
    string monthName;   // To store the name of the month in words, like "June".
    int choice;         // To store user's choice to continue or stop.

    // We will use a loop so that the user can see more than one month
    // (just like your example: "Do you want to see other month?").
    do
    {
        // Ask and validate the year.
        year = yearInputValidation();   // This calls the function. It returns a valid year.

        // Ask and validate the month.
        month = monthInputValidation(); // This calls the function. It returns a valid month (1–12).

        // Find how many days are in that month and also get the month name.
        // We pass monthName as reference (&monthName) so the function can change its value.
        daysInMonth = findDaysInMonth(month, year, monthName);

        // Find which day of the week the 1st day of this month falls on.
        // Example: 0 = Sunday, 1 = Monday, etc.
        startDay = getStartDay(year, month);

        // Now display the calendar using all this information.
        displayCalendar(year, month, monthName, daysInMonth, startDay);

        // Ask the user if they want to see another month.
        cout << endl;
        cout << "Do you want to see other month? 1 = Yes, others = No: ";
        cin >> choice;  // Read the choice from the user.

        cout << endl;   // Print an empty line for nicer spacing.

    } while (choice == 1);  // If user types 1, loop again. Otherwise, program ends.

    return 0;  // Return 0 means program ended successfully.
}


// ------------------------------------------------------------------
// yearInputValidation()
// Ask the user to enter a year and make sure it is valid.
// Here we simply check that year is >= 1.
// You can change the rule if your assignment wants different limits.
// ------------------------------------------------------------------
int yearInputValidation()
{
    int year;  // Variable to store the input year.

    while (true)  // "while (true)" means an endless loop. We will break when input is valid.
    {
        cout << "Enter calendar's year: ";
        cin >> year;    // Read the year from the user.

        if (year >= 1)  // Check if the year is valid (1 or more).
        {
            // If valid, break the loop.
            break;
        }
        else
        {
            // If not valid, show an error message and ask again.
            cout << "Invalid year. Please enter a positive number." << endl;
        }
    }

    return year;  // Give back the valid year to the place where this function was called.
}


// ------------------------------------------------------------------
// monthInputValidation()
// Ask the user to enter a month and make sure it is between 1 and 12.
// 1 = January, 2 = February, ..., 12 = December.
// ------------------------------------------------------------------
int monthInputValidation()
{
    int month;  // Variable to store the input month.

    while (true)  // Loop until the user enters a valid month.
    {
        cout << "Enter calendar's month: ";
        cin >> month;   // Read the month from the user.

        if (month >= 1 && month <= 12)  // Check if month is between 1 and 12.
        {
            break;       // Valid month, so exit the loop.
        }
        else
        {
            cout << "Invalid month. Please enter a value between 1 and 12." << endl;
        }
    }

    return month;  // Give back the valid month.
}


// ------------------------------------------------------------------
// isLeapYear()
// A leap year has 29 days in February instead of 28.
// Rule for leap year (Gregorian calendar):
//   - If year is divisible by 400 → leap year.
//   - Else if year is divisible by 100 → NOT a leap year.
//   - Else if year is divisible by 4 → leap year.
//   - Else → NOT a leap year.
// ------------------------------------------------------------------
bool isLeapYear(int year)
{
    // Check the conditions one by one.
    if (year % 400 == 0)          // If year divided by 400 leaves no remainder
        return true;              // → it's a leap year.
    else if (year % 100 == 0)     // Else if year divisible by 100
        return false;             // → NOT a leap year.
    else if (year % 4 == 0)       // Else if year divisible by 4
        return true;              // → it's a leap year.
    else
        return false;             // Otherwise, it's not a leap year.
}


// ------------------------------------------------------------------
// findDaysInMonth()
// This function does two things:
//  1. It returns the number of days in the given month of that year.
//  2. It also sets the "monthName" variable to the correct month name.
// The "monthName" parameter has "&" in front (string &monthName).
// That means it is passed by reference, so this function can change it.
// ------------------------------------------------------------------
int findDaysInMonth(int month, int year, string &monthName)
{
    int days;  // To store number of days.

    // Use switch-case to handle each month.
    switch (month)
    {
        case 1:
            monthName = "January";
            days = 31;
            break;
        case 2:
            monthName = "February";
            // February has 28 days normally, 29 days in a leap year.
            if (isLeapYear(year))
                days = 29;
            else
                days = 28;
            break;
        case 3:
            monthName = "March";
            days = 31;
            break;
        case 4:
            monthName = "April";
            days = 30;
            break;
        case 5:
            monthName = "May";
            days = 31;
            break;
        case 6:
            monthName = "June";
            days = 30;
            break;
        case 7:
            monthName = "July";
            days = 31;
            break;
        case 8:
            monthName = "August";
            days = 31;
            break;
        case 9:
            monthName = "September";
            days = 30;
            break;
        case 10:
            monthName = "October";
            days = 31;
            break;
        case 11:
            monthName = "November";
            days = 30;
            break;
        case 12:
            monthName = "December";
            days = 31;
            break;
        default:
            // This should never happen if monthInputValidation() works correctly.
            monthName = "Unknown";
            days = 30;
            break;
    }

    return days;  // Give back the number of days in that month.
}


// ------------------------------------------------------------------
// getStartDay()
// This function calculates which day of the week the first day of the
// given month and year falls on.
// We will use a known formula (similar to Zeller's congruence).
//
// Return value:
//   0 = Sunday
//   1 = Monday
//   2 = Tuesday
//   3 = Wednesday
//   4 = Thursday
//   5 = Friday
//   6 = Saturday
// ------------------------------------------------------------------
int getStartDay(int year, int month)
{
    int d = 1;  // We always want the 1st day of the month.

    int y = year;
    int m = month;

    // In this formula, January and February are treated as months 13 and 14
    // of the previous year. This is a common trick for date formulas.
    if (m == 1 || m == 2)
    {
        m += 12;   // January becomes 13, February becomes 14
        y -= 1;    // Year is reduced by 1
    }

    // Apply the formula.
    // This formula will give a number corresponding to day of week.
    int k = y % 100;        // Last two digits of year
    int j = y / 100;        // First two digits of year

    int h = (d + (13 * (m + 1)) / 5 + k + (k / 4) + (j / 4) + 5 * j) % 7;

    // In this formula:
    // h = 0 → Saturday
    // h = 1 → Sunday
    // h = 2 → Monday
    // ...
    // We want: 0 = Sunday, 1 = Monday, ..., 6 = Saturday.
    // So we shift the value.
    int dayOfWeek = (h + 6) % 7;

    return dayOfWeek;  // Return day of week (0..6) for the first day of this month.
}


// ------------------------------------------------------------------
// displayCalendar()
// This function prints the calendar in a nice table format.
//
// Parameters:
//   year        → the year (e.g. 2020)
//   month       → the month number (1..12)
//   monthName   → the name of the month in words, like "June"
//   daysInMonth → how many days in that month (30, 31, etc.)
//   startDay    → which day of the week the 1st is (0=Sun .. 6=Sat)
//
// The output will look similar to your assignment example.
// ------------------------------------------------------------------
void displayCalendar(int year, int month, string monthName, int daysInMonth, int startDay)
{
    // Print the calendar title line, e.g. "Calendar Title : June - 2020"
    cout << endl;
    cout << "Calendar Title : " << monthName << " - " << year << endl;
    cout << "----------------------------------------------------------" << endl;

    // Print the names of the days in one row.
    cout << setw(8) << "Sunday"
         << setw(8) << "Monday"
         << setw(9) << "Tuesday"
         << setw(10) << "Wednesday"
         << setw(9) << "Thursday"
         << setw(8) << "Friday"
         << setw(9) << "Saturday" << endl;

    cout << "----------------------------------------------------------" << endl;

    int currentPosition = 0;  // This will track how many columns we have printed on the current row.

    // First, we need to print some empty spaces before the first day.
    // Example: if startDay is 1 (Monday),
    // we leave space under Sunday and then print 1 under Monday.
    for (int i = 0; i < startDay; i++)
    {
        cout << setw(8) << " ";  // Print an empty field with width 8.
        currentPosition++;       // We used one column.
    }

    // Now print the days of the month.
    for (int day = 1; day <= daysInMonth; day++)
    {
        cout << setw(8) << day;  // Print the day number with some width for spacing.
        currentPosition++;       // Move to the next column.

        // If we have printed 7 columns (Sunday to Saturday),
        // we move to the next line.
        if (currentPosition == 7)
        {
            cout << endl;        // Go to the next line.
            currentPosition = 0; // Reset the column count.
        }
    }

    // After printing all days, print a line break for nicer output.
    cout << endl;
    cout << "----------------------------------------------------------" << endl;
}
