/**
 *  This file is part of BoomingsCalculator
 *  Copyright (C) 2018  Cornelius Huber
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see https://www.gnu.org/licenses/gpl.html.
 */

package iOStreams;

public class Output{

	private static boolean extendedlog = false;
	private static int spaces = 0;
	private static int indention = 5;
	
	public static void setextendedlog(boolean b) {
		
		extendedlog = b;
		
	}
	
	public static void printlog(String log) {
		
		if (extendedlog) {
			
			System.out.println(processString(log));
			
		}
		
	}
	
	public static void printlog(char log) {
		
		if (extendedlog) {
			
			System.out.println(processChar(log));
			
		}
		
	}
	
	public static void printlog() {
		
		if (extendedlog) {
			
			System.out.println();
			
		}
		
	}
	
	public static void printlog(Exception e) {
		
		if (extendedlog) {
			
			System.out.println(e.getMessage());
			
		}
		
	}
	
	public static void printImp(String imp) {
		
		System.out.println(processString(imp));
		
	}
	
	public static void printImp(Exception e) {
		
		System.out.println(e.getMessage());
		e.printStackTrace();
		
	}
	
	public static String processString(String process) {

		int spaceshere = spaces; 
		String begin = "";
		while (spaceshere > 0) {
			
			begin = begin + " ";
			spaceshere--;
			
		}
		process = begin + process;
		
		return process;
		
	}
	
	public static String processChar(char process) {
		
		int spaceshere = spaces;
		String begin = "";
		while (spaceshere > 0 ) {
			
			begin = begin + " ";
			spaceshere --;
			
		}
		begin = begin + process;
		
		return begin;
		
		
	}
	
	public static void indent() {
		
		spaces += indention;
		
	}
	
	public static void unindent() {
		
		spaces -= indention;
	
	}
	
	//TODO wäre möglich, dass man hier Probleme bekommen kann, weil man dann die Static variablen nur ein mal verwenden kann. (Just saying.)
	
	
}