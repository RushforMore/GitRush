package c9q10;
import java.util.*;
public class MaxStack {
	
	public static void main(String[] args){
		Box[] boxes = new Box[3];
		for(int i = 0; i < 3; i++){
			boxes[i] = new Box(i + 1, i + 1, i + 1);
		}
		ArrayList<Box> maxStack = new ArrayList<Box>();
		Hashtable<Box, ArrayList<Box>> box_map = new Hashtable<Box, ArrayList<Box>>();
		maxStack = new MaxStack().createStack(boxes, boxes[0], box_map);//we are going to experiment with each box we got as bottom
		//and get the largest one
		for(Box b: maxStack){
			System.out.println(b.height);
		}
	}
	
	ArrayList<Box> createStack(Box[] boxes, Box bottom, Hashtable<Box, ArrayList<Box>> box_map){
		if(box_map.containsKey(bottom) && bottom!=null){
			return box_map.get(bottom);
		}
		ArrayList<Box> maxStack = null;
		int maxHeight = 0;
		for(int i = 0; i < boxes.length; i++){
			if(boxes[i].canBeAbove(bottom)){
				ArrayList<Box> newStack = createStack(boxes, boxes[i], box_map);
				int newHeight = stackHeight(newStack);
				if (newHeight > maxHeight){
					maxHeight = newHeight;
					maxStack = newStack;
				}
			}
		}
		if(maxStack == null){
			maxStack = new ArrayList<Box>();
		}
		if(bottom != null){
			maxStack.add(0, bottom);
		}
		/*what you didn't come up first time*/
		box_map.put(bottom, maxStack);
		return (ArrayList<Box>)maxStack.clone();
	}
	
	int stackHeight(ArrayList<Box> boxes){
		int sum = 0;
		for(Box b: boxes){
			sum += b.height;
		}
		return sum;
	}
}
