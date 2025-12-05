#include <iostream>      // Allows us to use cout (to print) and cin (to read input).
using namespace std;     // So we can write cout and cin without writing std::cout, std::cin.


// ----------------------------------------------------------
// Function: validateTime
// Purpose : Check whether the hour, minute and second values
//           are valid or not.
//           - hour   should be from 0 to 23
//           - minute should be from 0 to 59
//           - second should be from 0 to 59
// Return  : true  → time is valid
//           false → time is NOT valid
// ----------------------------------------------------------
bool validateTime(int hour, int minute, int second)
{
    // Check the range of hour, minute and second one by one.
    if (hour < 0 || hour > 23)      // If hour is less than 0 OR more than 23 → invalid.
        return false;               // Immediately return false (time is not valid).

    if (minute < 0 || minute > 59)  // If minute is less than 0 OR more than 59 → invalid.
        return false;

    if (second < 0 || second > 59)  // If second is less than 0 OR more than 59 → invalid.
        return false;

    // If all three checks are passed, the time is valid.
    return true;                    // Return true to say "time is valid".
}


// ----------------------------------------------------------
// Function: calculateElapsedSeconds
// Purpose : Convert a time (HH:MM:SS) to "total seconds
//           since midnight".
// Idea    :
//   - 1 hour  = 3600 seconds
//   - 1 minute = 60 seconds
//   - So total seconds = hour * 3600 + minute * 60 + second
// Return : the total number of seconds as an int.
// ----------------------------------------------------------
int calculateElapsedSeconds(int hour, int minute, int second)
{
    int totalSeconds;                    // Variable to store the result.

    // Multiply hour by 3600 to convert hours to seconds.
    // Multiply minute by 60 to convert minutes to seconds.
    // Then add all seconds together.
    totalSeconds = hour * 3600 + minute * 60 + second;

    return totalSeconds;                 // Give the answer back to the caller.
}


// ----------------------------------------------------------
// main function
// The program starts running from here.
// ----------------------------------------------------------
int main()
{
    int hour, minute, second;       // To store the time entered by the user.
    char colon1, colon2;            // To store the ':' characters between HH, MM and SS.
    bool isValid = false;           // This will tell us if the time is valid or not.

    // We use a loop so that if the user enters wrong time,
    // we can ask again until the input is correct.
    while (!isValid)                // Loop while isValid is false.
    {
        // Ask user to enter the time in HH:MM:SS format.
        cout << "Please enter your elapsed time (in HH:MM:SS format) = ";

        // Read the input.
        // Example input: 00:02:44
        //   hour   will get 0
        //   colon1 will get ':'
        //   minute will get 2
        //   colon2 will get ':'
        //   second will get 44
        cin >> hour >> colon1 >> minute >> colon2 >> second;

        // First, check if the format used ':' correctly.
        // We expect something like 12:34:56, so both colon1 and colon2 must be ':'.
        if (colon1 != ':' || colon2 != ':')
        {
            cout << "Invalid format. Please use HH:MM:SS (with colons ':')." << endl;
            // Continue means skip the rest of this loop and ask again.
            continue;
        }

        // Now call validateTime() to check the numeric ranges of hour, minute, second.
        if (validateTime(hour, minute, second))
        {
            // If the function returns true, then the time is valid.
            isValid = true;   // Set isValid to true, so the while loop will stop.
        }
        else
        {
            // If time is not valid, show an error message and repeat the loop.
            cout << "Invalid time values. "
                 << "Hour must be 0-23, minute and second must be 0-59." << endl;
        }
    }

    // At this point, we know the time is valid (because the loop ended).
    // Next, we calculate how many seconds have passed since midnight.
    int elapsedSeconds = calculateElapsedSeconds(hour, minute, second);

    // Finally, we display the result to the user.
    cout << "Elapsed time in seconds = " << elapsedSeconds << " seconds" << endl;

    return 0;  // Tell the operating system that the program ended successfully.
}
