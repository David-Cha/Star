package codes;

public class planet {
	String name;
	int allegiance, discovered, income, distance, // for allegiance, -1 is enemy, 0 is neutral, 1 is ally, for discovered, 0 is undiscovered, 1 is discovered
	incomeC = 0, incomeO = 0, incomeF = 0, // bonuses from facilities
	crystals, ore, fuel, crystalsPrice, orePrice, fuelPrice;
	
	double chanceAttack;
	
	String[] facilities = new String[5];
	int[] facilityPrices = new int[5]; // randomly generated later, multiples of 50
	int numberFacilities = 0;
	String[] boughtFacilities = new String[]{"", "", "", "", ""}; // once a facility is bought it is added here and removed from original array
	
	planet(String n, int a, int c, int o, int f, int cP, int oP, int fP) {
		name = n;
		allegiance = a;
		crystals = c;
		ore = o;
		fuel = f;
		crystalsPrice = cP;
		orePrice = oP;
		fuelPrice = fP;
		
		facilities[0] = "Crystal mine: +3 crystals / day";
		facilities[1] = "Ore mine: +3 ore / day";
		facilities[2] = "Fuel refinery: +3 fuel / day";
		facilities[3] = "Tariffs port: +10% increase to income from planet";
		facilities[4] = "Garrison: -20% chance of planet being attacked";
	}
	
}
