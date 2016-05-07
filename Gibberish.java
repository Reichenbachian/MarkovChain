import java.util.Scanner;
class Gibberish {
	public static void main(String args[]){
		if (args.length < 1) {
			throw new IllegalArgumentException("Missing a command line argument!");
		}
		Scanner sc = new Scanner(System.in);
		String response = "";
		MarkovChain chain = new MarkovChain(args[0]);
		do {
			System.out.print("Press <Enter> to generate a random sentence; type \"quit\" to quit. ");
			response = sc.nextLine();
			if (response.equals("")) {
				System.out.println(chain.randomSentence());
			} else if (response.equals("quit")) {
				break;
			} else {
				System.out.println("  [Sorry, I didn't understand that.]");
			}
		} while (true);
		sc.close();
	}
}