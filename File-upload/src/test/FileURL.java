package test;
import java.io.*;
import java.net.*;
		public class FileURL {
		  public static void main(String args[]) throws MalformedURLException {
		    File file = new File("The End");
		    URL url2 = file.toURI().toURL();
		    System.out.printf("Good url %s%n", url2);
	}

}
