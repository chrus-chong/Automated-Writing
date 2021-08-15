import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;

/**
 * This is the main class for your Markov Model.
 *
 * Assume that the text will contain ASCII characters in the range [1,255].
 * ASCII character 0 (the NULL character) will be treated as a non-character.
 *
 * Any such NULL characters in the original text should be ignored.
 */
public class MarkovModel {

	// Use this to generate random numbers as needed
	private Random generator = new Random();

	// This is a special symbol to indicate no character
	public static final char NOCHARACTER = (char) 0;

	public int order;
	public HashMap<String, HashMap<Character, Integer>> table = new HashMap<String, HashMap<Character, Integer>>();

	/**
	 * Constructor for MarkovModel class.
	 *
	 * @param order the number of characters to identify for the Markov Model sequence
	 * @param seed the seed used by the random number generator
	 */
	public MarkovModel(int order, long seed) {
		// Initialize your class here
		this.order = order;

		// Initialize the random number generator
		generator.setSeed(seed);
	}

	/**
	 * Builds the Markov Model based on the specified text string.
	 */
	public void initializeText(String text) {
		// Build the Markov model here
		for (int i = 0; i < text.length() - this.order; i++) {
			String preceding = text.substring(i, i + order);
			char next = text.charAt(i+order);
			if (this.table.containsKey(preceding)){
				HashMap<Character, Integer> possible = this.table.get(preceding);
				if (possible.containsKey(next)) {
					possible.put(next, possible.get(next) + 1);
				} else {
					possible.put(next, 1);
				}
			} else {
				HashMap<Character, Integer> helper = new HashMap<Character, Integer>();
				helper.put(next, 1);
 				this.table.put(preceding, helper);
			}
		}
	}

	/**
	 * Returns the number of times the specified kgram appeared in the text.
	 */
	public int getFrequency(String kgram) {
		if (kgram.length() != this.order) {
			return 0;
		}
		if (this.table.containsKey(kgram)) {
			HashMap<Character, Integer> possible = this.table.get(kgram);
			int total = 0;
			for (Integer val : possible.values()) {
				total = total + val;
			}
			return total;
		} else {
			return 0;
		}
	}

	/**
	 * Returns the number of times the character c appears immediately after the specified kgram.
	 */
	public int getFrequency(String kgram, char c) {
		if (kgram.length() != this.order) {
			return 0;
		}
		if (this.table.containsKey(kgram)) {
			HashMap<Character, Integer> possible = this.table.get(kgram);
			if (possible.containsKey(c)) {
				return possible.get(c);
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * Generates the next character from the Markov Model.
	 * Return NOCHARACTER if the kgram is not in the table, or if there is no
	 * valid character following the kgram.
	 */
	public char nextCharacter(String kgram) {
		// See the problem set description for details
		// on how to make the random selection.
		if (this.table.containsKey(kgram)) {
			HashMap<Character, Integer> possible = this.table.get(kgram);
			int chosen = generator.nextInt(this.getFrequency(kgram));
			Character[] array = this.table.get(kgram).keySet().toArray(new Character[0]);
			Arrays.sort(array);
			int start = 0;
			int end = possible.get(array[0]) - 1;
			for (int i = 0; i < array.length; i++) {
				if (chosen >= start && chosen <= end) {
					return array[i];
				} else {
					start = end;
					end = end + possible.get(array[i+1]);
				}
			}

		} else {
			return NOCHARACTER;
		}
		throw new RuntimeException();
	}
}
