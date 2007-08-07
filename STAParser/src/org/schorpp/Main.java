package org.schorpp;

import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MT940Lexer lexer;
		try {
			lexer = new MT940Lexer( new ANTLRFileStream("C:/Programme/Dresdner Cash Management/MCCWIN/BWMDA000.sta"));

		MT940Parser parser = new MT940Parser(new CommonTokenStream(lexer));

			parser.prog();
		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
