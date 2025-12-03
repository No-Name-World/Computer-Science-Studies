#include <iostream>      // This line allows us to use cout and cin for printing and reading input.
using namespace std;     // This makes our code easier to write so we don’t need to type std::cout every time.



// ============================================================================
// Function 1: RecursiveReverseDisplayNumber
// This function will PRINT numbers from "end" down to "start".
// It does NOT return anything (void).
// It uses RECURSION — which means the function calls itself again.
// No loops (for, while) are allowed.
//
// Example:
// If start = 5 and end = 8,
// this function will print: 8 + 7 + 6 + 5
// ============================================================================
void RecursiveReverseDisplayNumber(int start, int end)
{
    // --- Base Case (Stopping Condition) ---
    // A recursive function must know WHEN TO STOP.
    // If "end" becomes smaller than "start", there are no numbers left to print.
    // Example: start=5, end=4 → stop!
    if (end < start)
        return;    // Stop the function. Do not continue.



    // --- Print the current number ---
    // At the first call: end = 8 → print 8
    // Next recursive call: end = 7 → print 7
    // Next: end = 6 → print 6
    // Next: end = 5 → print 5
    cout << end;



    // --- Print the "+" sign ONLY if we are not at the last number ---
    // We want to print "8 + 7 + 6 + 5"
    // Notice that there is no "+" after 5.
    if (end > start)
        cout << " + ";



    // --- Recursive Call ---
    // Now call the SAME function, but reduce "end" by 1.
    //
    // In simple English:
    // "Print this number, then let the function print the next number."
    //
    // The chain will be:
    // RecursiveReverseDisplayNumber(5, 8)
    // RecursiveReverseDisplayNumber(5, 7)
    // RecursiveReverseDisplayNumber(5, 6)
    // RecursiveReverseDisplayNumber(5, 5)
    // RecursiveReverseDisplayNumber(5, 4) → stops
    RecursiveReverseDisplayNumber(start, end - 1);
}



// ============================================================================
// Function 2: RecursiveCalSum
// This function CALCULATES the total sum from "start" to "end" using recursion.
// It RETURNS the sum as an integer.
//
// Example:
// start=5, end=8
// It will calculate: 8 + 7 + 6 + 5 = 26
// ============================================================================
int RecursiveCalSum(int start, int end)
{
    // --- Base Case ---
    // If "end" becomes smaller than "start", stop the recursion and return 0.
    // This 0 will be used to finish the addition.
    // Example: RecursiveCalSum(5, 4) returns 0
    if (end < start)
        return 0;



    // --- Recursive Calculation ---
    // The idea is:
    // return end + (sum of all numbers before it)
    //
    // For example:
    // RecursiveCalSum(5, 8)
    // → returns 8 + RecursiveCalSum(5, 7)
    //
    // RecursiveCalSum(5, 7)
    // → returns 7 + RecursiveCalSum(5, 6)
    //
    // RecursiveCalSum(5, 6)
    // → returns 6 + RecursiveCalSum(5, 5)
    //
    // RecursiveCalSum(5, 5)
    // → returns 5 + RecursiveCalSum(5, 4)
    //
    // RecursiveCalSum(5, 4)
    // → returns 0 (base case)
    //
    // Now all the returns go back:
    // 5 + 0 = 5
    // 6 + 5 = 11
    // 7 + 11 = 18
    // 8 + 18 = 26
    //
    // SO… the final answer returned to main() is 26.
    return end + RecursiveCalSum(start, end - 1);
}



// ============================================================================
// MAIN FUNCTION
// This is where the program starts running.
// ============================================================================
int main()
{
    int startvalue, endvalue;   // These variables will store numbers entered by the user.



    // --- Ask user to type a start value ---
    cout << "Enter a start value: ";
    cin >> startvalue;          // Store the number typed by the user.



    // --- Ask user to type an end value ---
    cout << "Enter an end value: ";
    cin >> endvalue;            // Store the number typed by the user.



    cout << endl;               // Print a blank line for neat output.



    // ========================================================================
    // Step 1:
    // Call the RECURSIVE PRINT function
    // This will display numbers like:
    // 8 + 7 + 6 + 5
    //
    // Note:
    // We are not printing the sum here.
    // This function ONLY prints numbers.
    // ========================================================================
    RecursiveReverseDisplayNumber(startvalue, endvalue);



    // Print the equals sign after the number list
    cout << " = ";



    // ========================================================================
    // Step 2:
    // Call the RECURSIVE SUM function.
    // This function RETURNS the final answer.
    // We print it using cout.
    // ========================================================================
    cout << RecursiveCalSum(startvalue, endvalue) << endl;



    return 0;   // End the program
}
