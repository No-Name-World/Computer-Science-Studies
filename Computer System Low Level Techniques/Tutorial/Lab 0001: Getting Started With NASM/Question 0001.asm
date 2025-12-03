section .data                                      ; this section is used to store data
                                                   ; data means text or numbers that do not change
                                                   ; the program will read from here when needed

msg db "Welcome to Malaysia!", 10                  ; 'msg' is the name of our message
                                                   ; 'db' means "define bytes" → store these characters in memory
                                                   ; "Welcome to Malaysia!" is the text we want to print
                                                   ; 10 is the newline character (moves cursor to next line)
                                                   ; msg now holds the memory address of this message

len equ $ - msg                                    ; 'len' calculates the length of the message
                                                   ; '$' means the current memory position
                                                   ; '$ - msg' = the number of bytes from start of msg to here
                                                   ; this gives the exact length of our text (for printing)

section .text                                      ; this section is for instructions (the actual code)
                                                   ; the CPU runs everything written in this section

global _start                                      ; tell the system that _start is the entry point
                                                   ; this means the program begins running at _start label

_start:                                            ; this is where the program really starts executing


    ; ------------------------- PRINT THE MESSAGE -------------------------

    mov rax, 1                                     ; mov = copy a value into a register
                                                   ; rax is a special register used to choose the Linux system call
                                                   ; system call 1 = write() → used to print text
                                                   ; so this means: "Linux, I want to use write()"

    mov rdi, 1                                     ; rdi is the register for the 1st argument of the system call
                                                   ; write() needs 3 arguments: (where_to_write, what_to_write, length)
                                                   ; 1 means the screen (file descriptor 1 = standard output)
                                                   ; so rdi = 1 means "write the message to the screen"

    mov rsi, msg                                   ; rsi is the register for the 2nd argument of the system call
                                                   ; the 2nd argument of write() is the address of the message
                                                   ; 'msg' is the memory location where the text is stored
                                                   ; so rsi = msg means "here is the message you should print"

    mov rdx, len                                   ; rdx is the register for the 3rd argument of the system call
                                                   ; the 3rd argument of write() is the length of the message
                                                   ; len tells Linux how many characters to print
                                                   ; so rdx = len means "print this many bytes"

    syscall                                        ; syscall tells Linux to run the instruction described in registers
                                                   ; Linux will now run: write(1, msg, len)
                                                   ; this prints "Welcome to Malaysia!" on the screen



    ; ------------------------- EXIT THE PROGRAM -------------------------

    mov rax, 60                                    ; rax = 60 tells Linux to use the exit() system call
                                                   ; system call 60 = exit program

    xor rdi, rdi                                   ; xor rdi, rdi sets rdi to 0
                                                   ; rdi is the exit code → 0 means "program ended successfully"
                                                   ; xor is a quick way to set a register to zero

    syscall                                        ; ask Linux to exit the program now
                                                   ; program ends here

