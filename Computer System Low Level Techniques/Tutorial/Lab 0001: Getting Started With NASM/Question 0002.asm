section .data                           ; Section for stored data
  char    db 'A'                        ; Store the letter A (1 byte)
  newline db 10                         ; 10 = ASCII for newline '\n'

section .text                         ; Section for instructions
  global _start                       ; Program entry point

_start:                                ; Start of the program

; ---- Print 'A' ----
        mov eax, 4                    ; System call 4 = write()
        mov ebx, 1                    ; Write to screen (file descriptor 1)
        mov ecx, char                 ; Memory address of our A
        mov edx, 1                    ; Print only 1 byte
        int 0x80                      ; Ask Linux to print it


; ---- Print NEWLINE ----
        mov eax, 4                    ; System call 4 = write()
        mov ebx, 1                    ; Write to screen
        mov ecx, newline              ; Print the newline
        mov edx, 1                    ; Only 1 byte again
        int 0x80                      ; Now output the newline


; ---- Exit program ----
        mov eax, 1                    ; System call 1 = exit()
        xor ebx, ebx                  ; Exit code 0 (successful)
        int 0x80                      ; End the program
