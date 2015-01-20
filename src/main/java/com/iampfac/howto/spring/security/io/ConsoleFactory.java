package com.iampfac.howto.spring.security.io;

public class ConsoleFactory {
	
	public AbstractConsole createConsole() {
		if (System.console() == null) {
			return new EmulatedConsole();
		} else {
			return new DefaultConsole();
		}
	}

}
