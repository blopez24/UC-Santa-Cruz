import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calculator extends JFrame
{
	JButton addButton, minusButton, multiplyButton, divideButton;
	JButton f1Button, f2Button;
	JButton exitButton;
	JLabel f1Label, f2Label, resultsLabel, resultsBoxLabel;
	JTextField f1Field, f2Field;
	
	int widthOfPane = 800; int heightOfPane = 950;	int widthOfButton = 200; int heightOfButton = 200;
	
	Calculator()
	{
		this.setSize(widthOfPane, heightOfPane);
		this.getContentPane().setBackground(Color.pink);
		this.setTitle("Calculator");
		this.setLayout(null);
		
		displaysButtons();
		
		DoCalulactions listener = new DoCalulactions(f1Field, f2Field, resultsBoxLabel);
		addButton.addActionListener(listener);
		minusButton.addActionListener(listener);
		multiplyButton.addActionListener(listener);
		divideButton.addActionListener(listener);
		
		f1Button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f1Field.setText(resultsBoxLabel.getText());
			}
		});
		
		f2Button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f2Field.setText(resultsBoxLabel.getText());
			}
		});
		
		
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
		});
		
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void displaysButtons()
	{
				
		addButton = new JButton("+");	minusButton = new JButton("-");	multiplyButton = new JButton("*");	divideButton = new JButton("/");
		this.add(addButton); 			this.add(minusButton); 			this.add(multiplyButton); 			this.add(divideButton);
		addButton.setBounds(0, 500, 2 * widthOfButton, heightOfButton);
		minusButton.setBounds(400, 500, 2 * widthOfButton, heightOfButton);
		multiplyButton.setBounds(0, 700, 2 * widthOfButton, heightOfButton);
		divideButton.setBounds(400, 700, 2 * widthOfButton, heightOfButton);
		
		f1Button = new JButton("Copy to F1");	f2Button = new JButton("Copy to F2");
		this.add(f1Button); 				this.add(f2Button);
		f1Button.setBounds(0, 300, widthOfButton, heightOfButton);
		f2Button.setBounds(200, 300, widthOfButton, heightOfButton);
		
		f1Label = new JLabel("F1", JLabel.CENTER);				f2Label = new JLabel("F2", JLabel.CENTER);
		this.add(f1Label);										this.add(f2Label);
		f1Label.setBounds(0, 0, 200, 100);						f2Label.setBounds(0, 100, 200, 100);
		f1Label.setFont(new Font("Helvetica", Font.BOLD, 40));	f2Label.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		resultsLabel = new JLabel("Output:", JLabel.CENTER);
		this.add(resultsLabel);
		resultsLabel.setBounds(0, 200, 200, 100);
		resultsLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		f1Field = new JTextField("");							f2Field = new JTextField("");
		this.add(f1Field);										this.add(f2Field);
		f1Field.setBounds(200, 0, 630, 100);					f2Field.setBounds(200, 100, 630, 100);
		f1Field.setFont(new Font("Helvetica", Font.BOLD, 20));	f2Field.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		resultsBoxLabel = new JLabel("0", JLabel.CENTER);
		this.add(resultsBoxLabel);
		resultsBoxLabel.setBounds(200, 200, 630, 100);
		resultsBoxLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
		
		exitButton = new JButton("EXIT");
		this.add(exitButton);
		exitButton.setBackground(Color.red);
		exitButton.setBounds(400, 300, 400, 200);
	}
	
	public static void main(String[] args)
	{
		Calculator frame = new Calculator();
	}
}

class DoCalulactions implements ActionListener
{
	private JTextField inputOne = null;
	private JTextField inputTwo = null;
	private JLabel output;
	
	DoCalulactions(JTextField first, JTextField second, JLabel output)
	{
		this.inputOne = first;
		this.inputTwo = second;
		this.output = output;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		double first;
		double second;
		
		if(inputOne.getText().isEmpty() || inputTwo.getText().isEmpty())
			output.setText("Please make sure all fields are filled in!");
		else if(e.getActionCommand().equals("+"))
		{
			first = Double.parseDouble(inputOne.getText());
			second = Double.parseDouble(inputTwo.getText());
			
			if(inputOne.getText().equals("0") && inputTwo.getText().equals("0"))
				output.setText("Please make sure all fields are filled in!");
			else
				output.setText(String.valueOf(first + second));
		}
		else if(e.getActionCommand().equals("-"))
		{
			first = Double.parseDouble(inputOne.getText());
			second = Double.parseDouble(inputTwo.getText());
			
			if(inputOne.getText().equals("0") && inputTwo.getText().equals("0"))
				output.setText("Please make sure all fields are filled in!");
			else
				output.setText(String.valueOf(first - second));
		}
		else if(e.getActionCommand().equals("*"))
		{
			first = Double.parseDouble(inputOne.getText());
			second = Double.parseDouble(inputTwo.getText());
			
			if(inputOne.getText().equals("0") && inputTwo.getText().equals("0"))
				output.setText("Please make sure all fields are filled in!");
			else
				output.setText(String.valueOf(first * second));
		}
		else if(e.getActionCommand().equals("/"))
		{
			first = Double.parseDouble(inputOne.getText());
			second = Double.parseDouble(inputTwo.getText());
			
			if(inputOne.getText().equals("0") && inputTwo.getText().equals("0"))
				output.setText("Please make sure all fields are filled in!");
			else
				output.setText(String.valueOf(first / second));
		}
	}
}