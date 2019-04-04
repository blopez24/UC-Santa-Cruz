# Bryan Lopez
# blopez 1612626
#
# Makefile for NQueen class

JAVASRC    = NQueens.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = NQueens
CLASSES    = NQueens.class Queen.class
JARFILE    = NQueens.jar

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

