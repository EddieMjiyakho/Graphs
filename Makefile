JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) -sourcepath $(SRCDIR) $<



CLASSES= GraphException.class Edge.class Vertex.class Graph.class Path.class RandomGraphGenerator.class GraphExperiment.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class

run: $(CLASS_FILES)
	java -cp bin GraphExperiment

docs: $(CLASS_FILES)
	javadoc -d doc -sourcepath src src/*.java
