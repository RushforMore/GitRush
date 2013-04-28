package c9q10;

public class Box {
	 int height;
	 int width;
	 int depth;
	
	public Box(int h, int w, int d){
		height = h;
		width = w;
		depth = d;
	}
	
	public boolean canBeAbove(Box box){
		if(height < box.height && width < box.width && depth < box.depth){
			return true;
		}
		return false;
	}
	
}
