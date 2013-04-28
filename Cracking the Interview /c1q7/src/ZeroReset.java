
public class ZeroReset {
	public static void main(String[] ars){
		int[][] matrix = {{1,2,3,4},{3,5,7,1},{0,2,2,2}};
		new ZeroReset().zeroReset(matrix);
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(new ZeroReset().countParam(1,2,3,4,5,5,5,5,5));
	}

	public void zeroReset(int[][] matrix){
		boolean[] rows = new boolean[matrix.length];
		boolean[] cols = new boolean[matrix[0].length];
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				if(matrix[i][j] == 0){
					rows[i] = true;
					cols[j] = true;
				}
			}
		}
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				if(rows[i]||cols[j]){
					matrix[i][j] = 0;
				}
			}
		}
		
	}
	
	public int countParam(int...parameters){
		return parameters.length;
	}
}
