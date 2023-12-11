package main;

import javax.swing.*;

import java.awt.Component;
import java.awt.event.*;
import java.util.*;

public class Igui extends JFrame {
	private void printTextBoxData() {
	    Component[] dropdownAndTextBoxPanels = dropdownsAndTextBoxesPanel.getComponents();

	    for (Component component : dropdownAndTextBoxPanels) {
	    	
	        if (component instanceof JPanel) {
	        	
	            JPanel panel = (JPanel) component;
	            for (Component comp : panel.getComponents()) {
	            	
	                if( (comp instanceof JComboBox)) {
	                	JComboBox box =(JComboBox) comp;
	                	optype type=optype.valueOf(box.getSelectedItem().toString());
	                	JPanel p=(JPanel) panel.getComponent(1);
	                	switch (type) {
	                	case addi:
	                		 
	                	  ins.add( new instruction(type,((JComboBox)p.getComponent(0)).getSelectedItem().toString()
	                				,((JComboBox)p.getComponent(1)).getSelectedItem().toString()
	                				,Integer.parseInt(((JTextField)p.getComponent(2)).getText())
	                				,1));
	                		break;
	                	case subi:
	                		 
		                	  ins.add( new instruction(type,((JComboBox)p.getComponent(0)).getSelectedItem().toString()
		                				,((JComboBox)p.getComponent(1)).getSelectedItem().toString()
		                				,Integer.parseInt(((JTextField)p.getComponent(2)).getText())
		                				,1));
		                		break;
	                	case addu:
	                		 
		                	  ins.add( new instruction(type,((JComboBox)p.getComponent(0)).getSelectedItem().toString()
		                				,((JComboBox)p.getComponent(1)).getSelectedItem().toString()
		                				,((JComboBox)p.getComponent(2)).getSelectedItem().toString()
		                				,this.addtime));
		                		break;
	                	case subu:
	                		 
		                	  ins.add( new instruction(type,((JComboBox)p.getComponent(0)).getSelectedItem().toString()
		                				,((JComboBox)p.getComponent(1)).getSelectedItem().toString()
		                				,((JComboBox)p.getComponent(2)).getSelectedItem().toString()
		                				,this.addtime));
		                		break;
	                	case adds:
	                		 
		                	  ins.add( new instruction(type,((JComboBox)p.getComponent(0)).getSelectedItem().toString()
		                				,((JComboBox)p.getComponent(1)).getSelectedItem().toString()
		                				,((JComboBox)p.getComponent(2)).getSelectedItem().toString()
		                				,this.addtime));
		                		break;
	                	case muls:
	                		 
		                	  ins.add( new instruction(type,((JComboBox)p.getComponent(0)).getSelectedItem().toString()
		                				,((JComboBox)p.getComponent(1)).getSelectedItem().toString()
		                				,((JComboBox)p.getComponent(2)).getSelectedItem().toString()
		                				,this.multime));
		                		break;
	                	case divs:
	                		 
		                	  ins.add( new instruction(type,((JComboBox)p.getComponent(0)).getSelectedItem().toString()
		                				,((JComboBox)p.getComponent(1)).getSelectedItem().toString()
		                				,((JComboBox)p.getComponent(2)).getSelectedItem().toString()
		                				,this.multime));
		                		break;
	                	case bnez:
	                		 
		                	  ins.add( new instruction(type,null
		                				,((JComboBox)p.getComponent(0)).getSelectedItem().toString()
		                				,0
		                				,1));
		                		break;
	                	case lw:
	                		 ins.add( new instruction(type,
		                				((JComboBox)p.getComponent(0)).getSelectedItem().toString()
,null
		                				,Integer.parseInt(((JTextField)p.getComponent(1)).getText())
		                				,this.loadtime));
		                		break;
	                	}
	                	
                	}
	            }
	        }
	    }
	    
	   Gui g=new Gui();
	   g.main=new main();
	   g.main.instructions=this.ins;
	   g.main.IntRegisterfile=new Vector<IntRegister>();
	   for(int i=0;i<32;i++) {
    	g.main.IntRegisterfile.add(new IntRegister(this.register.get(i).intValue()));
       }
	   g.main.FloatRegisterfile=new Vector<FloatRegister>();
	   for(int i=32;i<63;i++) {
    	g.main.FloatRegisterfile.add(new FloatRegister(this.register.get(i).floatValue()));
       }
	   g.main.Addstations=new Vector<stations>();
	   for(int i=1;i<=addstations;i++) {
		   g.main.Addstations.add(new stations());
	      }
	   g.main.Mulstations=new Vector<stations>();
	   for(int i=1;i<=mulstations;i++) {
		   g.main.Mulstations.add(new stations());
	      }
	   g.main.Loadstations=new Vector<stations>();
	   for(int i=1;i<=this.lwstations;i++) {
		   g.main.Loadstations.add(new stations());
	      }
        g.main.Cache=new float[100];
	    g.initialise();
	}
	private Vector <instruction> ins;

