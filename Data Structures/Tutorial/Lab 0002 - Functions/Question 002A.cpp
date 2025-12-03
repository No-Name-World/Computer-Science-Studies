#include <iostream>
using namespace std;

// This function prints numbers from end → start (reverse order) using recursion
void RecursiveReverseDisplayNumber(int start, int end)
{
    if (end < start)          // If end is smaller than start → nothing to print → stop
        return;

    cout << end;              // Print the current number

    if (end > start)          // If this is NOT the last number, print " + "
        cout << " + ";

    RecursiveReverseDisplayNumber(start, end - 1);
    // Call the SAME function again but with end - 1
    // This moves to the next number (going backwards)
}

// This function returns the total sum of all numbers from end → start
int RecursiveCalSum(int start, int end)
{
    if (end < start)          // If end is smaller than start → no more numbers → return 0
        return 0;

    // Return current number + sum of the rest
    return end + RecursiveCalSum(start, end - 1);
    // The function calls itself again with end - 1
}

int main()
{
    int startvalue, endvalue;

    cout << "Enter a start value: ";
    cin >> startvalue;        // Read the first number from the user

    cout << "Enter an end value: ";
    cin >> endvalue;          // Read the second number from the user

    cout << endl;

    // Step 1: Print the numbers in reverse using recursion
    RecursiveReverseDisplayNumber(startvalue, endvalue);

    cout << " = ";

    // Step 2: Call the second recursive function to get the total sum
    cout << RecursiveCalSum(startvalue, endvalue) << endl;

    return 0;                 // End the program
}
