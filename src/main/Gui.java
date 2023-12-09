package main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.*;


public class Gui extends JFrame  implements KeyListener{
	
	JTextField addsExecTime, subsExecTime, mulsExecTime, divsExecTime, ADDIExecTime, SUBIExecTime, lwExecTime,swExecTime;
JPanel formPanel;
JPanel intreg;
JPanel stations; 
	main main;
    public void initialise() {
		/* Form */
		JLabel lbaddsExecTime = new JLabel("addsExecTime");
		addsExecTime = new JTextField();

		JLabel LBsubsExecTime = new JLabel("subsExecTime");
		subsExecTime = new JTextField();

		JLabel lbmulsExecTime = new JLabel("mulsExecTime");
		mulsExecTime = new JTextField();

		JLabel lbdivsExecTime = new JLabel("divsExecTime");
		divsExecTime = new JTextField();

		JLabel lbADDIExecTime = new JLabel("ADDIExecTime");
		ADDIExecTime = new JTextField();

		JLabel lbSUBIExecTime = new JLabel("SUBIExecTime");
		SUBIExecTime = new JTextField();

		JLabel lblwExecTime = new JLabel("lwExecTime");
		lwExecTime = new JTextField();

		JLabel lbswExecTime = new JLabel("swExecTime");
		swExecTime = new JTextField("swExecTime");

         formPanel =  new JPanel();
         
		intreg=new JPanel();
		stations=new JPanel();

 
        JPanel mainPanel = new JPanel(); 
        mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color(128,128,255));
		mainPanel.add(formPanel,BorderLayout.NORTH);
		;
		mainPanel.add(intreg,BorderLayout.WEST);

		mainPanel.add(stations,BorderLayout.SOUTH);
		this.addKeyListener(this);
        this.add(mainPanel);
        setTitle("Welcome");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
       
    }

    public static void main(String [] args){
		Gui myFrame = new Gui();
		myFrame.initialise();	
		main main=new main();
		main.instructions=new Vector<instruction>();
		instruction i=new instruction(optype.addi, "R0", "R1", "R2", 3);

		instruction ii=new instruction(optype.muls, "F4", "F1", "F2", 3);

		instruction iii=new instruction(optype.subi, "R31", "R0", "R2", 3);
		main.instructions.add(i);

		main.instructions.add(ii);
		main.instructions.add(iii);
		main.Addstations=new Vector<stations>();
		stations a1=new stations();
		main.Addstations.add(a1);
		stations a2=new stations();
		main.Addstations.add(a2);

		main.Mulstations=new Vector<stations>();
		stations m1=new stations();
		main.Mulstations.add(m1);
        main.IntRegisterfile=new Vector<IntRegister>();
        for(int k=0;k<32;k++) {
        main.IntRegisterfile.add(new IntRegister(1));
        }
        main.FloatRegisterfile=new Vector<FloatRegister>();
        for(int k=0;k<32;k++) {
        main.FloatRegisterfile.add(new FloatRegister(1));
        }
		
		myFrame.main=main;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
	    System.out.println("Key pressed: " + e.getKeyCode()); // Debug statement
	    if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Debug statement

	        // Ensure main object is initialized and not null
	        if (main != null) {
	            main.next();
	            formPanel.removeAll();

	    		formPanel.setLayout(new GridLayout(main.instructions.size(),8,5,5));
	      
	            for(instruction s :main.instructions) {
	        		formPanel.add(new JLabel(s.type.toString()));
	        		formPanel.add(new JLabel(s.issue+""));
	        		formPanel.add(new JLabel(s.exec[0]+""));
	        		formPanel.add(new JLabel(s.exec[1]+""));
	        		formPanel.add(new JLabel(s.writeres+""));
	        		formPanel.add(new JLabel(s.destination+""));
	        		formPanel.add(new JLabel(s.source1+""));
	        		formPanel.add(new JLabel(s.source2+""));
	        		
	        		
	        		


	            }
	            intreg.removeAll();

	    		intreg.setLayout(new GridLayout(main.IntRegisterfile.size(),12,1,1));
	      
	            for(IntRegister s :main.IntRegisterfile) {
	        		intreg.add(new JLabel(s.name));
	        		intreg.add(new JLabel(s.qj));
	        		intreg.add(new JLabel(s.value+""));	        		
	        		
	        		


	            }
	            stations.removeAll();
	            stations.setLayout(new GridLayout(main.Addstations.size(),12,1,1));
	  	      
	            for(stations s :main.Addstations) {
	        		stations.add(new JLabel(s.Qj));
	        		stations.add(new JLabel(s.Qk));
	        		stations.add(new JLabel(s.vj+""));	 

	        		stations.add(new JLabel(s.vk+""));	  
	        		
	        		


	            }
this.setVisible(true);
		           
	        } else {
	            
	        }
	    }
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}