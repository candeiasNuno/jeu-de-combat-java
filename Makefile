all: compile run

compile:
	mkdir -p bin
	javac -d bin src/*.java

run:
	java -cp bin Main

clean:
	rm -rf bin/