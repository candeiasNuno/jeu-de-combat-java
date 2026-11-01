all: compile run

compile:
	mkdir -p bin
	javac -d bin $(shell find src -name "*.java")

run:
	java -cp bin Main

clean:
	rm -rf bin/