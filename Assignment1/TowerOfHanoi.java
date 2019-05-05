public class TowerOfHanoi {
	
	public static void toh_with_recursion(int num_disks, int start_pos, int end_pos) {
		if (num_disks ==1) {
			System.out.println(start_pos + " " + end_pos);
		}
		else if(num_disks ==0) {
			return;
		}
		else {
			
			toh_with_recursion(num_disks -1 , start_pos, (6-(start_pos+end_pos)));
			System.out.println(start_pos + " "+ end_pos);
			toh_with_recursion(num_disks - 1,(6-(start_pos+end_pos)),end_pos);
			
			
		}
		
	}		
	 

	
	public static void toh_without_recursion(int num_disks,int start_pos,int end_pos) {
		
		MyStack<Integer> stack1 = new MyStack<Integer>();
		stack1.push(end_pos);
		stack1.push(start_pos);
		stack1.push(num_disks);
		while(stack1.empty() == false) {
			int numberDisks = stack1.pop();
			if(numberDisks ==1) {
				int startPos = stack1.pop();
				int endPos = stack1.pop();
				System.out.println(startPos+" "+endPos);
				continue;
			}
			else if(numberDisks ==-1) {
				int startPos = stack1.pop();
				int endPos = stack1.pop();
				System.out.println(startPos+" "+endPos);
				continue;
			}
			
				num_disks = numberDisks-1;
				int stTemp = stack1.pop();
				int endTemp = stack1.pop();
				stack1.push(endTemp);
				stack1.push(6-(stTemp+endTemp));
				stack1.push(num_disks);
				stack1.push(endTemp);
				stack1.push(stTemp);
				stack1.push(-1);
				stack1.push(6-(stTemp+endTemp));
				stack1.push(stTemp);
				stack1.push(num_disks);

			
		}
		return;
		
		
	}
}

