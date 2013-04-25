package cryptogram;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests for Cryptos.
 * @author Zhu Cai
 *
 */
public class CryptosTest {
	
	Cryptos c = new Cryptos();
	Hashtable<Character, Character> table = new Hashtable<Character, Character>();
	
	@Before
	public void setUp() throws Exception {
		c.makeCrypt();
		table = c.getBigTable();
	}

	@Test
	public void testMakeCrypt() {
		assertEquals(52, table.size());
		assertTrue(Character.isLowerCase(table.get('a')));
		assertTrue(Character.isUpperCase(table.get('A')));
		Object[] keys = table.keySet().toArray();
		ArrayList<Character> chars = new ArrayList<Character>();
		for(Object ch : keys){
			chars.add(table.get(ch));
		}
		Object[] values = chars.toArray();
		Arrays.sort(values);
		String valueStr = "";
		for(Object o: values){
			valueStr += o;
		}
		System.out.println(valueStr);
		assertTrue(valueStr.equals("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"));
	}

	@Test
	public void testMakeCodeLine() {
		c.readLine('b');
		c.makeCodeLine();
		assertEquals(c.getCodeLine().length(), c.getOriginalLine().length());
		assertFalse(c.getCodeLine().equals(c.getOriginalLine()));
	}

	@Test
	public void testReadLine() {
		c.readLine('b');
		String l1 = c.getOriginalLine();
		c.readLine('c');
		String l2 = c.getOriginalLine();
		assertTrue(l1.length() > 0);
		assertTrue(l2.length() > 0);
	}
	
	@Test
	public void testMakeGuess(){
		c.makingGuess("abcd");
		assertEquals(2, c.getSmallTable().size());
		c.makingGuess("abcde");
		assertEquals(3, c.getSmallTable().size());
		assertTrue(c.getSmallTable().get('a') == 'd');
	}
	
	@Test
	public void testCutString(){
		c.readLine('b');
		String str = c.cutString(c.getOriginalLine());
		assertTrue(str.length() <= 40);
	}
}
