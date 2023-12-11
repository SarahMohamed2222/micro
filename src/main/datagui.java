package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;


public class datagui extends JFrame {
	final private Font mainFont = new Font("Helvetica",Font.BOLD,18);
	JTextField addsExecTime, subsExecTime, mulsExecTime, divsExecTime, ADDIExecTime, SUBIExecTime, lwExecTime,swExecTime,T1, T2, T3, T4, Name, Value;
    DefaultTableModel Registers;
	JTable dataTable;

    public void initialise() {
		/********* Form **********/
		JLabel lbaddsExecTime = new JLabel("addsExecTime");
		lbaddsExecTime.setFont(mainFont);
		addsExecTime = new JTextField();
		addsExecTime.setFont(mainFont);

		JLabel lbsubsExecTime = new JLabel("subsExecTime");
		lbsubsExecTime.setFont(mainFont);
		subsExecTime = new JTextField();
		subsExecTime.setFont(mainFont);

		JLabel lbmulsExecTime = new JLabel("mulsExecTime");
		lbmulsExecTime.setFont(mainFont);
		mulsExecTime = new JTextField();
		mulsExecTime.setFont(mainFont);

		JLabel lbdivsExecTime = new JLabel("divsExecTime");
		lbdivsExecTime.setFont(mainFont);
		divsExecTime = new JTextField();
		divsExecTime.setFont(mainFont);

		JLabel lbADDIExecTime = new JLabel("ADDIExecTime");
		lbADDIExecTime.setFont(mainFont);
		ADDIExecTime = new JTextField();
		ADDIExecTime.setFont(mainFont);

		JLabel lbSUBIExecTime = new JLabel("SUBIExecTime");
		lbSUBIExecTime.setFont(mainFont);
		SUBIExecTime = new JTextField();
		SUBIExecTime.setFont(mainFont);

		JLabel lblwExecTime = new JLabel("lwExecTime");
		lblwExecTime.setFont(mainFont);
		lwExecTime = new JTextField();
		lwExecTime.setFont(mainFont);

		JLabel lbswExecTime = new JLabel("swExecTime");
		lbswExecTime.setFont(mainFont);
		swExecTime = new JTextField();
		swExecTime.setFont(mainFont);

		T1 = new JTextField();
		T1.setFont(mainFont);

		T2 = new JTextField();
		T2.setFont(mainFont);

		T3 = new JTextField();
		T3.setFont(mainFont);

		T4 = new JTextField();
		T4.setFont(mainFont);
        
        JPanel formPanel =  new JPanel();
		formPanel.setLayout(new GridLayout(1,1,5,5));
		formPanel.add(lbaddsExecTime);
		formPanel.add(addsExecTime);
		formPanel.add(lbmulsExecTime);
		formPanel.add(mulsExecTime);
		formPanel.add(lblwExecTime);
		formPanel.add(lwExecTime);
		formPanel.add(T1);
		formPanel.add(T2);
		formPanel.add(T3);

        /******* Registers *********/
		 Registers = new DefaultTableModel();
		Registers.addColumn("Name");
		Registers.addColumn("Value");
        for(int i=0;i<32;i++) {
		Registers.addRow(new Object[]{"R"+i,"0"});
        }
        for(int i=0;i<32;i++) {
    		Registers.addRow(new Object[]{"F"+i,"0"});
            }

		dataTable = new JTable(Registers);
             /******** Buttons **********/

	    JButton Add = new JButton("Add");
		Add.setFont(mainFont);
		

		JButton Ok = new JButton("Ok");
		Ok.setFont(mainFont);
		Ok.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				int add,mul,load;
			try {
			add=Integer.parseInt( addsExecTime.getText());
			mul=Integer.parseInt( mulsExecTime.getText());
			load=Integer.parseInt( lwExecTime.getText());
			
		   Vector <Double> reg=new Vector<Double>();
		   for(int i=0;i<64;i++) {
			   System.out.print(Registers.getValueAt(i, 1));
			   reg.add(Double.parseDouble((String) Registers.getValueAt(i, 1)));
		   }
		   System.out.print(T3.getText());
		   new Igui(reg,add,load,mul,Integer.parseInt(T1.getText()),
				   Integer.parseInt(T2.getText())
				   ,Integer.parseInt(T3.getText()));
		  setVisible(false);
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
			}
			catch(Error err) {
				System.out.print(err);
				
			}
			finally {
				
			}
			}	
		});

		JButton clear = new JButton("Clear");
		clear.setFont(mainFont);
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addsExecTime.setText("");
				subsExecTime.setText("");
				mulsExecTime.setText("");
				divsExecTime.setText("");
				ADDIExecTime.setText("");
				SUBIExecTime.setText("");
				lwExecTime.setText("");
				swExecTime.setText("");
			}
		});

		

        JPanel centerPanel = new JPanel(new BorderLayout());
		


		JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(new JLabel("Value:"));
		
		
		

		JPanel ButtonsPanel = new JPanel();
		ButtonsPanel.setLayout(new GridLayout(1,3,5,5));
		ButtonsPanel.add(Ok);
		ButtonsPanel.add(clear);
		ButtonsPanel.add(Add);
		centerPanel.add(ButtonsPanel,BorderLayout.SOUTH);

        
		JLabel displayLabel = new JLabel();
		displayLabel.setFont(mainFont);
		centerPanel.add(displayLabel, BorderLayout.NORTH);



        JPanel mainPanel = new JPanel(); 
        mainPanel.setLayout(new BorderLayout());
		mainPanel.add(formPanel,BorderLayout.NORTH);
		mainPanel.add(new JScrollPane(dataTable), BorderLayout.CENTER);
		mainPanel.add(centerPanel,BorderLayout.SOUTH);
		
		add(mainPanel);

        setTitle("Welcome");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public static void main(String [] args){
		datagui myFrame = new datagui();
		myFrame.initialise();	
	}
}