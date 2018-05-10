/**
 *  This file is part of BoomingsCalculator
 *  Copyright (C) 2018  <name of author>
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
	private static int leerzeichen = 0;
	private static int einrueckung = 5;
	
	public static void setextendedlog(boolean b) {
		
		extendedlog = b;
		
	}
	
	public static void printlog(String log) {
		
		if (extendedlog) {
			
			System.out.println(verarbString(log));
			
		}
		
	}
	
	public static void printlog(char log) {
		
		if (extendedlog) {
			
			System.out.println(verarbChar(log));
			
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
		//Hier wird wichtiges ausgegeben
		System.out.println(verarbString(imp));
		
	}
	
	public static void printImp(Exception e) {
		
		System.out.println(e.getMessage());
		e.printStackTrace();
		
	}
	
	public static String verarbString(String verarb) {

		int leerzeichenhier = leerzeichen; 
		String anfang = "";
		while (leerzeichenhier > 0) {
			
			anfang = anfang + " ";
			leerzeichenhier--;
			
		}
		verarb = anfang + verarb;
		
		return verarb;
		
	}
	
	public static String verarbChar(char verarb) {
		
		int leerzeichenhier = leerzeichen;
		String anfang = "";
		while (leerzeichenhier > 0 ) {
			
			anfang = anfang + " ";
			leerzeichenhier --;
			
		}
		anfang = anfang + verarb;
		
		return anfang;
		
		
	}
	
	public static void indent() {
		
		leerzeichen += einrueckung;
		
	}
	
	public static void unindent() {
		
		leerzeichen -= einrueckung;
	
	}
	
	//TODO wäre möglich, dass man hier Probleme bekommen kann, weil man dann die Static variablen nur ein mal verwenden kann. (Just saying.)
	
	
}