# Bryan Lopez
# blopez (1612626)

# Makdfile for fileReverse.c with macros

FLAGS	= -std=c99 -Wall
SOURCES	= fileReverse.c
OBJECTS	= fileReverse.o
EXEBIN	= fileReverse

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean:
	rm -f $(EXEBIN) $(OBJECTS)
