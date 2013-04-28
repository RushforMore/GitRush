package c11q5;

public class SearchStr {
	public static void main(String[] args){
		String[] strings = {"asdf", "", "", "asdfsa", "", "asdfsdfdfs", "target", "", "sadfasf"};
		System.out.println(new SearchStr().searchR(strings, "target", 0, strings.length - 1));
	}
	
	public int searchR(String[] strings, String str, int left, int right){
		if(left > right) return  -1;
		int mid = (left + right) / 2;
		if(strings[mid].isEmpty()){
			int l = mid - 1;//mind this!!!
			int r = mid + 1;
			while(true){
				if(l < left && r > right){
					return -1;
				}
				else if(l >= left && !strings[l].isEmpty()){
					mid = l;
					break; //remember to break!!!
				}
				else if(r <= right && !strings[r].isEmpty()){
					mid = r;
					break;
				}
				l--;
				r++;
			}
			
		}
		if(strings[mid].equals(str)){
			return mid;
		}
		else if(strings[mid].compareTo(str) < 0){
			return searchR(strings, str, mid + 1, right);
		}
		else if(strings[mid].compareTo(str) > 0){
			return searchR(strings, str, left, mid - 1);
		}
		return -1;
	}
}
