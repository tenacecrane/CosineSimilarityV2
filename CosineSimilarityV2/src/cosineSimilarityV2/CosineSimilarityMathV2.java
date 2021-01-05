package cosineSimilarityV2;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CosineSimilarityMathV2 {
	// Author: Tenace Crane
	// Last Update: 1/4/2020

	double cosineSimilarity = 0;

	// methods

	/**
	 * Begins cosine similarity calculation prep. Splits the trigger phrase and
	 * removes unnecessary punctuation, makes all lowercase, and saves words from
	 * both vectors into their separate ArrayLists. After this is done,
	 * combineVectorWords is called.
	 * 
	 * @param v1 - The trigger phrase string.
	 * @param v2 - The test phrase string.
	 */
	public void cosineSimilarity(String v1, String v2) {
		String[] v1WordArray = v1.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
		String[] v2WordArray = v2.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
		ArrayList<String> vectorOneWords = new ArrayList<String>(Arrays.asList(v1WordArray));
		ArrayList<String> vectorTwoWords = new ArrayList<String>(Arrays.asList(v2WordArray));
		combineVectorWords(vectorOneWords, vectorTwoWords);
	}

	/**
	 * Combines v1 and v2 to make v3 consisting of both vector's words. Because we
	 * use a Set, there are no duplicates in v3. After this is done,
	 * vectorWordCounter is called.
	 * 
	 * @param v1 - The trigger phrase word ArrayList.
	 * @param v2 - The test phrase string.
	 * @return
	 */
	private void combineVectorWords(ArrayList<String> v1, ArrayList<String> v2) {
		Set<String> set = new HashSet<>(v1);
		set.addAll(v2);
		ArrayList<String> v3 = new ArrayList<>(set);
		vectorWordCounter(v1, v2, v3);
	}

	/**
	 * Compares vector 1 (trigger phrase) and vector 2 (user phrase) to get a count
	 * of how many times each word in v3 appears in them. Populates two new HashMaps
	 * that contain words as keys and word counts as value pairs.
	 * 
	 * @param v1 - Trigger phrase word ArrayList.
	 * @param v2 - User phrase word ArrayList.
	 * @param v3 - Complete word ArrayList without duplicates.
	 */
	private void vectorWordCounter(ArrayList<String> v1, ArrayList<String> v2, ArrayList<String> v3) {
		HashMap<String, Integer> vectorOneWC = new HashMap<String, Integer>();
		HashMap<String, Integer> vectorTwoWC = new HashMap<String, Integer>();
		// populating vector one and two HashMaps
		for (int i = 0; i < v3.size(); i++) {
			vectorOneWC.put(v3.get(i), 0);
			vectorTwoWC.put(v3.get(i), 0);
		}
		// counting v3 words present in v1 and v2.
		for (int i = 0; i < v1.size(); i++) {
			if (vectorOneWC.containsKey(v1.get(i))) {
				vectorOneWC.replace(v1.get(i), vectorOneWC.get(v1.get(i)) + 1);
			}
		}
		for (int i = 0; i < v2.size(); i++) {
			if (vectorTwoWC.containsKey(v2.get(i))) {
				vectorTwoWC.replace(v2.get(i), vectorTwoWC.get(v2.get(i)) + 1);
			}
		}
		cosineSimilarityMath(vectorOneWC, vectorTwoWC);
	}

	/**
	 * Takes in vector 1 (trigger phrase) and vector 2 (test phrase) to calculate
	 * cosine similarity.
	 * 
	 * @param v1 - The trigger phrase for the bot.
	 * @param v2 - The user phrase given to bot.
	 * @return - Cosine Similarity, a number between 0 and 1 that indicates how
	 *         similar two phrases are.
	 */

	private double cosineSimilarityMath(HashMap<String, Integer> v1, HashMap<String, Integer> v2) {
		double numerator = 0;
		double denominator = 0;
		double x = 0;
		double y = 0;

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
		return cosineSimilarity;
	}

	public String toString() {
		String cosineSimilarityString = (String.format("Cosine Similarity: %.3f" + "\n", cosineSimilarity));
		return cosineSimilarityString;
	}

}
