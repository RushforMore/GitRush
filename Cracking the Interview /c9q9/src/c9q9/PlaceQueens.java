package c9q9;
import java.util.*;
public class PlaceQueens {
	static int GRID_SIZE = 8;
	
	public static void main(String[] args){
		ArrayList<Integer[]> results = new ArrayList<Integer[]>();
		Integer[] columns = new Integer[GRID_SIZE];
		new PlaceQueens().placeQueens(0, columns, results);
		for(Integer[] res: results){
			for(int i = 0; i <res.length; i++){
				System.out.print(res[i] + ", ");
			}
			System.out.println();
		}
	}
	
	public void placeQueens(int row, Integer[] columns, ArrayList<Integer[]> results){
		if(row == GRID_SIZE){
			results.add(columns.clone());
		}
		else{
			for(int col = 0; col < GRID_SIZE; col++){
				if(isValid(row, col, columns)){
					columns[row] = col;
					placeQueens(row + 1, columns, results);//this line should be right after you decided your column
				}
			}
		}
	}
	
	boolean isValid(int row, int col, Integer[] columns){
		for(int r = 0; r < row; r++){
			if(columns[r] == col){
				return false;
			}
			int rowDist = row - r;
			int colDist = Math.abs(columns[r] - col);
			if(rowDist == colDist){
				return false;
			}
		}
		return true;
	}
}
