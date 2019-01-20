package codes;

import java.util.Scanner;

public class star {
	
	public static int credits, crystals, ore, fuel, day;
	public static double construction = 0;
	public static int discoveredPlanets = 5; // eventually increases to 15
	public static final int numberPlanets = 15;
	public static planet []map = new planet[numberPlanets];
	public static planet currentLocation;
	
	public static void main(String[] args) {
		//TESTING
		credits = 100; crystals = 5; ore = 5; fuel = 5;
		
		
		planet Coruscant = new planet("Coruscant", 1, 20, 20, 20, 3, 3, 3);
		map[1] = Coruscant;
		
		currentLocation = map[1];
		
		planet earth = new planet("earth",0,10,10,10,1,1,1);
		
		do {
			resourceStore(earth);
		} while(!purchase(earth));
	}
	
	// INCOMPLETE
	public static void mainMenu(planet p) {
		System.out.println("Day: " + day + "	Progress: " + construction + "%		Fear: "); // incomplete
		System.out.println("===========================================");
		System.out.println("\n1. Map");
		System.out.println("2. Fleet");
		System.out.println("3. Resources");
		
		int choice = errorTrap("\nWhat would you like to do? ",1,3);
		// interact with current location or travel to another place
		if(choice == 1) {
			System.out.println("\nOrbiting: " + p.name);
			System.out.println("===========================================");
			System.out.println("\n1. View " + p.name);
			System.out.println("2. Travel");
			System.out.println("3. Secure routes");
			System.out.println("0. Exit");
			
			int subchoice = errorTrap("\nWhat would you like to do? ",0,3);
			// interact with current location
			if(subchoice == 1) {
				if(p.name.equals("Death Star")) {
					do {
						deathStarMenu();
					} while(!deathStarMenu());
				}
				else {
					do {
						planetMenu(p);
					} while(!planetMenu(p));
				}
			}
			// move to another location, can view data of discovered planets before moving
			else if(subchoice == 2) {
				do {
					travel(p);
				} while(!travel(p));
			}
			// unlock more planets to travel to
			else if(subchoice == 3) {
				// MAKE METHOD THEN ADD HERE
			}
		}
		// view, manage, buy ships for fleet
		else if(choice == 2) {
			
		}
		// view list of resources and incomes
		else if(choice == 3) {
			// calculate all incomes first, then display
			int income = 0;
			for(int i = 0; i < numberPlanets; i++) {
				if(map[i].allegiance == 1) {
					income = income + map[i].income;
				}
			}
			
			int crystalIncome = 0;
			for(int i = 0; i < numberPlanets; i++) {
				if(map[i].allegiance == 1) {
					crystalIncome = crystalIncome + map[i].incomeC;
				}
			}
			
			int oreIncome = 0;
			for(int i = 0; i < numberPlanets; i++) {
				if(map[i].allegiance == 1) {
					oreIncome = oreIncome + map[i].incomeO;
				}
			}
			
			int fuelIncome = 0;
			for(int i = 0; i < numberPlanets; i++) {
				if(map[i].allegiance == 1) {
					fuelIncome = fuelIncome + map[i].incomeF;
				}
			}
			
			int controlledPlanets = 0;
			for(int i = 0; i < numberPlanets; i++) {
				if(map[i].allegiance == 1) {
					controlledPlanets++;
				}
			}
			// adjust spaces if needed
			System.out.println("\nPlanets controlled: " + controlledPlanets + " / 15");
			System.out.println("Credits: " + credits + "			+" + income + " per month");
			System.out.println("Kyber crystals: " + crystals + "	+" + crystalIncome + " per day");
			System.out.println("Ore: " + ore + "					+" + oreIncome + " per day");
			System.out.println("Fuel: " + fuel + "					+" + fuelIncome + " per day");
			
			errorTrap("\nEnter 0 to exit: ", 0, 0);
		}
	}
	
