# Bryan Lopez
# blopez24 (1612626)

# Makefile for charType.c

FLAGS   = -std=c99 -Wall
SOURCES = charType.c
OBJECTS = charType.o
EXEBIN = charType

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean:
	rm -f $(EXEBIN) $(OBJECTS)

