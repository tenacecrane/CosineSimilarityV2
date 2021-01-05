package cosineSimilarityV2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Author:Tenace Crane
 * Last Update: August 28, 2020
 * The program runs as follows: The user inputs a Trigger Phrase and a Test
 * Phrase. Upon clicking the calculate button, they are stored as vectorOne and
 * VectorTwo. Then vector three is created and contains vectorOneWords and
 * vectorTwoWords without punctuation, capitalization, and no more than one
 * instance stored of each unique word. Two more int vectors, vectorOneWC and
 * vectorTwoWC are created to store the Word Count of each word found in each
 * original vector. Cosine Similarity is then calculated and printed to the
 * console and application. Vectors are then cleared to prep for next test.
 * For purposes of simplicity, vector refers to either an ArrayList or HashMap.
 */

public class CosineSimilarityInterface implements ActionListener {
	private static JFrame frame;
	private static JPanel panel;
	private static JLabel v1Label;
	private static JLabel v2Label;
	private static JLabel csLabel;
	private static JTextField v1Text;
	private static JTextField v2Text;
	private static JButton button;
	private static String vectorOne;
	private static String vectorTwo;

	public static void main(String[] args) {
		frame = new JFrame();
		frame.setSize(600, 220);
		frame.setTitle("Cosine Similarity");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel);
		v1Label = new JLabel("Trigger Phrase ");
		v2Label = new JLabel("Test Phrase ");
		csLabel = new JLabel("Cosine Similarity: ");
		v1Label.setBounds(20, 20, 120, 20);
		v2Label.setBounds(20, 50, 120, 20);
		csLabel.setBounds(20, 110, 180, 20);
		panel.add(v1Label);
		panel.add(v2Label);
		panel.add(csLabel);
		v1Text = new JTextField(20);
		v2Text = new JTextField(20);
		v1Text.setBounds(150, 20, 400, 20);
		v2Text.setBounds(150, 50, 400, 20);
		panel.add(v1Text);
		panel.add(v2Text);
		button = new JButton("Calculate");
		button.setBounds(440, 95, 100, 50);
		button.addActionListener(new CosineSimilarityInterface());
		panel.add(button);
		frame.setVisible(true);

	}// end of main

	@Override
	public void actionPerformed(ActionEvent e) {
		CosineSimilarityMathV2 c = new CosineSimilarityMathV2();
		c.cosineSimilarity(v1Text.getText(), v2Text.getText());
		csLabel.setText(c.toString());
	} // end of calculations

}// end of class UserInterface