	// move to another planet
	public static boolean travel(planet p) {
		boolean exit = false;
		String allegiance = "";
		int distance;
		int currentIndex = 0;
		
		// ADJUST SPACES WHEN TESTING
		System.out.println("\n   NAME		ALLEGIANCE		DISTANCE");
		for(int i=0; i < discoveredPlanets; i++) {
			if(map[i].allegiance == -1)
				allegiance = "Rebel";
			else if(map[i].allegiance == 0)
				allegiance = "Neutral";
			else if(map[i].allegiance == 1)
				allegiance = "Empire";
			
			distance = Math.abs(p.distance - map[i].distance);
			
			if(!p.equals(map[i])) { // don't display current location
				System.out.println(i+1 + ". " + map[i].name + "		" + allegiance + "		" + distance + " days");
			}
			else
				currentIndex = i; // to be used in error trap
		}
		
		// customized error trap for this situation
		int planetChoice = 0; boolean success;// KEEP NOTE OF SHIFTED INDEX
		do{
			System.out.print("\nSelect a planet (enter 0 to exit): ");
			success = true;
			try {
				planetChoice = input.nextInt();
			}
			catch(Exception e) {
				input.nextLine();
				success = false;
			}
		}while(planetChoice < 0 || planetChoice > discoveredPlanets+1 || planetChoice == currentIndex+1 || !success);
		
		if(planetChoice != 0) {
			System.out.println("\n1. View planet data");
			System.out.println("2. Travel to planet");
			System.out.println("0. Reselect");
			
			int choice = errorTrap("\nWhat would you like to do? ", 0, 2);
			if(choice == 1) {
				viewData(map[planetChoice-1]);
			}
			else if(choice == 2) {
				exit = true; // need to return to previous menu
				currentLocation = map[planetChoice-1];
				// COMBINE WITH DAY PASSING SOMEHOW
			}
		}
		else
			exit = true;
		
		return exit;
	}
	
	// fight a battle to unlock 5 more planets
	public static void secureRoutes(planet p) {
		if(discoveredPlanets == 5) {
			System.out.println("\n1. Secure");
		}
		else if(discoveredPlanets == 10) {
			System.out.println("\n1. Secure Outer Rim");
		}
		System.out.println("0. Exit");
		
		int choice = errorTrap("\nWhat would you like to do? ", 0, 1);
		if(choice == 1) {
			// ADD BATTLE METHOD HERE
		}
	}
	
	// sub menu specific to Death Star
	public static boolean deathStarMenu() {
		System.out.println("Death Star Construction Site");
		System.out.println("===========================================");
		System.out.println("\nKyber crystals: " + crystals + "    Ore: " + ore + "    Fuel: " + fuel); // show resources so player can calculate
		System.out.println("\n1. Construct");
		System.out.println("0. Exit");
		
		boolean exit = true;
		int choice =  errorTrap("\nWhat would you like to do? ",0,1);
		if(choice == 1) {
			construction( errorTrap("\nEnter number of days to construct: ",0,2000000000) ); // arbitrary large number as limit
			exit = false;
		}
		
		return exit;
	}
	
	// enter a specified amount of days to construct, 1 day builds 0.5% and consumes 5,5,10
	public static void construction(int workDays) {
		System.out.println(); // new line after entering number of days from menu method
		// MUST COMBINE WITH PASS DAYS METHOD SOMEHOW
		
		if(crystals >= 5*workDays && ore >= 5*workDays && fuel >= 10*workDays) {
			crystals = crystals - 5*workDays; ore = ore - 5*workDays; fuel = fuel - 10*workDays;
			construction = construction + 0.5*workDays;
			day = day + workDays;
			System.out.println(0.5*workDays + "% constructed.");
		}
		else
			System.out.println("Not enough resources for " + workDays + " days.");
	}
	
