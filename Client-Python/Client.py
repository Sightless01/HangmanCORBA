import sys
from omniORB import CORBA
import HangmanGame, CosNaming

orb = CORBA.ORB_init(sys.argv, CORBA.ORB_ID)

name_service_obj = orb.resolve_initial_references("NameService")
name_service_root = name_service_obj._narrow(CosNaming.NamingContext)

hangman_name = [CosNaming.NameComponent("hangman", "")]

obj = name_service_root.resolve(hangman_name)

hangman = obj._narrow(HangmanGame.Hangman)

while True:
	name = input("Enter your name: ")
	word = hangman.startGame(name)
	print(word)
	if word != "Player Already exists.":
		break
		
while True:
	line = ""
	i = 0
	while ( i < len(word)):
		line+="_ "
		i+=1
		
	while True:
		print(line)
		userIn = input("Enter your letter guess: ")
		charIn = userIn[0]
		
		if(hangman.letterGuess(name, charIn)) :
			replacement = list(line)
			i = 0
			while ( i < len(word)):
				if(word[i] == charIn):
					replacement[i*2] = charIn
				else:
					if(replacement[i*2] == "_"):
						replacement[i*2] = "_"
				i+=1
			
			line = ''.join(replacement)
		else:
			print("Wrong!")
			
		print("Remaining Life: " + str(hangman.getCurrentLife(name)))
		print(str(hangman.checkKey(name, line)))
		if(hangman.getCurrentLife(name) == 0 or hangman.checkKey(name, line)):
			break
	
	if(hangman.getCurrentLife(name) == 0):
		answer = input("Do you still want to play?(y/n): ")[0]
		if(answer == 'n'):
			print(hangman.endGame(name))
			sys.exit(0)
			
	word = hangman.newGame(name)