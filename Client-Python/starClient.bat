@echo off
omniidl -bpython ../Hangman.idl
python Client.py -ORBbootstrapAgentPort 6505 -ORBbootstrapAgentHostname localhost
