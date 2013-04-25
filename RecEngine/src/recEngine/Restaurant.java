package recEngine;
import java.util.*;

/**
 * Restaurant holds the data of a restaurant.
 * @author Zhu Cai
 *
 */
public class Restaurant {
	private String name;
	private ArrayList<String> phone;
	private ArrayList<String> address;
	private ArrayList<String> menu;
	private ArrayList<String> recipe;

	public Restaurant(String name){
		this.name = name;
		phone = new ArrayList<String>();
		address =  new ArrayList<String>();
		menu = new ArrayList<String>();
		recipe = new ArrayList<String>();
	}

	public void setPhone(String phoneNum){
		phone.add(phoneNum);
	}

	public void setAddress(String addr){
		address.add(addr);
	}

	public void setMenu(String m){
		menu.add(m);
	}

	public void setRecipe(String r){
		recipe.add(r);
	}

	public String getName(){
		return this.name;
	}

	public ArrayList<String> getPhone(){
		return phone;
	}

	public ArrayList<String> getAddress(){
		return address;
	}

	public ArrayList<String> getMenu(){
		return menu;
	}

	public ArrayList<String> getRecipe(){
		return recipe;
	}
}
