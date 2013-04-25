package recEngine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;
import java.util.regex.*;
/**
 * Parser parses the webpage and stores things in database.
 * @author Zhu Cai
 *
 */
public class Parser {

	/**
	 * Parses the restaurants.
	 * @param tableName The table we need to update.
	 */
	public void parse(String tableName){
		Restaurant bombay = new Restaurant("Bombay Garden");
		Restaurant viognier = new Restaurant("the Viognier Restaurant");
		parseBombay(bombay);
		parseViogner(viognier);
		DBManager.updateTable(bombay, tableName);
		DBManager.updateTable(viognier, tableName);

	}

	/**
	 * Updates the restaurant with required info.
	 * @param res The restaurant we need to update.
	 */
	public void parseBombay(Restaurant res){
		try {
			/*Get phone and address*/
			String url = "http://www.dinebombaygarden.com/";
			Document doc = Jsoup.connect(url + "contacts.php").get();
			Elements elems = doc.getElementsByAttributeValueContaining("class", "right-data");
			Element loc = elems.get(1);
			Pattern p = Pattern.compile("(.*)(\\(.*)");
			for(int i = 0; i < 3; i++){
				Matcher m = p.matcher(loc.child(i).text());
				if(m.find()){
					res.setAddress(m.group(1));
					res.setPhone(m.group(2));
				}
			}
			/*Get menu and recipe*/
			doc = Jsoup.connect(url + "menus.html").get();
			elems = doc.getElementsByAttributeValueContaining("class", "element");
			Element el = elems.get(0);
			elems = el.getElementsByAttribute("href");
			for(int i = 0; i < elems.size(); i++){

				parseBombayMenu(url + elems.get(i).attr("href"), res);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Parses the menu items for Bombay Garnden.
	 * @param url Webpage URL we need.
	 * @param res Restaurant we need to update.
	 */
	public void parseBombayMenu(String url, Restaurant res){
		try {
			/*Get Recipe*/
			Document doc = Jsoup.connect(url).get();
			Elements elems = doc.getElementsByAttributeValueContaining("class", "left-data");
			Element el = elems.get(0);
			/*Get Menu Name*/
			elems = el.getElementsByTag("h3");
			Pattern p = Pattern.compile("(.*)(\\......)");
			for(Element e: elems){
				Matcher m = p.matcher(e.text());
				if(m.find()){
					String menuName = m.group(1).replaceAll("\\. ","").replaceAll("\\.", "");
					if(e.nextElementSibling() != null){
						res.setRecipe(e.nextElementSibling().text().replaceAll("'", ""));
					}
					else{
						res.setRecipe(null);
					}
					res.setMenu(menuName.replaceAll("'", ""));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * Parses the viognier restaurant.
	 * @param res The restaurant we need to support.
	 */
	public void parseViogner(Restaurant res){
		try {
			/*Get address and phone*/
			String url = "http://www.viognierrestaurant.com/";
			Document doc = Jsoup.connect(url + "contact.html").get();
			Elements elems = doc.getElementsByAttributeValueContaining("class", "col-a");
			Element el = elems.get(0);
			Pattern p = Pattern.compile("(Restaurant )(.*)(\\(650\\).*)(Online)");
			Matcher m = p.matcher(el.child(1).text());
			if(m.find()){
				res.setAddress(m.group(2));
				res.setPhone(m.group(3));
			}
			/*Get menu and recipe*/
			doc = Jsoup.connect(url + "menus.html").get();
			String data = doc.data();
			p = Pattern.compile("(load\\(\")(.*)(\")");
			m = p.matcher(data);
			while(m.find()){
				parseViognerMenu(url + m.group(2), res);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * Parses the menu items for the viognier restaurant.
	 * @param url Webpage URL we need.
	 * @param res Restaurant we need to update.
	 */
	public void parseViognerMenu(String url, Restaurant res){
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elems = doc.getElementsByTag("li");
			for(Element e: elems){
				Elements el = e.getElementsByTag("h2");
				res.setMenu(el.text().replaceAll("'", ""));
				res.setRecipe(e.text().replaceAll(el.text(), "").replaceAll("'", ""));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
