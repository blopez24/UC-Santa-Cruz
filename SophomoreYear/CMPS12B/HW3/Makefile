#Bryan Lopez
#blopez24 (1612626)

# A simple Makefile

JAVASRC    = ChessMoves.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = ChessMoves
CLASSES    = ChessMoves.class CPLinkedList.class Node.class ChessPiece.class King.class Queen.class Bishop.class Knight.class Rook.class WhitePawn.class BlackPawn.class
JARFILE    = ChessMoves.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)


