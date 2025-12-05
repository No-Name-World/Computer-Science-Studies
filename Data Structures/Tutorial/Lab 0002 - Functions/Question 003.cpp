#include <iostream>
// This line tells the compiler to include the "iostream" library.
// "iostream" allows us to use input and output objects like:
//   - cout  → to print text on the screen
//   - cin   → to get input from the keyboard


using namespace std;
// This line lets us use names like "cout" and "cin" directly.
// Without this line, we would have to write "std::cout" and "std::cin".
// Using "namespace std" makes the code easier to write and read for beginners.



// -------------------------------------------------------------
// Function to reverse a string (without using built-in reverse)
// -------------------------------------------------------------
string reverseString(string text)   // This creates a function named "reverseString".
                                    // The function:
                                    //   - takes one input: "text" (this is a string given by the caller)
                                    //   - returns a string (the reversed version of "text")
{

    string reversed = "";       // Create an empty string named "reversed".
                                // At the start, "reversed" has nothing inside it.
                                // We will slowly add characters into "reversed" one by one,
                                // but we will add them from the end of "text" to the beginning.
                                // This will create a reversed version of the original string.


    // Loop from last character to first character of the string "text".
    // text.length() gives the number of characters in "text".
    // Example: if text = "cat", text.length() is 3.
    // The last index is length - 1 (because index starts from 0).
    // So:  c  a  t
    //      0  1  2  ← last index is 2 (which is length-1)

    for (int i = text.length() - 1; i >= 0; i--)
    // int i = text.length() - 1  → start from the last character position.
    // i >= 0                     → keep looping until i reaches 0 (the first character).
    // i--                        → after each loop, reduce i by 1 (move one step to the left).
    {

        reversed += text[i];      // This adds the character at position i into "reversed".
        // Example:
        // If text = "cat":
        //   i = 2 → text[2] = 't' → reversed = "t"
        //   i = 1 → text[1] = 'a' → reversed = "ta"
        //   i = 0 → text[0] = 'c' → reversed = "tac"
        //
        // So, at the end, "reversed" will be "tac".
    }


    return reversed;              // After the loop, we return the reversed string.
// Whoever called reverseString(text) will now receive this reversed string as the result.
}


// -------------------------------------------------------------
// Function to check if a string is a palindrome
// -------------------------------------------------------------
bool isPalindrome(string text)    // This creates a function named "isPalindrome".
                                  // The function:
                                  //   - takes one input: "text" (the word or string to check)
                                  //   - returns a boolean (true or false)
                                  //   - true  → if the string is a palindrome
                                  //   - false → if the string is not a palindrome
{
    // We first get the reversed version of the text.
    // We reuse the reverseString function we already wrote above.
    string reversedText = reverseString(text);
    // Now we have:
    //   - "text"        → original string
    //   - "reversedText" → reversed version of the string

    // Next, we compare the original and reversed strings.
    // If they are exactly the same, then the word is a palindrome.
    // A palindrome is a word that reads the same forward and backward.
    // Examples of palindrome: "level", "madam", "racecar"

    if (text == reversedText)     // Check if "text" is equal to "reversedText".

    {
        return true;              // If they are equal, we return true.
        // This means: "Yes, this string IS a palindrome."
    }
    else                          // If they are not equal...
    {
        return false;             // We return false.
        // This means: "No, this string is NOT a palindrome."
    }
}



// -------------------------------------------------------------
// Main Program (where the program starts running)
// -------------------------------------------------------------
int main()               // This is the main function.
                        // Every C++ program starts from the main() function.
                        // When you run your program, the computer begins reading code from here.
{

    string userInput;             // Create a string variable named "userInput".
    // This will be used to store the text that the user types from the keyboard.


    cout << "Enter a string: ";   // Print a message on the screen asking the user to enter a string.
    // "cout" prints text to the screen.
    // "<<" sends the text to cout for display.


    cin >> userInput;       // Read the string typed by the user and store it in "userInput".
                            // Note: "cin >> userInput" will stop reading when it sees a space.
                            // So if the user types "hello world": Only "hello" will be stored in userInput.
                            // If you want to read spaces also, later you can use getline(), but for now this is okay.


    // Call the reverseString function and display the result on the screen.
    cout << "Reversed string: " << reverseString(userInput) << endl;
    // Here is what happens step by step:
        // 1. reverseString(userInput) is called.
        // 2. It returns the reversed version of userInput.
        // 3. cout prints: "Reversed string: " followed by that reversed text.
        // 4. "endl" moves the cursor to the next line (like pressing Enter).


    // Now we check if the string entered by the user is a palindrome.
    // We call isPalindrome(userInput) which returns true or false.
    if (isPalindrome(userInput))  // If the function returns true...
    {
        cout << "This string is a palindrome." << endl;
        // We print this message to tell the user that the string is a palindrome.

    }
    else                          // If the function returns false...

    {
        cout << "This string is NOT a palindrome." << endl;
        // We print this message to tell the user that the string is NOT a palindrome.
    }


    return 0;           // This tells the operating system that the program ended successfully.
                        // "0" usually means "no error" in programming.
                        // After this line, the program stops running.
}
