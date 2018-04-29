@echo off
idlj -fclient ../Hangman.idl
javac -d . *.java
java -cp . Client -ORBInitialPort 6000
