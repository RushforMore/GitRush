package recEngine;

/**
 * Makes recommendation.
 * @author Zhu Cai
 *
 */
public class Recommendation {
	static Parser parser = new Parser();

	/**
	 * Do recommendation depends on keywords.
	 * @param args Keywords for recommendation.
	 */
	public static void main(String[] args){
		if(args.length == 0){
			return;
		}
		else{
			String tableName = "restaurant";
			DBManager.createTable(tableName);
			parser.parse(tableName);
			for(int i = 0; i < args.length; i++){
				System.out.println("The recommendation for " + args[i] + " are:");
				DBManager.recommend(args[i], tableName);
				System.out.println();
			}
		}
	}
}
