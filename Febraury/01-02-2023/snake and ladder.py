import random
from turtle import *
class Board:
    def __init__(self,size):
        self.size = size
        self.snake = Snake()
        slef.ladder = Ladder()
        self.dice = Dice()
        self.no_of_players = int(input("Enter Number of players"))
        self.createPlayers()
        self.startGame()

    def createPlayers(self):
        self.players =[]
        for i in range(self.no_of_players):
            self.players.append(Player(input("Enter Name")))
    def startGame(self):
            print("Start Game")
        while(len(self.players)>1):
            self.currPlayer= self.player[0]
            self.outcome =roll()
            self.total =0
            self.currPos=0
            for i in range(self.outcome):
                self.total +=self.outcome[i]
   
           
           

class Snake:
    def __init__(self):
        self.snakes = []
        self.snakeBitted = []
        self.no_of_snakes = int(input("Enter count of Snake"))
        for i in range(self.no_of_snakes):
            self.snakes.append(list(map(int,input("Enter head and tail seperated by space").split())))
    def isSnake(self, position):
        for i in range(self.no_of_snakes):
            if i[0]==position:
                self.snakeBitted = i
                return True
        return False

    def snakeEnd(self, position):
        tail = self.snakeBitted[1]
        self.snakeBitted = []
        print("Snake byted at :",position)
        return tail

class Ladder:
    def __init__(self):
        self.ladder = []
        self.ladderClimb = []
        self.no_of_ladders = int(input("Enter count of Ladder"))
        for i in range(self.no_of_ladders):
            self.ladder.appens(list(map(int,input("Enter bottom and top seperated by space"))))
    def isLadder(self,position):
        for i in range(self.no_of_ladders):
            if(i[0]== position):
                self.laddeClimb= i
                return True
        return False
    def ladder(self, position):
        top = self.ladderClimb[1]
        self.ladderClimb = []
        print("Climbed ladder at",position)
        return top

class Dice(Turtle):
    def __init__(self):
        Turtle.__init__(self,shape = "rectangle")
        self.color("black","white")
        self.up()
        self.goto()
       
        self.dices = int(input("Enter number of dices"))
        self.value =[]

    def roll(self):
       
        for i in range(self.dices):
            self.value.append(random.randint(1,6))
        getValue =self.value
        self.value=[]
        return getValue
    def is6(self):
        self.count=self.roll()
        for i in range(self.count):
            if(self.count[i]!=6):
                return False
        return True
    def isnextChance(self,count6):
        if (count6>2):
            print("Ooops! 3 sixes")
            return False
        return True
class Player(Turtle):
    def __init__(self,color,shape,name):
        Turtle.__init__(self,color=color,shape = "circle")
        self.up()
        self.position=0
        self.name = name
