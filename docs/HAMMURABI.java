package hammurabi.docs;

import java.util.Random;
import java.util.Scanner;

public class HAMMURABI {
	public int Population = 100;
	public int bushels = 2800;
	public int acresOwned = 1000;
	public int landValue = 19;
	public boolean gameOn = true;
	public int year;

	Random rand = new Random();  // this is an instance variable
	Scanner scanner = new Scanner(System.in);
	int amountHarvested;

	public static void main(String[] args) { // required in every Java program
		new HAMMURABI().playGame();

	}

	void playGame() {
		// declare local variables here: grain, population, etc.
		// statements go after the declations

		while (gameOn) {

			System.out.println("O great Hammurabi!\n" +
					"You are in year " + year + " of your ten year rule.\n" +
					"In the previous year 0 people starved to death.\n" +
					"In the previous year 5 people entered the kingdom.\n" +
					"The population is now 100.\n" +
					"We harvested " + amountHarvested + " bushels at 3 bushels per acre.\n" +
					"Rats destroyed 200 bushels, leaving 2800 bushels in storage.\n" +
					"The city owns 1000 acres of land.\n" +
					"Land is currently worth 19 bushels per acre.\n");

			howManyAcresToBuy(19, bushels);
			askHowManyAcresTosell(acresOwned);
			int grainToFeed = askHowMuchGrainToFeedPeople(bushels);
			int acresToPlant = askHowManyAcresToPlant(acresOwned, Population, bushels);
			plagueDeaths(Population);
			int deathFromStarvation = starvationDeaths(Population, grainToFeed);
			uprising(Population, deathFromStarvation);
			this.amountHarvested = harvest(acresToPlant);


		year++;
		}
		scanner.close();
	}

	public int howManyAcresToBuy(int price, int bushels) {
		System.out.println("HOW MANY ACRES DO YOU WISH TO BUY? ");
		int userInput = scanner.nextInt();
		price = userInput * 19;
		if (bushels >= price) {
			this.bushels -= price;

		}
		else {
			System.out.println("You do not have enough bushels :(");
		}
		System.out.println("You have " + bushels + " bushels left.");
		return userInput;
	}

	public int askHowManyAcresTosell(int acresOwned){
		System.out.println("How many acres of land would you like to sell \n");
		int userInpt = scanner.nextInt();
		if(userInpt <= acresOwned){
			this.acresOwned -= userInpt;
			this.bushels += userInpt * landValue;
		}
		else {
			System.out.println("You don't have enough acres to sell that amount");
		}
		System.out.println("You have " + this.bushels + " amount of bushels left");
		return userInpt;
	}
	public int askHowMuchGrainToFeedPeople(int bushels){
		System.out.println("How many bushels would you like to feed the people \n");

		int userInput = scanner.nextInt();
		if(userInput <= bushels){
			this.bushels -= userInput;
		}
		else {
			System.out.println("You don't have enough :(");
		}
		System.out.println("Thank you for feeding the people " + userInput + " bushels, you currently " + this.bushels + " amount left.");

		return userInput;
	}

	public int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
		while (true) {
			System.out.println("How many acres would you like to plant with grain \n");

			int acresToPlant = scanner.nextInt();
			int amountOfBushelsNeed = acresToPlant * 2;
			int amountOfPeopleNeeded = acresToPlant / 10;
			if (acresToPlant <= this.acresOwned && amountOfBushelsNeed <= this.bushels && amountOfPeopleNeeded <= this.Population) {
				System.out.println("You are currently planting " + acresToPlant + " amount of acres");
				this.bushels -= amountOfBushelsNeed;
				return acresToPlant;
			} else {
				System.out.println("You can't plant these amount of acres");
			}


		}
	}





	public int plagueDeaths(int population) {
		Random random = new Random();
		if(random.nextInt(101) <15){
			System.out.println("Sorry, you guys got hit with the plaque. You lost half you're population :(");

			this.Population -= this.Population/2;
			return this.Population;
		}
		System.out.println("You have a current population is " + this.Population);
		return 0;
	}

	public int starvationDeaths(int people, int bushelsToFeed) {
		int amountOfBushelsNeeded = people * 20;
		if (bushelsToFeed >= amountOfBushelsNeeded) {
			this.bushels -= amountOfBushelsNeeded;
//			immigrants(Population, this.acresOwned,this.bushels);
			return 0;
		}

		int bushelsShort = amountOfBushelsNeeded - bushelsToFeed;
		double amountOfPeopleStarved = (double) bushelsShort/20;
		double numberCeil = Math.ceil(amountOfPeopleStarved);
		this.Population -= (int) numberCeil;
		return (int) numberCeil;

	}

	public boolean uprising(int population, int howManyPeopleStarved) {

		double populationNumber = (double) population *.45;
		if(howManyPeopleStarved > populationNumber){
			System.out.println("You've been impeached!");
			gameOn = false;
			return true;
		}

		return false;
	}

	public int immigrants(int population, int acresOwned, int grainInStorage) {
		if(starvationDeaths(population,grainInStorage) ==0){
			int result = (20 * acresOwned + grainInStorage) / (100 * population) + 1;
			System.out.println("You gained immigrants");
			return result;

		}
		System.out.println("You gained no immigrants");
		return 0;
	}

	public int harvest(int acres) {
		Random random = new Random();
		int randomNum =random.nextInt(6)+1;
		this.bushels += acres * randomNum;

		return acres * randomNum;
	}

	public int grainEatenByRats(int bushels) {
		Random random = new Random();
		if (random.nextInt(101) < 40) {
			Random random2 = new Random();
			double randomNumber2 = random2.nextInt(21)+10;
			return  (int) (bushels * randomNumber2/100);

		}





		return 0;
	}

	public int newCostOfLand() {
		Random random = new Random();
		int newPrice = random.nextInt(7)+17;

		return newPrice;
	}


	//other methods go here
}