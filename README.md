PDA-Verfier
===========

Program that verifies if a given input is a valid language. Written in Java.
Uses a PDA (Push-Down Automata).

Language is defined as such:
```
S -> $T$
T -> T+T | T-T | T*T | T/T | (T) | CX
X -> XX | C | N | (EMPTY_STRING)
C -> a | b | c | ... | z
N -> 0 | 1 | 2 | ... | 9
```