package risk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RiskBoard {
	private List<Territory> territories;
	
	// A new board will be blank, with no territories and no connections
	public RiskBoard(){ 
		territories = new ArrayList<Territory>();
	}

	/**
	 * Will attempt to setup the board based on an input file. Territories 
	 * will be added first and then routes will be set up.
	 * <p>
	 * A valid file should contain Regions in the format "Region: Name".
	 * followed by the names of territories. After all the territories 
	 * in that region should be a blank line. (You can have as many regions as you like.)
	 * Following all the regions and territories, the routes are set up by starting 
	 * a line with "Routes:". This should be followed by a list of each connection 
	 * (routes are all bi-directional) in the format "Place-Place2".
	 * 
	 * @param fileName 	the name of a file containing valid board information
	 **/
	public void setup(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			while(br.ready()){
				String input = br.readLine();
				
				if (input.contains("Region: ")){
					// setup regions
					this.setupRegions(br);
				}else if(input.contains("Routes: ")){
					// setup routes
					this.setupRoutes(br);
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: ");
			e.printStackTrace();
		} catch (IOException e){
			System.out.println("File read error: ");
			e.printStackTrace();
		}
	}

	/**
	 * Private method takes a BufferedReader and reads in each line, 
	 * adds connection to territory objects in the territories list until 
	 * it reaches a blank line or end of file.
	 * 
	 * @param br 	a BufferedReader object of the file with setup information
	 **/
	private void setupRoutes(BufferedReader br) throws IOException {
		// TODO stub
	}
	
	/**
	 * Private method takes a BufferedReader and reads in each line, 
	 * creates a territory object, and adds it to the territories list until 
	 * it reaches a blank line or end of file.
	 * 
	 * @param br 	a BufferedReader object of the file with setup information
	 **/
	private void setupRegions(BufferedReader br) throws IOException {
		while(br.ready()){
			String input = br.readLine();
			if(input.equals("")) return;
			else territories.add(new Territory(input));
		}
	}

	/**
	 * Get method for territories.
	 * @return	the territories in List<Territory> form.
	 **/
	public List<Territory> getTerritories() {return territories;}

	/**
	 * Method to get the number of troops in a particular territory.
	 * Method will return 0 for calls without a valid name.
	 * 
	 * @param territory	the name of the territory to get info from
	 * @return		returns the number of troops in a territory
	 **/
	public int getTroops(String territory) {
		for(Territory terra : territories){
			if (terra.getName().equals(territory)){
				return terra.getTroops();
			}
		}
		return 0;
	}

	/**
	 * Method to add (positive number) or subtract (negative number) troops from a given territory.
	 * Any calls without a valid name will be ignored.
	 * 
	 * @param territory	the name of the territory to add to
	 * @param num		the number of troops to add(or subtract)
	 **/
	public void addTroops(String territory, int num) {
		for(Territory terra : territories){
			if (terra.getName().equals(territory)){
				terra.setTroops(terra.getTroops() + num);
			}
		}
	}

	/**
	 * Method to subtract (positive number) or add (negative number) troops from a given territory.
	 * Any calls without a valid name will be ignored. This method will check to ensure the number 
	 * does not fall below 0.
	 * 
	 * @param territory	the name of the territory to add to
	 * @param num		the number of troops to subtract(or add)
	 **/
	public void removeTroops(String territory, int num) {
		for(Territory terra : territories){
			if (terra.getName().equals(territory)){
				int troops = terra.getTroops() - num;
				if(troops > 0){
					terra.setTroops(troops);
				} else {
					terra.setTroops(0);
				}
			}
		}
	}

}