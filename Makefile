all: build run

build: clean
	@javac interfaces/*.java
	@javac administrative/*.java
	@javac components/*.java
	@javac gameObjects/*.java
	@javac global/*.java
	@javac graphics/*.java

run: build
	@java administrative/Tester

tests: build
	@java administrative/TestCases

clean:
	@rm -rf interfaces/*.class
	@rm -rf administrative/*.class
	@rm -rf components/*.class
	@rm -rf gameObjects/*.class
	@rm -rf global/*.class
	@rm -rf graphics/*.class
