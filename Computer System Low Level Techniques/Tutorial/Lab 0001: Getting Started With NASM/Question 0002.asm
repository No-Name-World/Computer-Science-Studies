section .data                                      ; this section stores data (text or numbers)
                                                   ; data here does not change during the program

    char db 'A'                                    ; 'char' is a label pointing to memory storing one character: 'A'
                                                   ; 'db' means "define byte" → store exactly 1 byte into memory
                                                   ; this will be printed later

    newline db 10                                  ; store the value 10 in memory
                                                   ; 10 is the ASCII code for NEWLINE (go to next line)
                                                   ; this lets us print a line break after printing 'A'


section .text                                      ; this section contains all instructions (actual program code)
                                                   ; the CPU runs these instructions

    global _start                                  ; tell the system that program execution begins at _start
                                                   ; without this, Linux would not know where to begin


_start:                                            ; this is the entry point — code starts running here


    ; ---------------- PRINT THE CHARACTER 'A' ----------------

    mov eax, 4                                     ; eax chooses the system call number
                                                   ; system call 4 = write()
                                                   ; write() is used to print data

    mov ebx, 1                                     ; ebx is the 1st argument of write()
                                                   ; 1 means "write to the screen" (stdout)

    mov ecx, char                                  ; ecx is the 2nd argument of write()
                                                   ; ecx must contain the memory ADDRESS of the data to print
                                                   ; 'char' is the address where 'A' is stored

    mov edx, 1                                     ; edx is the 3rd argument of write()
                                                   ; edx tells Linux how many bytes to print
                                                   ; 1 → only print 1 character ('A')

    int 0x80                                       ; this triggers the system call
                                                   ; Linux now runs: write(1, char, 1)
                                                   ; the letter 'A' appears on the screen


    ; ---------------- PRINT NEWLINE ----------------

    mov eax, 4                                     ; again select system call 4 = write()
                                                   ; we call write() a second time to print a newline

    mov ebx, 1                                     ; same as before → write to the screen

    mov ecx, newline                               ; ecx = address of newline character
                                                   ; newline contains the value 10 (ASCII for new line)
                                                   ; printing this moves the cursor to the next line

    mov edx, 1                                     ; print only 1 byte (the newline character 10)

    int 0x80                                       ; trigger system call again
                                                   ; Linux prints the newline on the screen


    ; ---------------- EXIT THE PROGRAM ----------------

    mov eax, 1                                     ; eax = 1 selects system call 1 = exit()
                                                   ; exit() ends the program

    xor ebx, ebx                                   ; set ebx = 0
                                                   ; ebx stores the exit code
                                                   ; 0 means "successful exit"

    int 0x80                                       ; ask Linux to exit the program
                                                   ; the program ends here

