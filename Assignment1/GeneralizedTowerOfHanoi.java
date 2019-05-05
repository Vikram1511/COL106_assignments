
public class GeneralizedTowerOfHanoi extends TowerOfHanoi {
	
       public static void gtoh_with_recursion(int num_disks,int start_pos,int r,int b) {
	 
		                                     
    	                      // *** CASE-1***//
	 
		// if all the parameters are equal than we do not have to do anything
		if(start_pos==r && r==b) {
			               return;
		 }
		
		
		                                    // *** CASE-2***//
		
		
		// if we want to put all the odds and even disks in the same rod 
		//for both odd and even number of disks
		else if(r ==b && start_pos != r) {
			
			        if((start_pos ==1 || start_pos ==2)&&(r==2||r==1)) {
			        	moveSrcToEnd(num_disks,start_pos,r);
		                     	return;
		         	}
		        	else {
		        		moveSrcToEnd(num_disks,start_pos,r);
			                	return;
			        }
			
		  }

		
		                                     // *** CASE-3***//
		
		
		//for odd disks placed on the initial rod
		else if(start_pos ==r && r !=b) {
			
			//for even number of disks
				if(num_disks %2==0) {
			        if(num_disks==1) {
			                 	System.out.println(start_pos+" "+b);
			        }
			        else if(num_disks==2) {
			        	System.out.println(start_pos+" "+b);
			        }
			        else {
			                    moveSrcToEnd(num_disks - 2,start_pos,(6-(r+b)));
			                    System.out.println(start_pos+" "+b);
			                    moveSrcToEnd(num_disks - 2 ,(6-(r+b)),start_pos );
			                    num_disks = num_disks-3;
			                    gtoh_with_recursion(num_disks,start_pos,r,b);
		            }
				}
				
			//for odd number of disks
				else {
					if(num_disks ==3) {
						moveSrcToEnd(num_disks - 1,start_pos,(6-(r+b)));
						System.out.println(start_pos + " "+ b);
						System.out.println((6-(r+b))+" "+b);
						System.out.println((6-(r+b))+" "+r);
						
					}
					else if(num_disks==1) {
						System.out.println(start_pos + " "+ b);
					}
					else {
						moveSrcToEnd(num_disks - 1,start_pos,(6-(r+b)));
						System.out.println(start_pos + " " +b );
	                    moveSrcToEnd(num_disks - 2 ,(6-(r+b)),b );
	                    System.out.println((6-(r+b))+" "+ r);
	                    moveSrcToEnd(num_disks - 3,b,start_pos );
	                    gtoh_with_recursion(num_disks - 4,start_pos,r,b);
						
					}
				}
				
		 }
		
												// *** CASE-4***//
		
		//for even disks placed on the initial road
		 else if(start_pos != r && start_pos == b) {
			 //for even number of disks
			 	if(num_disks%2 ==0) {
			        if(num_disks ==2) {
				              System.out.println(start_pos+" "+(6-(r+b)));
				              System.out.println(start_pos+" "+r);
				              System.out.println((6-(r+b))+" "+start_pos);
			        }
			        else if(num_disks ==0) {
			        	return;
			        }
			        else{
				                 moveSrcToEnd(num_disks-1,start_pos,(6-(r+b)));
				                 System.out.println(start_pos+" "+ r );
				                 moveSrcToEnd(num_disks - 2 , (6-(r+b)),r);
				                 System.out.println((6-(r+b))+" "+start_pos );
				                 moveSrcToEnd(num_disks-3,r,start_pos);
				                 gtoh_with_recursion(num_disks-4,start_pos,r,b);
			        }
			 	}
			 	
			 //for odd number of disks
			 	
			 	else {
			 		if(num_disks==1) {
			 					return;
			 		}
			 		else {
			 			 moveSrcToEnd(num_disks-1,start_pos,r);
		                 moveSrcToEnd(num_disks - 2 , r,start_pos);
		                 gtoh_with_recursion(num_disks-3,start_pos,r,b);
			 			
			 		}
			 	}
			        
		}
		
		
										        // *** CASE-5***//
		
		//for different rods of odd and even disks
		
		else {
				//if number of disks is even
			if(num_disks % 2==0) {
		          if(num_disks==2) {
	                      System.out.println(start_pos + " "+ b);
			              System.out.println(start_pos +  " "+ r);
		           }
		          else if(num_disks==0) {
		        	  		return;
		           }
		          else {
		            		
		            	      moveSrcToEnd(num_disks -1,start_pos,b);
			                  System.out.println(start_pos + " "+ r);
			                  moveSrcToEnd(num_disks -3,b,start_pos);
			                  System.out.println(b+" "+r);
			                  gtoh_with_recursion(num_disks - 3,start_pos,r,b);
		            
		            	}
		            }
				
		        	//if number of disks is odd
				
				else {
					if(num_disks ==1) {
									System.out.println(start_pos + " "+ b);
						
					}
					else if(num_disks==0) {
						return;
					}
					else {
            			
          			      moveSrcToEnd(num_disks -1,start_pos,r);
	                      System.out.println(start_pos + " "+ b);
	                      moveSrcToEnd(num_disks -3,r,start_pos);
	                      System.out.println(r + " "+ b);
	                      gtoh_with_recursion(num_disks - 3,start_pos,r,b);
          			
					}	
				
				}
				
		
	     }
	
  }
	
	
	
  
  public static void gtoh_without_recursion(int num_disks,int start_pos,int r, int b) {
	  
	  		MyStack<Integer> stack2 = new MyStack<Integer>();
	  								//**** CASE-1***
	  		if(start_pos==r && r==b) {
	  			return;
	  		}
	  							
	  								       //**** CASE-2***
	  		
	  		 
	  		else if(start_pos !=r && r==b) {
	  				moveSrcToEnd_iterative(num_disks,start_pos,r );
	  		}
	  		
	  		
	  								       //**** CASE-3***
	  		
	  		
	  		else if (start_pos ==r && r !=b) {
	  			
	  			stack2.push(b);
	  			stack2.push(r);
	  			stack2.push(start_pos);
	  			stack2.push(num_disks);
	  			while(stack2.empty()==false) {
	  				num_disks = stack2.pop();
	  				if(num_disks==2) {
	  					 start_pos = stack2.pop();
	  					 r = stack2.pop();
	  					 b = stack2.pop();
	  					System.out.println(start_pos + " "+ b);
	  					continue;
	  				
	  				}
	  				else if(num_disks==0) {
	  					return;
	  				}
	  				else if(num_disks==3) {
	  					 start_pos = stack2.pop();
	  					 r = stack2.pop();
	  					 b = stack2.pop();
	  					moveSrcToEnd_iterative(num_disks - 1,start_pos,(6-(r+b)));
						System.out.println(start_pos + " "+ b);
						System.out.println((6-(r+b))+" "+b);
						System.out.println((6-(r+b))+" "+r);
						continue;
	  					
	  				}
	  				else if(num_disks==1) {
	  					start_pos = stack2.pop();
	  					r = stack2.pop();
	  					b = stack2.pop();
	  					System.out.println(start_pos + " "+ b);
	  					return;
	  				}
	  				else {
	  					 start_pos = stack2.pop();
	  					 r = stack2.pop();
	  					 b = stack2.pop();
	  					if(num_disks%2==0) {
	  						moveSrcToEnd_iterative(num_disks - 2,start_pos,(6-(r+b)));
	  						System.out.println(start_pos+" "+b);
	  		  				moveSrcToEnd_iterative(num_disks-2,(6-(r+b)),start_pos);
	  							stack2.push(b);
	  							stack2.push(r);
	  							stack2.push(start_pos);
	  							num_disks=num_disks-3;
	  							stack2.push(num_disks);
	  							continue;
	  					}
	  					else {
	  						moveSrcToEnd_iterative(num_disks - 1,start_pos,(6-(r+b)));
							System.out.println(start_pos + " " +b );
		                    moveSrcToEnd_iterative(num_disks - 2 ,(6-(r+b)),b );
		                    System.out.println((6-(r+b))+" "+ r);
		                    moveSrcToEnd_iterative(num_disks - 3,b,start_pos );
	  							stack2.push(b);
	  							stack2.push(r);
	  							stack2.push(start_pos);
	  							num_disks=num_disks-4;
	  							stack2.push(num_disks);
	  							continue;
	  					}
	  				}
	  				
  	  			  			
	  			}
	  			
	  			
	  			
	  			
	  			
	  			
	  			
	  		}
	  			
	  									       //**** CASE-4***
	  		
	  		
	  		else if(start_pos !=r && start_pos==b) {
	  			stack2.push(b);
	  			stack2.push(r);
	  			stack2.push(start_pos);
	  			stack2.push(num_disks);
	  			while(stack2.empty()==false) {
	  				num_disks = stack2.pop();
	  				if(num_disks==2) {
	  					 start_pos = stack2.pop();
	  					 r = stack2.pop();
	  					 b = stack2.pop();
	  						System.out.println(start_pos+" "+(6-(r+b)));
	  						System.out.println(start_pos+" "+r);
	  						System.out.println((6-(r+b))+" "+start_pos);
	  						continue;
	  				
	  				}
	  				else if(num_disks==0) {
	  					return;
	  				}
	  				else if(num_disks==1) {
	  					stack2.pop();
	  				    stack2.pop();
	  					stack2.pop();
	  					return;
	  				}
	  				else {
	  					 start_pos = stack2.pop();
	  					 r = stack2.pop();
	  					 b = stack2.pop();
	  					if(num_disks%2==0) {
	  						  moveSrcToEnd_iterative(num_disks-1,start_pos,(6-(r+b)));
		            		  System.out.println(start_pos+" "+ r );
		            		  moveSrcToEnd_iterative(num_disks - 2 , (6-(r+b)),r);
		            		  System.out.println((6-(r+b))+" "+start_pos );
		            		  moveSrcToEnd_iterative(num_disks-3,r,start_pos);
	  							stack2.push(b);
	  							stack2.push(r);
	  							stack2.push(start_pos);
	  							num_disks=num_disks-4;
	  							stack2.push(num_disks);
	  							continue;
	  					}
	  					else {
	  							moveSrcToEnd_iterative(num_disks-1,start_pos,r);
	  							moveSrcToEnd_iterative(num_disks - 2 , r,start_pos);
	  							stack2.push(b);
	  							stack2.push(r);
	  							stack2.push(start_pos);
	  							num_disks=num_disks-3;
	  							stack2.push(num_disks);
	  							continue;
	  					}
	  				}
	  				
  	  			  			
	  			}
	  			
	  			
	  			
	  			
	  		}
	  			
	  		
	  								              //*****CASE - 5****
	  		
	  		else{
	  			
	  			stack2.push(b);
	  			stack2.push(r);
	  			stack2.push(start_pos);
	  			stack2.push(num_disks);
	  			while(stack2.empty()==false) {
	  				int numberDisks = stack2.pop();
	  				if(numberDisks==2) {
	  					int startPos = stack2.pop();
	  					int redPos = stack2.pop();
	  					int blackPos = stack2.pop();
	  					System.out.println(startPos + " "+ blackPos);
			            System.out.println(startPos +  " "+ redPos);
			            continue;
	  				
	  				}
	  				else if(numberDisks==0) {
	  					return;
	  				}
	  				else if(numberDisks==1) {
	  					 stack2.pop();
	  					 stack2.pop();
	  					 stack2.pop();
	  					System.out.println(start_pos + " "+ b);
	  					continue;
	  				}
	  				else {
	  					 start_pos = stack2.pop();
	  					 r = stack2.pop();
	  					 b = stack2.pop();
	  					if(num_disks%2==0) {
	  							moveSrcToEnd_iterative(num_disks -1,start_pos,b);
	  							System.out.println(start_pos + " "+ r);
	  							moveSrcToEnd_iterative(num_disks -3,b,start_pos);
	  							System.out.println(b+" "+r);
	  							stack2.push(b);
	  							stack2.push(r);
	  							stack2.push(start_pos);
	  							num_disks=num_disks-3;
	  							stack2.push(num_disks);
	  					}
	  					else {
	  							moveSrcToEnd_iterative(num_disks -1,start_pos,r);
	  							System.out.println(start_pos + " "+ b);
	  							moveSrcToEnd_iterative(num_disks -3,r,start_pos);
	  							System.out.println(r + " "+ b);
	  							stack2.push(b);
	  							stack2.push(r);
	  							stack2.push(start_pos);
	  							num_disks=num_disks-3;
	  							stack2.push(num_disks);
	  					}
	  				}
  	  			  			
	  			}
	  			return;
	  		}
	  		
	  		
	  			
	
	  			
}
	  		
  
  public static void moveSrcToEnd(int num_disks, int start_pos, int end_pos) {
		if (num_disks ==1) {
			System.out.println(start_pos + " " + end_pos);
		}
		else if(num_disks ==0) {
			return;
		}
		else {
			
			moveSrcToEnd(num_disks -1 , start_pos, (6-(start_pos+end_pos)));
			System.out.println(start_pos + " "+ end_pos);
			moveSrcToEnd(num_disks - 1,(6-(start_pos+end_pos)),end_pos);
			
			
		}
		
	}
  
  public static void moveSrcToEnd_iterative(int num_disks,int start_pos,int end_pos) {
		
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
			else if(numberDisks ==0) {
				return;
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
