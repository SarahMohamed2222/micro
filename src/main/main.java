package main;

import java.util.Vector;

public class main {
	
	Vector<stations> Addstations;
    Vector<stations> Loadstations;
	Vector<stations> Mulstations;
	float[] Cache;
	Vector<IntRegister> IntRegisterfile;
	Vector<FloatRegister> FloatRegisterfile;
	Vector<instruction> instructions;
	int currentcycle=1;
	public void next()
	{
		
		for(instruction i :instructions)
		{ 
			if(i.issue==0) 
			{   
				System.out.print(i);
				issue(i);
				if(i.issue!=0)
				{
					break;
					
				}
			}
			
			
		}
		for(instruction i :instructions)
		{ 
			if(i.issue!=0) 
			{   
				if(i.exec[0]==0)
				{ 
					execute(i);
					
					if(i.exec[0]!=0)
					{ 
						break;
					}
					
				}
				
			}
			
			
		}

		for(instruction i :instructions)
		{ 
			if(i.issue!=0) 
			{   
				if(i.exec[0]!=0)
				{ 
					if(i.writeres==0)
					{ 
						writeres(i);

						if(i.writeres!=0)
						{ 
							break;
						}
					}
					
				}
				
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
	currentcycle++;
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	

	private void issue(instruction instruction) {
		 optype Type = instruction.type;
		  
		    
		    switch (Type) {
		        case addi  :
		            issueadd(instruction);
		            break;
		        case subi  :
		            issueadd(instruction);
		            break;
		        case addu  :
		            issueadd(instruction);
		            break;
		        case subu  :
		            issueadd(instruction);
		            break;  
		        case adds  :
		            issueaddf(instruction);
		            break;
		        case subs  :
		            issueaddf(instruction);
		            break;   
		        case muls:
		            issuemul(instruction);
		            break;
		        case divs:
		            issuemul(instruction);
		            break;
		        case lw:
		            issueld(instruction);
		            break;
		        case sw:
		            issueld(instruction);
		            break;
		        case bnez:
		        	issueadd(instruction);
		        	break;
		       
		        // Other operation cases
		        default:
		            // Handle unsupported operations or errors
		            break;
		    }

		
	}


	private void issueld(instruction instruction) {
		FloatRegister dest=null;
		for(FloatRegister R :FloatRegisterfile) {
			if(R.name.equals(instruction.destination)) {
				dest=R;
				break;
			}
		}
	IntRegister dest2=null;
		for(IntRegister R :IntRegisterfile) {
			if(R.name.equals(instruction.destination)) {
				dest2=R;
				break;
			}
		}

		for(stations s :this.Loadstations) {

			System.out.print(false);
			if(!s.busy) {
				s.busy=true;
				int index=this.Loadstations.indexOf(s)+1;
				if(dest!=null) {
					dest.qj="L"+index;
					instruction.issue=this.currentcycle;
					s.instruction=instruction;
				}else {

					dest2.qj="L"+index;
					instruction.issue=this.currentcycle;
					s.instruction=instruction;
					
				}
				break;
			}}
		
	}


	private void issuemul(instruction instruction) {
		FloatRegister dest=null;
		for(FloatRegister R :FloatRegisterfile) {
			if(R.name.equals(instruction.destination)) {
				dest=R;
				break;
			}
		}
		FloatRegister r2=null;
		for(FloatRegister R :FloatRegisterfile) {
			if(R.name.equals(instruction.source1)) {
				r2=R;
				break;
			}
		}
		FloatRegister r3=null;
		for(FloatRegister R :FloatRegisterfile) {
			if(R.name.equals(instruction.source2)) {
				r3=R;
				break;
			}
		}
		
		for(stations s :Mulstations) {
			if(!s.busy) {
			
				s.busy=true;
				s.instruction=instruction;
				instruction.issue=currentcycle;
				if(r2!=null) {
				s.Qj=r2.qj;
				}
				else {
					
				}
				if(r3!=null) {
				s.Qk=r3.qj;
				}
				if(s.Qj!=null) {
					s.vj=r2.value;
				}
				if(s.Qk!=null) {
					s.vk=r3.value;
				}
				int ziko=Mulstations.indexOf(s)+1;
				dest.qj="M"+ziko;
				return;
			}
		}
	}
		
	private void writeres(instruction instruction)
	{
		System.out.print(false);
		
		if(instruction.type.equals(optype.bnez)&&currentcycle==instruction.exec[1]) {
			for(stations s: Addstations) {
				if(s.busy&&s.instruction.equals(instruction)) {
					s.busy=false;
					s.destination=null;
					s.instruction=null;
					s.Qj=null;
					s.Qk=null;
					
				}
				
			}
			IntRegister r0 = null;
		        for(IntRegister f :IntRegisterfile) {
		        	if(f.name.equals(instruction.source1)) {
		        		r0=f;;
		        	}
		        }
		        System.out.print("value"+r0.value);
			if(r0.value!=0) {
				
				Vector<instruction> temp=new Vector<instruction>();
				int i=instruction.immediate;
				int j=0;
			 while(true) {
				 temp.add(instructions.get(j));
				 if(temp.lastElement().equals(instruction)) {
					 break;
				 }
				 
				 j++;
			 }
			 j++;
			for(;i<j;i++) {
			if(instructions.get(i).source2!=null) {
				instruction temp1=new instruction(instructions.get(i).type,instructions.get(i).destination,instructions.get(i).source1,instructions.get(i).source2, instructions.get(i).exectime);
				
				temp.add(temp1);
			}
			else {
		instruction temp1=new instruction(instructions.get(i).type,instructions.get(i).destination,instructions.get(i).source1,instructions.get(i).immediate, instructions.get(i).exectime);
				
				temp.add(temp1);
			}
				
				
				
			}
			for(int k=j;k<instructions.size();k++) {
			for(stations s: Addstations) {
				if(s.busy&&s.instruction.equals(instructions.get(k))) {
					s.busy=false;
					s.destination=null;
					s.instruction=null;
					s.Qj=null;
					s.Qk=null;
					
				}
			}
				
			for(stations s: Mulstations) {
				if(s.busy&&s.instruction.equals(instructions.get(k))) {
					s.busy=false;
					s.destination=null;
					s.instruction=null;
					s.Qj=null;
					s.Qk=null;
					
				}
			}
				

			
			for(stations s: Loadstations) {
				if(s.busy&&s.instruction.equals(instructions.get(k))) {
					s.busy=false;
					s.destination=null;
					s.instruction=null;
					s.Qj=null;
					s.Qk=null;
					
				}
			}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				if(instructions.get(k).source2!=null) {
					instruction temp1=new instruction(instructions.get(k).type,instructions.get(k).destination,instructions.get(k).source1,instructions.get(k).source2, instructions.get(k).exectime);
				temp.add(temp1);
				}
				else {
					instruction temp1=new instruction(instructions.get(k).type,instructions.get(k).destination,instructions.get(k).source1,instructions.get(k).immediate, instructions.get(k).exectime);
						temp.add(temp1);
				}
			}
			instructions=temp;
				
			
			
			
			}
		}
		String stationname ="";
        FloatRegister f0 = null;
        for(FloatRegister f :FloatRegisterfile) {
        	if(f.name.equals(instruction.destination)) {
        		f0=f;
        	}
        }
        FloatRegister f1 = null;
        for(FloatRegister f :FloatRegisterfile) {
        	if(f.name.equals(instruction.source1)) {
        		f1=f;
        	}
        }
        FloatRegister f2 = null;
        for(FloatRegister f :FloatRegisterfile) {
        	if(f.name.equals(instruction.source2)) {
        		f2=f;
        	}
        }
       IntRegister r0 = null;
        for(IntRegister f :IntRegisterfile) {
        	if(f.name.equals(instruction.destination)) {
        		r0=f;;
        	}
        }
        IntRegister r1 = null;
        for(IntRegister f :IntRegisterfile) {
        	if(f.name.equals(instruction.source1)) {
        		r1=f;;
        	}
        }IntRegister r2 = null;
        for(IntRegister f :IntRegisterfile) {
        	if(f.name.equals(instruction.source2)) {
        		r2=f;;
        	}
        }
        int imme=instruction.immediate;
       
       int end =instruction.exec[1];
	   if(currentcycle >= end+1){
		 if(instruction.type.equals(optype.subu) || instruction.type.equals(optype.addu)||instruction.type.equals(optype.subs) || instruction.type.equals(optype.adds) || instruction.type.equals(optype.addi) || instruction.type.equals(optype.subi) ){

			
			 for(stations i :Addstations){
				if(instruction.equals(i.instruction)){
				i.busy=false;
				int l=Addstations.indexOf(i)+1;
				stationname="A"+l;

				}
			}
			
			
			
			
			
			
		}
			if(instruction.type.equals(optype.muls) || instruction.type.equals(optype.divs) ){
				for(stations i :Mulstations){
					if(instruction.equals(i.instruction)){
					i.busy=false;

					int l=Mulstations.indexOf(i)+1;
					stationname="M"+l;
					
					}
				}
				
		 }
		 if(instruction.type.equals(optype.lw) || instruction.type.equals(optype.sw)  ){
			for(stations i :Loadstations){
				if(instruction.equals(i.instruction)){
				i.busy=false;
				
				int l=Loadstations.indexOf(i)+1;
				stationname="L"+l;
				
				}
			}}
			
		  

	
	   }	
	   System.out.print(stationname);
	   if(end<this.currentcycle) {
	   instruction.writeres=currentcycle;
	   switch (instruction.type) {
       case adds :
           f0.value=f1.value+f2.value;
           f0.qj=null;
           break;
       case subs  :

           f0.value=f1.value-f2.value;
           f0.qj=null;
           break;
       case addi  :
           r0.value=r1.value+imme;
           r0.qj=null;
           break;
       case subi  :

           r0.value=r1.value-imme;
           r0.qj=null;
           break;
       case addu :
           r0.value=r1.value+r2.value;
           r0.qj=null;
           break;
       case subu  :

           r0.value=r1.value-r2.value;
           r0.qj=null;
           break;
       
       case muls:
    	   f0.value=f1.value*f2.value;
    	   f0.qj=null;
           break;
       case divs:

    	   f0.value=f1.value/f2.value;
    	   f0.qj=null;
           break;
       case lw:
           if(f0==null) {

        	   r0.value=(int) Cache[imme];
        	  r0.qj=null;
           }else {
        	   f0.value=Cache[imme];
        	   f0.qj=null;
           }
           break;
       case sw:
    	   if(f0==null) {
           Cache[imme]=r0.value;
           }else {
        	Cache[imme]=f0.value;
           }
           break;
      
       // Other operation cases
       default:
           // Handle unsupported operations or errors
           break;
   }
	   System.out.print(stationname);
     for(stations s: Addstations) {
    	   if(s.Qj!=null &&s.Qj.equals(stationname)){
    		  
    		   s.Qj=null;
    	   }
    	   if(s.Qk!=null &&s.Qk.equals(stationname)){
    		   s.Qk=null;
    	   }
       }
       for(stations s: Mulstations) {
    	   if(s.Qj!=null &&s.Qj.equals(stationname)){
    		   s.Qj=null;
    	   }
    	   if(s.Qk!=null &&s.Qk.equals(stationname)){
    		   s.Qk=null;
    	   }
       }
       for(stations s: Loadstations) {
    	   if(s.Qj!=null &&s.Qj.equals(stationname)){
    		   s.Qj=null;
    	   }
    	   if(s.Qk!=null &&s.Qk.equals(stationname)){
    		   s.Qk=null;
    	   }
       }
		
	   }
	}

	private void issueaddf(instruction instruction) {
		FloatRegister dest=null;
		for(FloatRegister R :FloatRegisterfile) {
			if(R.name.equals(instruction.destination)) {
				dest=R;
				break;
			}
		}
		FloatRegister r2=null;
		for(FloatRegister R :FloatRegisterfile) {
			if(R.name.equals(instruction.source1)) {
				r2=R;
				break;
			}
		}
		FloatRegister r3=null;
		for(FloatRegister R :FloatRegisterfile) {
			if(R.name.equals(instruction.source2)) {
				r3=R;
				break;
			}
		}
		for(stations s :Addstations) {
			if(!s.busy) {
				System.out.print("station empty");
				s.busy=true;
				s.instruction=instruction;
				instruction.issue=currentcycle;
				s.Qj=r2.qj;
				if(r3!=null) {
				s.Qk=r3.qj;
				}else {
					s.Qk=null;
				}
				if(s.Qj==null) {
					s.vj=r2.value;
				}
				if(s.Qk==null&&r3!=null) {
					s.vk=r3.value;
				}
				int ziko=Addstations.indexOf(s)+1;
				if(dest!=null) {
				dest.qj="A"+ziko;
				}
				return;
			}
		}
		
	}

	private void issueadd(instruction instruction) {
		IntRegister dest=null;
		for(IntRegister R :IntRegisterfile) {
			if(R.name.equals(instruction.destination)) {
				dest=R;
				break;
			}
		}
		IntRegister r2=null;
		for(IntRegister R :IntRegisterfile) {
			if(R.name.equals(instruction.source1)) {
				r2=R;
				break;
			}
		}
		IntRegister r3=null;
		for(IntRegister R :IntRegisterfile) {
			if(R.name.equals(instruction.source2)) {
				r3=R;
				break;
			}
		}
		for(stations s :Addstations) {
			if(!s.busy) {
				System.out.print("station empty");
				s.busy=true;
				s.instruction=instruction;
				instruction.issue=currentcycle;
				s.Qj=r2.qj;
				if(r3!=null) {
				s.Qk=r3.qj;
				}else {
					s.Qk=null;
				}
				if(s.Qj==null) {
					s.vj=r2.value;
				}
				if(s.Qk==null&&r3!=null) {
					s.vk=r3.value;
				}
				int ziko=Addstations.indexOf(s)+1;
				if(dest!=null) {
				dest.qj="A"+ziko;
				}
				return;
			}
		}
		
	}


	
	
	private void execute(instruction instruction) {
	    optype Type = instruction.type;
	  
	    if(instruction.issue<currentcycle) {
	    
	    switch (Type) {
	        case addi  :
	            executeAdd(instruction);
	            break;
	        case subi  :
	            executeAdd(instruction);
	            break;
	        case addu  :
	            executeAdd(instruction);
	            break;
	        case subu  :
	            executeAdd(instruction);
	            break;
	        case muls:
	            executeMultiply(instruction);
	            break;
	        case divs:
	            executeMultiply(instruction);
	            break;
	        case lw:
	            executeLoad(instruction);
	            break;
	        case sw:
	            executeStore(instruction);
	            break;
	        case bnez:
	            executeAdd(instruction);
	            break;
	        // Other operation cases
	        default:
	            // Handle unsupported operations or errors
	            break;
	    }
	    }
	}

	private void executeStore(instruction instruction) {
		   IntRegister r2 = null;
		    for( IntRegister r :IntRegisterfile) {
		    	if(r.name==instruction.destination) {
		    		r2=r;
		    	}
		    }
		    
		    int issueCycle = instruction.issue;
		    int[] executeCycles = instruction.exec;

		    stations loadstation = null ; 
		    for(stations s :Loadstations) {
		    	if(!s.busy) {
		    		loadstation=s;
		    		break;
		    	}
		    }
		   // MultiplyFunctionalUnit mulUnit = getAvailableMultiplyUnit();

		   
		        if (loadstation!=null) {
		      if(r2.qj=="") {
		       loadstation.instruction=instruction;
		      instruction.exec[0]=currentcycle;
		      instruction.exec[1]=currentcycle+instruction.exectime;
		
		      }
		
		
		         } else {
		            // Handle station or unit not available
		            // Possibly stall or implement a mechanism to handle this situation
		        }

		
	}


	private void executeAdd(instruction instruction) {
	   System.out.print(instruction.type);
	    int issueCycle = instruction.issue;
	    int[] executeCycles = instruction.exec;

	    stations addstation = null ; 
	    for(stations s :Addstations) {
	    	if(s.instruction.equals(instruction)) {
	    		addstation=s;
	    		break;
	    	}
	    }
	   // MultiplyFunctionalUnit mulUnit = getAvailableMultiplyUnit();
            
	        if (addstation!=null) {

		            if (addstation.Qj==null&&addstation.Qk==null) {
		                addstation.busy=true;
		             

		                executeCycles[0] = currentcycle;
		                executeCycles[1] = currentcycle +instruction.exectime;


		                instruction.exec=executeCycles;
		               
		            }
   
	         
	
}
	
	
	} 
	


	private void executeMultiply(instruction instruction) {
	  String r1 = instruction.destination;
	  FloatRegister r2 = null;
	    for( FloatRegister r :FloatRegisterfile) {
	    	if(r.name==instruction.source1) {
	    		r2=r;
	    	}
	    }
	    FloatRegister r3=null;
	    for( FloatRegister r :FloatRegisterfile) {
	    	if(r.name==instruction.source1) {
	    		r3=r;
	    	}
	    }
	    int issueCycle = instruction.issue;
	    int[] executeCycles = instruction.exec;

	    stations mulStation = null ; 
	    for(stations s :Mulstations) {
	    	if(s.instruction!=null&&s.instruction.equals(instruction)) {
	    		mulStation=s;
	    		break;
	    	}
	    }
	   // MultiplyFunctionalUnit mulUnit = getAvailableMultiplyUnit();

	   
	        if (mulStation!=null) {
	            boolean readyToExecute = true;

	            if (mulStation.Qj==null&&mulStation.Qk==null) {
	                mulStation.busy=true;
	             

	                executeCycles[0] = currentcycle;
	                executeCycles[1] = currentcycle +instruction.exectime;


	                instruction.exec=executeCycles;
	               
	            }
	        } else {
	            // Handle resource unavailability scenarios
	            // Implement mechanisms to handle resource conflicts or stalls
	        }
	   
	}


	private void executeLoad(instruction instruction) {
		  
	    IntRegister r2 = null;
	    for( IntRegister r :IntRegisterfile) {
	    	if(r.name==instruction.destination) {
	    		r2=r;
	    	}
	    }
	    
	    int issueCycle = instruction.issue;
	    int[] executeCycles = instruction.exec;

	    stations loadstation = null ; 
	    for(stations s :Loadstations) {
	    	if(s.instruction.equals(instruction)) {
	    		loadstation=s;
	    		break;
	    	}
	    }
	   // MultiplyFunctionalUnit mulUnit = getAvailableMultiplyUnit();

	   
	        if (loadstation!=null) {
	       loadstation.busy=true;
	       loadstation.instruction=instruction;
	      instruction.exec[0]=currentcycle;
	      instruction.exec[1]=currentcycle+instruction.exectime;
	
	
	
	
	         } else {
	            // Handle station or unit not available
	            // Possibly stall or implement a mechanism to handle this situation
	        }
	}


	/*private void executeBNEZ(instruction instruction) {
	    int r2 = instruction.getOperand2();
	    int issueCycle = instruction.getIssueCycle();
	    int[] executeCycles = instruction.getExecute();

	    BNEZReservationStation bnezStation = getAvailableBNEZStation();
	    BNEZFunctionalUnit bnezUnit = getAvailableBNEZUnit();

	    if (bnezStation != null && bnezUnit != null) {
	        if (!bnezStation.isBusy() && bnezUnit.isFree()) {
	            boolean readyToExecute = true;

	            if (isRegisterAvailable(r2)) {
	                int value = getValueFromRegister(r2);
	                bnezStation.setResult(value == 0 ? 0 : 1); // Set result to 0 or 1 based on r2 value
	                bnezStation.setQj(0);
	            } else {
	                bnezStation.setQj(getWaitingStationForRegister(r2));
	                readyToExecute = false;
	            }

	            if (readyToExecute) {
	                bnezStation.setBusy(true);

	                int latencyForBNEZ = getLatencyForBNEZ();
	                executeCycles[0] = issueCycle;
	                executeCycles[1] = issueCycle + latencyForBNEZ;

	                bnezUnit.setBusy(true);
	                bnezUnit.setRemainingCycles(latencyForBNEZ);

	                if (bnezStation.getResult() == 1) {
	                    int targetAddress = getValueFromRegister(r2); // Assuming r2 holds the target address
	                    setProgramCounter(targetAddress); // Set PC to the value in r2
	                }

	                instruction.setExecute(executeCycles);
	            }
	        } else {
	            // Handle resource unavailability scenarios
	            // Implement mechanisms to handle resource conflicts or stalls
	        }
	    } else {
	        // Handle no available station or unit
	        // Implement mechanisms to handle resource unavailability scenarios
	    }
	}
	
	
	*/
	
	
	
	
	
	
	

}
