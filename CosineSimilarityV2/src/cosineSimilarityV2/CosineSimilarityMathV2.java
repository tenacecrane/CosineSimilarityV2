package cosineSimilarityV2;

import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;

public class CosineSimilarityMathV2 {
	// Author: Tenace Crane
	// Last Update: 8/28/2020
	/*
	 * Attributes: Vector one is the trigger phrase. vector two is the student
	 * phrase. vector three consists of the words from trigger phrase added to the
	 * words in student phrase, with no repetitions, for counting purposes. To see
	 * the correct order that these methods get run in, refer to
	 * ActionPerformed(ActionEvent e) in UserInterface.java. For purposes of
	 * simplicity, vector refers to either an ArrayList or HashMap.
	 */
	ArrayList<String> vectorOneWords = new ArrayList<String>();
	ArrayList<String> vectorTwoWords = new ArrayList<String>();
	ArrayList<String> vectorThreeWords = new ArrayList<String>();
	HashMap<String, Integer> vectorOneWC = new HashMap<String, Integer>();
	HashMap<String, Integer> vectorTwoWC = new HashMap<String, Integer>();
	double numerator = 0;
	double denominator = 0;
	double x = 0;
	double y = 0;
	double cosineSimilarity;

	// methods

	// takes in v1wc and v2wc to calculate cosine similarity
	public double cosineSimilarity(HashMap<String, Integer> v1, HashMap<String, Integer> v2) {
		ArrayList<String> set = new ArrayList<String>();
		set.addAll(v1.keySet());
		// calculates numerator
		for (int i = 0; i < set.size(); i++) {
			numerator += v1.get(set.get(i)) * v2.get(set.get(i));
		}
		// calculates x and y for denominator
		for (int i = 0; i < set.size(); i++) {
			x += Math.pow(v1.get(set.get(i)), 2);
			y += Math.pow(v2.get(set.get(i)), 2);
		}
		x = Math.sqrt(x);
		y = Math.sqrt(y);
		denominator = x * y;
		cosineSimilarity = numerator / denominator;
		x = 0;
		y = 0;
		numerator = 0;
		denominator = 0;
		return cosineSimilarity;
	}

	public void vectorOneSplit(String v) {
		/*
		 * splits the trigger phrase and removes unnecessary punctuation. makes
		 * lowercase. saves words into vectorOneWords ArrayList.
		 */
		String[] words = v.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
		for (String word : words) {
			vectorOneWords.add(word);
		}
	}

	public HashMap<String, Integer> vectorOneWC(List<String> v3, ArrayList<String> v1) {
		/*
		 * takes in v3 word list (List), compares v1 (arraylist) to get a count of how
		 * many times each word (key) in v3 appears in v1 and then returns a new hashmap
		 * containing the word (key) and the word count (value).
		 */
		for (int i = 0; i < v3.size(); i++) {
			vectorOneWC.put(v3.get(i), 0);
		}
		for (int i = 0; i < v1.size(); i++) {
			if (vectorOneWC.containsKey(v1.get(i))) {
				vectorOneWC.replace(v1.get(i), vectorOneWC.get(v1.get(i)) + 1);
			}
		}
		return vectorOneWC;
	}

	public void vectorTwoSplit(String v) {
		/*
		 * splits the trigger phrase and removes unnecessary punctuation. makes
		 * lowercase. saves words into vectorTwoWords ArrayList.
		 */
		String[] words = v.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
		for (String word : words) {
			vectorTwoWords.add(word);
		}
	}

	public HashMap<String, Integer> vectorTwoWC(List<String> v3, ArrayList<String> v2) {
		// populates full word list to use for counting word occurences in v2.
		for (int i = 0; i < v3.size(); i++) {
			vectorTwoWC.put(v3.get(i), 0);
		}
		// counts words
		for (int i = 0; i < v2.size(); i++) {
			if (vectorTwoWC.containsKey(v2.get(i))) {
				vectorTwoWC.replace(v2.get(i), vectorTwoWC.get(v2.get(i)) + 1);
			}
		}
		return vectorTwoWC;
	}


	public void prepNewTest() {
		vectorOneWC.clear();
		vectorTwoWC.clear();
		vectorTwoWords.clear();
		numerator = 0;
	}

	// combines v1 and v2 to make one vector consisting of both vector's words.
	public List<String> combineVectorWords(ArrayList<String> v1, ArrayList<String> v2) {
		Set<String> set = new LinkedHashSet<>(v1);
		set.addAll(v2);
		List<String> vectorThreeWords = new ArrayList<>(set);
		return vectorThreeWords;
	}

}
