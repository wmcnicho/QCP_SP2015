package Gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class FileParser {
	public static HashMap<Integer, Integer>  parseFile(String filename){
		 Integer numElements = new Integer(0);
		 BufferedReader br = null;
		 try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String line =  null;
		 HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();

		    try {
				while((line=br.readLine())!=null){
				    String str[] = line.split(",");
				    for(int i=0;i<str.length;i++){
				        map.put(Integer.parseInt(str[i]), numElements);
				        numElements++;
				    }
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println(map);
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
