package c9q4;
import java.util.*;

public class AllSubSets {
	public static void main(String[] args){
		ArrayList<Integer> s = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allSubSets;
		s.add(1);
		s.add(2);
		s.add(3);
		allSubSets = new AllSubSets().getSubSets(s, 0);
		System.out.println(allSubSets.size());
	}
	public ArrayList<ArrayList<Integer>> getSubSets(ArrayList<Integer> set, int level){
		ArrayList<ArrayList<Integer>> allSubSets;
		if(set.size() == level){  //starts from here
			allSubSets = new ArrayList<ArrayList<Integer>>();
			allSubSets.add(new ArrayList<Integer>());
			return allSubSets;
		}
		
		allSubSets = getSubSets(set, level + 1);
		ArrayList<ArrayList<Integer>> moreSubSets = new ArrayList<ArrayList<Integer>>();
		int item = set.get(level);
		for(ArrayList<Integer> subSet: allSubSets){
			ArrayList<Integer> newSet = new ArrayList<Integer>();
			newSet.addAll(subSet);
			newSet.add(item);
			moreSubSets.add(newSet);
		}
		allSubSets.addAll(moreSubSets);
		return allSubSets;
	}
}