    private JPanel dropdownsAndTextBoxesPanel;
    private JButton addDropdownButton;
	private Vector<Double> register;
	private int addtime;
	private int loadtime;
	private int multime;
private int mulstations;
private int addstations;
private int lwstations;
    public Igui(Vector<Double> register, int addtime, int loadtime, int multime, int i, int j, int k) {
        this.register = register;
        this.addtime = addtime;
        this.loadtime = loadtime;
        this.multime = multime;
        ins=new Vector<instruction>();
       addstations=i;
       mulstations=j;
       lwstations=k;
       System.out.print(this.lwstations);
        init();
    }

    void init() {
        setTitle("Dynamic Dropdowns");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        dropdownsAndTextBoxesPanel = new JPanel();
        dropdownsAndTextBoxesPanel.setLayout(new BoxLayout(dropdownsAndTextBoxesPanel, BoxLayout.Y_AXIS));
        JButton fin=new JButton("done");
        addDropdownButton = new JButton("+");
        addDropdownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDropdown();
            }
        });
      fin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	printTextBoxData();
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(dropdownsAndTextBoxesPanel)
                        .addComponent(addDropdownButton)).addComponent(fin)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(dropdownsAndTextBoxesPanel)
                .addComponent(addDropdownButton)
                .addComponent(fin)
        );

        pack();
        setVisible(true);
        addDropdown(); // Add initial dropdown
    }

    private void addDropdown() {
        JComboBox<String> newDropdown = createDropdown();
        newDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTextBoxes();
            }
        });

        JPanel dropdownAndTextBoxPanel = new JPanel();
        dropdownAndTextBoxPanel.add(newDropdown);
        dropdownAndTextBoxPanel.add(createTextBoxPanel());

        dropdownsAndTextBoxesPanel.add(dropdownAndTextBoxPanel);
        revalidate();
        pack();
    }

    private JComboBox<String> createDropdown() {
        String[] choices = {optype.addi.toString(),optype.adds.toString()
        		,optype.addu.toString(),optype.bnez.toString()
        		,optype.divs.toString(),optype.muls.toString(),
        		optype.subi.toString(),optype.subu.toString(),
        		optype.sw.toString(),optype.lw.toString()}; // Options for the dropdown
        return new JComboBox<>(choices);
    }

    private JPanel createTextBoxPanel() {
        JPanel textBoxPanel = new JPanel();
        textBoxPanel.setLayout(new BoxLayout(textBoxPanel, BoxLayout.Y_AXIS));
        return textBoxPanel;
    }

    private void updateTextBoxes() {
    	
        Component[] dropdownAndTextBoxPanels = dropdownsAndTextBoxesPanel.getComponents();
       String [] reg= new String[64];
       for(int i=0;i<32;i++) {
    	   reg[i]="R"+i;
       }
       for(int i=32;i<64;i++) {
    	   int l=i-32;
    	   reg[i]="F"+l;
       }
        for (Component component : dropdownAndTextBoxPanels) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                JComboBox<String> dropdown = (JComboBox<String>) panel.getComponent(0);
                JPanel textBoxPanel = (JPanel) panel.getComponent(1);
                textBoxPanel.removeAll();
                String selectedChoice = (String) dropdown.getSelectedItem();
                int numOfTextBoxes = 0;

                if (selectedChoice.equals(optype.bnez.toString())||
                		selectedChoice.equals(optype.sw.toString())
                		||selectedChoice.equals(optype.lw.toString())) {
                	 textBoxPanel.add(new JComboBox(reg));
                	 textBoxPanel.add(new JTextField());
                	 
                } else if (selectedChoice.equals(optype.addi.toString())||selectedChoice.equals(optype.subi.toString())) {
               	 textBoxPanel.add(new JComboBox(reg));
               	 textBoxPanel.add(new JComboBox(reg));
             	System.out.print("here");
            	 textBoxPanel.add(new JTextField());
                } 
                
                
                
                else {
                  	 textBoxPanel.add(new JComboBox(reg));
                  	 textBoxPanel.add(new JComboBox(reg));
                  	 textBoxPanel.add(new JComboBox(reg));
                }

                // Clear previous text boxes
               

                // Add the appropriate number of text boxes to the textBoxPanel
                for (int i = 0; i < numOfTextBoxes; i++) {
                    JTextField textField = new JTextField(10); // Adjust the width as needed
                    textBoxPanel.add(textField);
                }
            }
        }

        revalidate();
        pack();
    }
}