	// sub menu when viewing a planet, menus is slightly different based on allegiance, completely different for Death Star
	public static boolean planetMenu(planet p) { // maybe add an ascii image at top
		System.out.println("\nOrbiting: " + p.name);
		System.out.println("===========================================");
		
		boolean exit = false;
		if(p.allegiance == 1) {
			System.out.println("\n1. View data");
			System.out.println("2. Trade");
			System.out.println("3. Build facilities");
			System.out.println("0. Exit");
			
			int choice = errorTrap("\nWhat would you like to do? ", 0, 3);
			if(choice == 0)
				exit = true;
			else if(choice == 1) 
				viewData(p);
			else if(choice == 2) {
				do {
					resourceStore(p);
					purchase(p);
				} while(!purchase(p));
			}
			else if(choice == 3) {
				do {
					buildFacilities(p);
				} while(!buildFacilities(p));
			}
		}
		else if(p.allegiance == 0) {
			System.out.println("\n1. View data");
			System.out.println("2. Trade");
			System.out.println("3. Invade");
			System.out.println("0. Exit");
			
			int choice = errorTrap("\nWhat would you like to do? ", 0, 3);
			if(choice == 0)
				exit = true;
			else if(choice == 1) 
				viewData(p);
			else if(choice == 2) {
				do {
					resourceStore(p);
					purchase(p);
				} while(!purchase(p));
			}
			else if(choice == 3) {
				//ADD THIS AFTER BUILDING ITS METHOD
			}
		}
		else if(p.allegiance == -1) {
			System.out.println("\n1. View data");
			System.out.println("2. Invade");
			System.out.println("0. Exit");
			
			int choice = errorTrap("\nWhat would you like to do? ", 0, 2);
			if(choice == 0)
				exit = true;
			else if(choice == 1) 
				viewData(p);
			else if(choice == 2) {
				//ADD THIS AFTER BUILDING ITS METHOD
			}
		}
		
		return exit;
	}
	
	// method shows information of planet after selecting View Data
	// ADD REST OF INFORMATION VIA ARRAYS
	public static void viewData(planet p) {
		System.out.println(p.name);
		System.out.println("===========================================");
		if(p.allegiance == -1) {
			System.out.println("\nAllegiance: REBEL");
			System.out.println("Income (if invaded): " + p.income);
			System.out.println("Defenses: "); // display fleet somehow, array?
		}
		else if(p.allegiance == 0) {
			System.out.println("\nAllegiance: NEUTRAL");
			System.out.println("Income (if invaded): " + p.income);
		}
		else if(p.allegiance == 1) {
			System.out.println("\nAllegiance: IMPERIAL");
			System.out.println("\nIncome: " + p.income);
			System.out.println("Facilities: "); // display facilities via array
			for(int i=0; i < p.numberFacilities; i++) {
				System.out.println(p.boughtFacilities[i]);
			}
		}
		errorTrap("\nEnter 0 to exit: ", 0, 0);
	}
	
	// method takes in a planet object and displays its store after selecting Trade
	public static void resourceStore(planet p) { 
		System.out.println("\nCredits: " + credits);
		System.out.println("\nSTORE");
		System.out.println("\nKyber crystals: " + p.crystals);
		System.out.println("\nOre: " + p.ore);
		System.out.println("\nFuel: " + p.fuel);
		
		System.out.println("\n1.Purchase Kyber crystals ("+ p.crystalsPrice + " credits)"); // try using credits symbol?
		System.out.println("2.Purchase Ore (" + p.orePrice + " credits)");
		System.out.println("3.Purchase Fuel (" + p.fuelPrice + " credits)");
		System.out.println("0.Exit");
	}
	
	// method for purchasing from a store
	public static boolean purchase(planet p) {
		boolean exit = false;
		int choice = errorTrap("\nWhat would you like to do? ", 0, 3);
		
		if(choice == 0)
			exit = true;
		else if(choice == 1) {
			int quantity = errorTrap("\nEnter quantity: ", 0, p.crystals);
			
			if(credits >= quantity * p.crystalsPrice) {
				p.crystals = p.crystals - quantity;
				crystals = crystals + quantity;
				credits = credits - quantity * p.crystalsPrice;
				System.out.println(quantity + " crystals purchased.");
			}
			else
				System.out.println("Not enough credits.");
		}
		else if(choice == 2) {
			int quantity = errorTrap("\nEnter quantity: ", 0, p.ore);
			
			if(credits >= quantity * p.orePrice) {
				p.ore = p.ore - quantity;
				ore = ore + quantity;
				credits = credits - quantity * p.orePrice;
				System.out.println(quantity + " ore purchased.");
			}
			else
				System.out.println("Not enough credits.");
		}
		else if(choice == 3) {
			int quantity = errorTrap("\nEnter quantity: ", 0, p.fuel);
			
			if(credits >= quantity * p.fuelPrice) {
				p.fuel = p.fuel - quantity;
				fuel = fuel + quantity;
				credits = credits - quantity * p.fuelPrice;
				System.out.println(quantity + " fuel purchased.");
			}
			else
				System.out.println("Not enough credits.");
		}
		
		return exit;
	}
	
