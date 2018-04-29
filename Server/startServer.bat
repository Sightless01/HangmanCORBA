@echo off
idlj -fall ../Hangman.idl
javac -d . *.java
start orbd -ORBInitialPort 6000
start java -cp . Server -ORBInitialPort 6000
