#include <iostream>
using namespace std;

// This function reverses the string manually, without using built-in functions
string reverseString(string text)
{
    string reversed = "";  // This will store the reversed result

    // Loop from the LAST character to the FIRST character
    for (int i = text.length() - 1; i >= 0; i--)
    {
        reversed = reversed + text[i]; // Add each character to 'reversed'
    }

    return reversed; // Send back the reversed string
}

// This function compares two strings manually (character by character)
bool compareString(string text1, string text2)
{
    // If both strings are not the same length → cannot be equal
    if (text1.length() != text2.length())
        return false;

    // Check each character one by one
    for (int i = 0; i < text1.length(); i++)
    {
        if (text1[i] != text2[i]) // If any character is different → not same
            return false;
    }

    return true; // All characters are the same → strings match
}

int main()
{
    string text;

    // Ask the user to enter a word
    cout << "Enter a string: ";
    cin >> text; // Read the text entered by user

    // Call the reverse function
    string reversedtext = reverseString(text);

    // Show the reversed result
    cout << "Reversed string: " << reversedtext << endl;

    // Check if original text == reversed text
    if (compareString(text, reversedtext))
    {
        cout << "The entered string is a palindrome!" << endl;
    }
    else
    {
        cout << "The entered string is NOT a palindrome!" << endl;
    }

    return 0;
}
