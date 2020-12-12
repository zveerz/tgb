all:
ifndef JAVA_HOME
	$(error JAVA_HOME is undefined)
endif
	gcc -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/linux" -fPIC -shared -o ping.so ping.c
clean:
	rm ping.so
