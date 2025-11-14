SHELL := /bin/zsh

.PHONY: help build test clean format check

MVN ?= mvn

help:
	@echo "Available targets:"
	@echo "  build   - Compile the project and run tests"
	@echo "  test    - Run unit tests"
	@echo "  clean   - Clean build artifacts"
	@echo "  check   - Run static checks (currently just 'test')"

build:
	$(MVN) clean verify

test:
	$(MVN) test

clean:
	$(MVN) clean

check: test
	@echo "Checks complete."