	public static boolean buildFacilities(planet p) {
		System.out.println("\nFACILITIES:\n");
		
		for(int i=0; i<5; i++) {
				System.out.print(i+1 + ". "); // numbers the list of facilities available to build
				System.out.println(p.facilities[i] + "   (" + p.facilityPrices[i] + " credits)");
		}
		System.out.println("0. Exit");
		
		int choice = errorTrap("\nWhat would you like to build?", 0, 5);
		
		boolean exit = false;
		if(choice == 0)
			exit = true;
		// +3 to daily crystal income
		else if(choice == 1) {
			if(credits >= p.facilityPrices[0] && p.numberFacilities < 5) {
				credits = credits - p.facilityPrices[0];
				p.boughtFacilities[p.numberFacilities] = p.facilities[0];
				p.incomeC = p.incomeC + 3;
				p.numberFacilities++;
			}
			else if(credits < p.facilityPrices[0] && p.numberFacilities < 5)
				System.out.println("Not enough credits.");
			else if(p.numberFacilities == 5)
				System.out.println("Maximum number of facilities reached.");
		}
		// +3 to daily ore income
		else if(choice == 2) {
			if(credits >= p.facilityPrices[1] && p.numberFacilities < 5) {
				credits = credits - p.facilityPrices[1];
				p.boughtFacilities[p.numberFacilities] = p.facilities[1];
				p.incomeO = p.incomeO + 3;
				p.numberFacilities++;
			}
			else if(credits < p.facilityPrices[1] && p.numberFacilities < 5)
				System.out.println("Not enough credits.");
			else if(p.numberFacilities == 5)
				System.out.println("Maximum number of facilities reached.");
		}
		// +3 to daily fuel income
		else if(choice == 3) {
			if(credits >= p.facilityPrices[2] && p.numberFacilities < 5) {
				credits = credits - p.facilityPrices[2];
				p.boughtFacilities[p.numberFacilities] = p.facilities[2];
				p.incomeF = p.incomeF + 3;
				p.numberFacilities++;
			}
			else if(credits < p.facilityPrices[2] && p.numberFacilities < 5)
				System.out.println("Not enough credits.");
			else if(p.numberFacilities == 5)
				System.out.println("Maximum number of facilities reached.");
		}
		// 10% increase to income
		else if(choice == 4) {
			if(credits >= p.facilityPrices[3] && p.numberFacilities < 5) {
				credits = credits - p.facilityPrices[3];
				p.boughtFacilities[p.numberFacilities] = p.facilities[3];
				p.income = (int) (p.income * 1.1); // rounded to an integer
				p.numberFacilities++;
			}
			else if(credits < p.facilityPrices[3] && p.numberFacilities < 5)
				System.out.println("Not enough credits.");
			else if(p.numberFacilities == 5)
				System.out.println("Maximum number of facilities reached.");
		}
		// 20% decrease to chance of being attacked
		else if(choice == 5) {
			if(credits >= p.facilityPrices[4] && p.numberFacilities < 5) {
				credits = credits - p.facilityPrices[4];
				p.boughtFacilities[p.numberFacilities] = p.facilities[4];
				p.chanceAttack = p.chanceAttack * 0.8;
				p.numberFacilities++;
			}
			else if(credits < p.facilityPrices[4] && p.numberFacilities < 5)
				System.out.println("Not enough credits.");
			else if(p.numberFacilities == 5)
				System.out.println("Maximum number of facilities reached.");
		}
		
		return exit;
	}
	
