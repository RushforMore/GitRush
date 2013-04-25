package cryptogram;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

/**
 * Creates a cryptogram game.
 * @author Zhu Cai
 * @version 04/09/2013
 */
@SuppressWarnings("serial")
public class GuiMaker extends JFrame{
	JFrame mainFrame;
	JPanel buttonPanel, comboPanel;
	JButton newGame, giveUp, quit, hint; 
	JComboBox box;
	JTextField textField;
	JTextArea textArea;
	Cryptos crypto;
	
	/**
	 * Makes a new GUI.
	 * @param args
	 */
	public static void main(String[] args){
		new GuiMaker().makeGui();
	}
	
	/**
	 * Makes the GUI.
	 */
	public void makeGui(){
		crypto = new Cryptos();
		crypto.makeCrypt();
		setComboBox();
		setTextField();
		setTextArea();
		setButtons();
		setButtonPanel();
		setComboPanel();
		setFrame();
	}
	
	/**
	 * Sets the frame.
	 */
	public void setFrame(){
		mainFrame = new JFrame("Cryptograms");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFrame.add(new JLabel("Zhu Cai"), BorderLayout.NORTH);
		mainFrame.add(textArea, BorderLayout.CENTER);
		mainFrame.add(comboPanel, BorderLayout.SOUTH);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Sets the combo box.
	 */
	public void setComboBox(){
		String[] quotesList = {"b = Computer Humor (269)", "c = Computer Profound/Serious (101)",
				"f = Fortune Cookies (69)", "f = Humorous (3643)", "p = Profound/Serious (5101)", "Any Category"};
		box = new JComboBox(quotesList);
		box.setSelectedIndex(5);
		box.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        String quotesType = (String)cb.getSelectedItem();
		        crypto = new Cryptos();
				crypto.makeCrypt();
		        crypto.readLine(quotesType.charAt(0));
		        crypto.makeCodeLine();
		        updateTextArea();
			}
		});
	}
	
	/**
	 * Sets the text field.
	 */
	public void setTextField(){
		textField = new JTextField();
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				crypto.makingGuess(text);
				updateTextArea();
				checkResult();
				textField.setText("");
			}
		});
	}
	
	/**
	 * Checks whether the puzzle has been solved.
	 */
	public void checkResult(){
		if(crypto.getGuessLine().equals(crypto.getOriginalLine())){
			JOptionPane.showMessageDialog(mainFrame, "Congraturations! You have solved the cryptogram!");
		}
		return;
	}
	
	/**
	 * Sets the text area.
	 */
	public void setTextArea(){
		textArea = new JTextArea(8, 40);
		Font font = new Font("Monospaced", Font.PLAIN, 15);
		textArea.setFont(font);
		textArea.setEditable(false);
		textArea.setText(crypto.makeLine(crypto.getCodeLine(), crypto.getGuessLine()));
	}
	
	/**
	 * Updates the text area.
	 */
	public void updateTextArea(){
		textArea.setText(crypto.makeLine(crypto.getCodeLine(), crypto.getGuessLine()));
	}
	
	/**
	 * Sets the buttons.
	 */
	public void setButtons(){
		newGame = new JButton("New Game");
		newGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		giveUp = new JButton("Give Up");
		giveUp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				textArea.setText(crypto.makeLine(crypto.getCodeLine(), crypto.getOriginalLine()));
			}
		});
		quit = new JButton("Quit");
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		hint = new JButton("Hint");
		hint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				makeHint();
			}
		});
	}
	
	/**
	 * Makes a hint when player clicks the hint button.
	 */
	public void makeHint(){
		Hashtable<Character, Character> lineTable = crypto.getLineTable();
		Hashtable<Character, Character> smallTable = crypto.getSmallTable();
		Object[] keys = smallTable.keySet().toArray();
		for(Object o: keys){
			if(smallTable.get(o) != lineTable.get(o)){
				JOptionPane.showMessageDialog(mainFrame, "" + o + " should be " + lineTable.get(o));
				return;
			}
		}
		Object[] lineKeys = lineTable.keySet().toArray();
		for(Object o: lineKeys){
			if(!smallTable.containsKey(o)){
				JOptionPane.showMessageDialog(mainFrame, "" + o + " should match " + lineTable.get(o));
				return;
			}
		}
		
	}
	
	/**
	 * Sets the button panel.
	 */
	public void setButtonPanel(){
		buttonPanel = new JPanel();
		buttonPanel.add(newGame);
		buttonPanel.add(giveUp);
		buttonPanel.add(quit);
		buttonPanel.add(hint);
	}
	
	/**
	 * Sets the combo panel.
	 */
	public void setComboPanel(){
		comboPanel = new JPanel();
		comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.Y_AXIS));
		comboPanel.add(textField);
		comboPanel.add(box);
		comboPanel.add(buttonPanel);
	}
}
