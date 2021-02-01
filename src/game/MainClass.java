package game;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainClass {
	public static String FILE = "locations.txt";
	static Scanner input = new Scanner(System.in);
	static ArrayList<Place> places = new ArrayList<>();
	static String userName = "";
	static final int RESET = 0;

	public static void main(String[] args) {
		System.out.println("***********************************");
		System.out.println();
		System.out.println("  Welcome to capital Guesser");
		System.out.println("		v5.0");
		System.out.println();
		System.out.println("***********************************");
		System.out.println();
		int wrongCount = 0;
		int correctCount = 0;
		load(FILE);
		char choice = ' ';
		System.out.println("Push (Q) to quit");
		System.out.println("Push (P) to play");
		choice = input.nextLine().toUpperCase().charAt(0);
		if (choice == 'P') {
			while (choice != 'Q') {
				
				while (wrongCount < 5) {
					Place currentPlace = getRandomPlace();
					String answer = askCountry(currentPlace, input);
					if (verifyAnswer(answer, currentPlace)) {
						System.out.println();
						System.out.println("CORRECT!");
						System.out.println();
						correctCount++;
					} else {
						System.out.println();
						System.out.println("***********************************");
						System.out.println();
						System.out.println("INCORRECT!");
						System.out.println("The correct answer was " + currentPlace.getCapital());
						System.out.println();
						System.out.println("***********************************");
						System.out.println();
						wrongCount++;
					}
					

				}

				System.out.println("********** GAME OVER *************");
		
				System.out.println();
				System.out.printf("%-20s%-2d\n", "Countries correct: ", correctCount);
				System.out.printf("%-20s%-2d\n", "Countries incorrect: ", wrongCount);
				System.out.println();
				System.out.println("Push (Q) to quit"); 
				System.out.println("Push (P) to play");
				System.out.println();
				System.out.println("***********************************");
				choice = input.nextLine().toUpperCase().charAt(0);
				if (choice == 'P') {
					 wrongCount = RESET;
					 correctCount = RESET;
				}
				
				
			}
		} else if (choice == 'Q') {
			System.out.println();
			System.out.println("Goodbye!");
			System.out.println();
			System.out.println("***********************************");
		}
	}

	public static Place getRandomPlace() {
		Random rand = new Random();
		return places.get(rand.nextInt(places.size() - 1));

	}

	public static String askCountry(Place place, Scanner input) {
		System.out.println("What is the capital of " + place.getCountry());
		return input.nextLine();
	}

	public static boolean verifyAnswer(String answer, Place currentPlace) {
		if (answer.equals(currentPlace.getCapital())) {
			return true;
		}
		return false;
	}

	public static void load(String fileName) {
		try {
			Scanner file = new Scanner(new FileInputStream(fileName));

			while (file.hasNext() == true) {

				String line;
				String[] fields;
				String capital = "";
				String country = "";
				line = file.nextLine();
				fields = line.split(" — ");
				if (fields.length < 0) {
					System.out.println("File empty");
				} else {
					try {
						country = fields[0];
						capital = fields[1];
						places.add(new Place(capital, country));
					} catch (Exception e) {
						System.out.println("Error loading from line");
					}
				}

			}
			file.close();
		} catch (IOException e) {
			System.out.println("Error");
		}

	}

}