	// battle with two fleets, if ship is destroyed it is permanent, win battle if all enemy ships are destroyed, can retreat
	//HAVE ARRAYS SORTED BEFOREHAND OR SOMETHING
	// SIMPLE TURN ORDER, NOT ALTERNATING
	public static void battle(ship[] player, ship[] enemy) {
		// ADD DO LOOP HERE
		// display both fleets first
		System.out.println("\nENEMY FLEET:");
		for(int i=0; i < enemy.length; i++) {
			if(enemy[i].health == 0)
				System.out.print(i + "." + enemy[i] + "(DESTROYED)   ");
			else
				System.out.print(i + "." + enemy[i] + "   ");
		}
		System.out.println("\nPLAYER FLEET:");
		for(int i=0; i < player.length; i++) {
			if(player[i].health == 0)
				System.out.print(i + "." + player[i] + "(DESTROYED)   ");
			else
				System.out.print(i + "." + player[i] + "   ");
		}
		
		int targetChoice, choice, dmgDealt;
		boolean hit;
		for(int i=0; i < player.length; i++) {
			if(player[i].health > 0) {
				if(player[i].name.equals("TIE fighter") || player[i].name.equals("TIE interceptor")) {
					System.out.println();
					System.out.println("Player turn: " + player[i].name);
					
					// DO LOOP THIS, ACCOUNT FOR RETREAT
					do {
						targetChoice = errorTrap("\nSelect an enemy (-1 to retreat): ", -1, enemy.length);
						System.out.println("1. View enemy data");
						System.out.println("2. Attack - Laser DMG: " + player[i].laser);
						System.out.println("0. Reselect enemy");
						
						do {
							choice = errorTrap("\nWhat would you like to do? ", 0, 2);
							if(choice == 1) {
								System.out.println(enemy[choice].name);
								System.out.println("Shields: " + enemy[choice].shield);
								System.out.println("Health: " + enemy[choice].health);
								System.out.println("Speed: " + enemy[choice].speed);
								System.out.println("Laser DMG: " + enemy[choice].laser);
								System.out.println("Ion DMG: " + enemy[choice].ion);
								
								errorTrap("\nEnter 0 to continue: ", 0, 0);
							}
							else if(choice ==2) {
								hit = hitOrMiss(player[i].speed, enemy[choice].speed);
								if(hit) {
									// calculate damage dealt to enemy health
									dmgDealt = player[i].laser - enemy[choice].shield;
									if(dmgDealt < 0)
										dmgDealt = 0;
									
									enemy[choice].health = enemy[choice].health - dmgDealt;
									if(enemy[choice].health < 0)
										enemy[choice].health = 0;
									
									System.out.println("Enemy health hit for " + dmgDealt + " damage");
								}
								else
									System.out.println("Your attack missed!");
							}
							
						} while(choice != 2); // end actions after attacking or using ability
						
					} while(choice == 0);
					
				}
			}
			else {
				System.out.println("\nShip is destroyed.");
				errorTrap("\nEnter 0 to pass turn: ",0,0);
			}
		}
		 // ENDS WHEN EITHER FLEET IS WIPED OR PLAYER RETREATS
		
	}
	
	// method uses RNG to determine if an attack hits or misses
	public static boolean hitOrMiss(int attacking, int defending) {
		boolean hit = true;
		int speedDifference =  attacking - defending;
		double minimum = 0;
		
		if(speedDifference >= 4) {
			minimum = 0.1;
		}
		else if(speedDifference >= 2 && speedDifference < 4) {
			minimum = 0.2;
		}
		else if(speedDifference >= 1 && speedDifference < 2) {
			minimum = 0.3;
		}
		else if(speedDifference >= 0 && speedDifference < 1) {
			minimum = 0.3;
		}
		else if(speedDifference >= -1 && speedDifference < 0) {
			minimum = 0.4;
		}
		else if(speedDifference >= -3 && speedDifference < -1) {
			minimum = 0.5;
		}
		else if(speedDifference <= -4) {
			minimum = 0.75;
		}
		
		if(Math.random() > minimum)
			hit = true; // hit
		else
			hit = false; // missed
		
		return hit;
	}
	
	// handles everything that will occur in a day, every time a day is passed call this method
	public static void passDay() {
		// receive all income
		for(int i = 0; i < numberPlanets; i++) {
			if(map[i].allegiance == 1) {
				// credits = credits + map[i].income; income is monthly, figure this out later
				crystals = crystals + map[i].incomeC;
				ore = ore + map[i].incomeO;
				fuel = fuel + map[i].incomeF;
			}
		}
		// monthly review and income
		if(day%30 == 0 && day != 0) {
			
		}
		// ADD ATTACKS, INCIDENTS, MONTHLY REVIEWS+INCOME, REFRESHES
		
		day++;
	}
	
	// error trap method 
	public static Scanner input = new Scanner(System.in);
	public static int errorTrap(String ask, int min, int max) {
		int in = 0; boolean success;
		do{
			System.out.print(ask);
			success = true;
			try {
				in = input.nextInt();
			}
			catch(Exception e) {
				input.nextLine();
				success = false;
			}
		}while(in < min || in > max || !success);
		return in;
	}

}
