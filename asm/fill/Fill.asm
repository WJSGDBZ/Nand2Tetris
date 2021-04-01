// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

(LOOP)
@KBD
D=M
@NZERO    //if KBD!=0 jump
D;JNE

@LOOP
0;JMP

(NZERO)
@8191         //i = 8191
D=A
@i
M=D

(BLACK)
@SCREEN
D=A       //  D = SCREEN + i
@i
D=D+M
M=M-1    // i--

A=D      // make pixel black
M=-1

@i       // if i>=0 jump
D=M
@BLACK
D;JGE

(Request)
@KBD
D=M
@Request
D;JNE

@8191         //j = 8191
D=A
@j
M=D
(WHITE)
@SCREEN
D=A       //  D = SCREEN + i
@j
D=D+M
M=M-1    // j--

A=D      // make pixel white
M=0

@j       // if j>=0 jump
D=M
@WHITE
D;JGE

@LOOP    // return
0;JMP












