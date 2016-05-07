/*
TODO
1) get filename as command line argument
2) Train the Markov Chain
	2a) Get text from the file word-by-word
	2b) Keep track of start words and end-words
3) Repeated input by the user
4) Generate gibberish sentences
*/
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


class MarkovChain {
	Map<String, List<String>> chain = new HashMap<String, List<String>>();
	/**
	* Prompts the user whether they want a new sentence until they respond with
	* the string argument.
	*/
	public MarkovChain(String fileName) {
		trainMarkovChain(fileName);
	}


	/**
	* Calls getNextWord. This function adds words in the proper place
	* to our dictionary in order to represent our markov chain as shown
	* by the assignment. Also adds words that begin with a capital letter
	* to the field startWords.
	*/
	public void trainMarkovChain(String fileName) {
		Scanner sc;
		try {
			File file = new File(fileName);
			sc = new Scanner(file).useDelimiter("\\s");
		} catch (Exception ex) {
			throw new IllegalArgumentException("File Not Found!!!!");
		}
		String previousWord = "";
		while (sc.hasNext()) {
			String currentWord = sc.next();
			if (currentWord.equals("")) {
				continue;
			}
			List<String> updatedList = new ArrayList<String>();
			if (chain.get(previousWord) != null) {
 				updatedList = chain.get(previousWord);
 			}
			updatedList.add(currentWord);
			chain.put(previousWord, updatedList);
			previousWord = currentWord;
			if (currentWord.charAt(currentWord.length()-1) == '.' ||
				currentWord.charAt(currentWord.length()-1) == '!' ||
				currentWord.charAt(currentWord.length()-1) == '?') {
				previousWord = "";
			}
		}
	}

	/**
	* Returns a random sentence based off of the Markov Chain this class represents.
	*/
	public String randomSentence() {
		List<String> startWords = chain.get("");
		String currentWord = startWords.get((int)(Math.random()*startWords.size()));
		String sentence = "  " + currentWord;
		do {
			List<String> newChoices = chain.get(currentWord);
			if (newChoices == null) {
				return sentence;
			}
			currentWord = newChoices.get((int)(Math.random()*newChoices.size()));
			sentence += " " + currentWord;
		} while (currentWord.equals("") || 
		 	currentWord.charAt(currentWord.length()-1) != '.');
		return sentence;
	}
}