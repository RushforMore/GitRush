package cryptogram;
import java.io.*;
import java.util.*;

/**
 * Creates and manages the cryptogram.
 * @author Zhu Cai
 *
 */
public class Cryptos {
	private Hashtable<Character, Character> bigTable;
	private Hashtable<Character, Character> lineTable;
	private Hashtable<Character, Character> smallTable;
	private ArrayList<String> lines;
	private String originalLine;
	private String codeLine;

	/**
	 * Creates a new cryotos.
	 */
	public Cryptos(){
		bigTable = new Hashtable<Character, Character>();
		smallTable = new Hashtable<Character, Character>();
		lineTable = new Hashtable<Character, Character>();
		lines = new ArrayList<String>();
		originalLine = "";
		codeLine = "";
	}

	/**
	 * Returns the big hashtable matches every letter to a new letter.
	 * @return The big hashtable.
	 */
	public Hashtable<Character, Character> getBigTable(){
		return bigTable;
	}
	
	/**
	 * Returns the hashtable contains the letters from the original line.
	 * @return The line table.
	 */
	public Hashtable<Character, Character> getLineTable(){
		return lineTable;
	}
	
	/**
	 * Returns the hashtable contains the guess player made.
	 * @return The player's guess hashtable.
	 */
	public Hashtable<Character, Character> getSmallTable(){
		return smallTable;
	}

	/**
	 * Returns the original line.
	 * @return The original line.
	 */
	public String getOriginalLine(){
		return originalLine;
	}

	/**
	 * Returns the code line.
	 * @return The code line.
	 */
	public String getCodeLine(){
		return codeLine;
	}
	
	/**
	 * Returns the guess player made.
	 * @return The guess line.
	 */
	public String getGuessLine(){
		String guess = "";
		for(int i = 0; i < codeLine.length(); i++){
			char ch = codeLine.charAt(i);
			if(smallTable.containsKey(ch)){
				guess += smallTable.get(ch);
			}
			else if(Character.isLetter(ch)){
				guess += " ";
			}
			else{
				guess += ch;
			}
		}
		return guess;
	}
	
	/**
	 * Makes the big hashtable.
	 */
	public void makeCrypt(){
		ArrayList<Character> charSet = new ArrayList<Character>();
		Random randomGenerator = new Random();
		for(int i = 65; i < 91; i++){
			charSet.add((char)(i));
		}
		for (int i = 65; i < 91 ; i++){
			char oldChar = (char)(i);
			int rand = randomGenerator.nextInt(charSet.size());
			char newChar = charSet.get(rand);
			while(newChar == oldChar){
				rand = randomGenerator.nextInt(charSet.size());
				newChar = charSet.get(rand);
			}
			bigTable.put(oldChar, newChar);
			charSet.remove(rand);
		}
		for(int i = 97; i < 123; i++){
			charSet.add((char)(i));
		}
		for (int i = 97; i < 123 ; i++){
			char oldChar = (char)(i);
			int rand = randomGenerator.nextInt(charSet.size());
			char newChar = charSet.get(rand);
			while(newChar == oldChar){
				rand = randomGenerator.nextInt(charSet.size());
				newChar = charSet.get(rand);
			}
			bigTable.put(oldChar, newChar);
			charSet.remove(rand);
		}
	}

	/**
	 * Makes the code line with big hashtable and original line.
	 */
	public void makeCodeLine(){
		char[] charSet = originalLine.toCharArray();
		char[] newLine = new char[charSet.length];
		for(int i = 0; i < charSet.length; i++){
			if(bigTable.containsKey(charSet[i])){
				newLine[i] = bigTable.get(charSet[i]);
				lineTable.put(newLine[i], charSet[i]);
			}
			else{
				newLine[i] = charSet[i];
			}
		}
		codeLine = new String(newLine);
	}

	/**
	 * Reads out the original line based on quotes type.
	 * @param type The quotes type.
	 */
	@SuppressWarnings("resource")
	public void readLine(char type){
		try {
			if(lines.size() == 0){
				File quotes = new File("quotes.txt");
				Reader reader;
				reader = new FileReader(quotes);
				BufferedReader lineReader = new BufferedReader(reader);
				String line;
				while ((line = lineReader.readLine()) != null) {
					lines.add(line);
				}
			}
			Random r = new Random();
			String randomStr = " ";
			if(type != 'A'){
				while(randomStr.charAt(0) != type){
					randomStr = lines.get(r.nextInt(lines.size()));
				}
			}
			else{
				randomStr = lines.get(r.nextInt(lines.size()));
			}
			originalLine = randomStr.substring(2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Updates the guess table based on player's guess.
	 * @param text Player's guess.
	 */
	public void makingGuess(String text){
		if(text.length()%2 != 0){
			text += " ";
		}
		char[] words = text.toCharArray();
		int offset = text.length() / 2;
		for(int i = 0; i < text.length() / 2; i++){
			smallTable.put(words[i], words[i + offset]);
		}
	}
	
	/**
	 * Makes a line to be displayed on text area.
	 * @return The line to be displayed.
	 */
	public String makeLine(String code, String guess){
		String newLine = "";
		while(code.length() > 40){
			newLine += cutString(code) + "\n" + cutString(guess) + "\n" + "\n";
			code = code.substring(cutString(code).length());
			guess = guess.substring(cutString(guess).length());
		}
		newLine += code + "\n" + guess;
		return newLine;
	}
	
	/**
	 * Cuts the string to fit the text area.
	 * @param str The string needed to be trimmed.
	 * @return The trimmed string.
	 */
	public String cutString(String str){
		if(str.length() < 40) 
			return str;
		char[] s = str.toCharArray();
		int spaceCount = 0;
		for(int i = 0; i < 40; i++){
			if(s[i]== ' '){
				spaceCount = i;
			}
		}
		char[] newS = new char[spaceCount];
		for(int i = 0; i < spaceCount; i++){
			newS[i] = s[i];
		}
		return new String(newS) + '\n';
	}
}
