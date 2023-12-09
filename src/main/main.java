package main;

import java.util.Vector;

public class main {
	
	Vector<stations> Addstations;
    Vector<stations> Loadstations;
	Vector<stations> Mulstations;
	Vector<Integer> Cache;
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
		       
		        // Other operation cases
		        default:
		            // Handle unsupported operations or errors
		            break;
		    }

		
	}


	private void issueld(instruction instruction) {
		// TODO Auto-generated method stub
		
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
				s.Qj=r2.qj;
				s.Qk=r3.qj;
			
				if(s.Qj!="") {
					s.vj=r2.value;
				}
				if(s.Qk!="") {
					s.vk=r3.value;
				}
				int ziko=Mulstations.indexOf(s)+1;
				dest.qj="M"+ziko;
				return;
			}
		}
		
	private void writeres(instruction instruction)
	{

       
       int end =instruction.exec[2]
	   if(currentcycle >= end+1){
		 if(optype.equals(subs) || optype.equals(adds) || optype.equals(addi) || optype.equals(subi) ){
			for(stations i :Addstations){
				if(instruction.equals(station.instruction)){
					station.busy=false
				}
			}
		}
			if(optype.equals(mulvs) || optype.equals(divs) || optype.equals(addi) || optype.equals(subi) ){
				for(stations i :Addstations){
					if(instruction.equals(station.instruction)){
						station.busy=false
					}
				}
				
		 }
		 if(optype.equals(lw) || optype.equals(sw)  ){
			for(stations i :Addstations){
				if(instruction.equals(station.instruction)){
					station.busy=false
				}
			}
			
		  

	
	   }
	   int destination=instruction.destination;
	   String source1= instruction.source1;
	   String source2= instruction.source2;
	   int imm= instruction.immediate;
		//instruction will have have the optyp (mul,add,load...) as op r1,r2,r3 as r1,r2,r3 and issue,exectue writeresult attrbutes
		// exectute will be array of two depicting start and end cycles 
		//your job  is to check wether it will write its result now or no based on comparsion with the end time to the current 
		//cycle and update the chache and stations accordingly
        // if lw ana equal to subs adds addi subi haloop on addstations le7ad ma ala2y station el instruction ely gowaha zai el instruction ely m3aky
		
   //if instrcution.equals(station.instruction) 
   //station.busy=false
	//h3ml kda lel multiply w kman lel load station
	//h3ml variable esmo inrtuction.destination w yeb2a gowa loop el kebera
	// String source1= instrucrtion.source1
    // h3ml nafs el haga le source 2 w el immediate			
                                                  

		
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
				s.Qk=r3.qj;
				
				if(s.Qj!="") {
					s.vj=r2.value;
				}
				if(s.Qk!="") {
					s.vk=r3.value;
				}
				int ziko=Addstations.indexOf(s)+1;
				dest.qj="A"+ziko;
				return;
			}
		}
		
	}


	private void writeres(instruction instruction)
	{IntRegister dest=null;
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
		for(IntRegister R :IntRegisterfile) {
			if(R.name.equals(instruction.destination)) {
				dest=R;
				break;
			}
		}
                  
		int stationunm=-1;
		
		if(instruction.exec[1]<currentcycle) {
                    	 
                    	 for(stations s: Addstations) {
                    		 if(s.instruction.equals(instruction)) {
                    			
                    			 instruction.writeres=currentcycle;
                    			 s.busy=false;
                    			 s.Qj=null;
                    			 s.Qk=null;
                    			 dest.qj=null;
                    			 dest.value=r2.value+r3.value;
                    			 stationunm=Addstations.indexOf(s)+1;
                    			break;
                    			
                    		 }
                    	 }
                    	 
                     }
		 for(stations s: Addstations) {
    		if(s.Qj!=null&& s.Qj.contains("A"+stationunm+"")) {
    			s.Qj=null;
    	 }
    		if(s.Qk!=null&&s.Qk.contains("A"+stationunm+"")) {
    			s.Qk=null;
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
	   
	    IntRegister r2 = null;
	    for( IntRegister r :IntRegisterfile) {
	    	if(r.name.equals(instruction.source1)) {
	    		r2=r;
	    	}
	    }
	    IntRegister r3=null;
	    for( IntRegister r :IntRegisterfile) {
	    	if(r.name.equals(instruction.source1)) {
	    		r3=r;
	    	}
	    }
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

		            if (r2.qj==null&&r3.qj==null) {
		                addstation.busy=true;
		             

		                executeCycles[0] = currentcycle;
		                executeCycles[1] = currentcycle +instruction.exectime;


		                instruction.exec=executeCycles;
		               
		            }
   
	         
	
}
	
	
	
	         } else {
	            // Handle station or unit not available
	            // Possibly stall or implement a mechanism to handle this situation
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
	    	if(s.instruction.equals(instruction)) {
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
	    	if(!s.busy) {
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
