import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Listener extends JFrame
{
	JTextField f1TextField;
	JTextField f2TextField;
	JButton reverseButton;
	
	Listener()
	{
		setup();
		textFieldsAndButtonDisplay();
		
		DoReverse listener = new DoReverse(f1TextField, f2TextField);
		reverseButton.addActionListener(listener);
		f1TextField.addKeyListener(listener);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Listener frame = new Listener();
	}
	
	void setup()
	{
		this.setTitle("Listener");
		this.setSize(800, 450);
		this.getContentPane().setBackground(new Color(66, 134, 244));
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void textFieldsAndButtonDisplay()
	{
		f1TextField = new JTextField("");
		f2TextField = new JTextField("");
		reverseButton = new JButton("Reverse");
		
		this.add(f1TextField);
		this.add(f2TextField);
		this.add(reverseButton);
		
		f1TextField.setBounds(20, 20, 760, 100);
		f1TextField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
		f2TextField.setBounds(20, 275, 760, 100);
		f2TextField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
		
		reverseButton.setBounds(300, 150, 200, 100);
		reverseButton.setIcon(new ImageIcon("src/button.png"));
		
		f1TextField.setFont(new Font("Helvetica", Font.BOLD, 25));
		f1TextField.setForeground(new Color(66, 134, 255));
		f2TextField.setFont(new Font("Helvetica", Font.BOLD, 25));
		f2TextField.setForeground(new Color(66, 134, 255));
	}
	
	
}

class DoReverse implements ActionListener, KeyListener
{
	private JTextField input;
	private JTextField output;
	
	DoReverse(JTextField input, JTextField output)
	{
		this.input = input;
		this.output = output;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String orignal = input.getText().trim();
		String reverse = "";
		
		for(int i = orignal.length() - 1; i >= 0; i--)
		{
			reverse  += orignal.charAt(i);
		}
		
		if(e.getActionCommand().equals("Reverse"))
		{
			output.setText(reverse);
		}
		
	}

	public void keyPressed(KeyEvent e) 
	{	
		String orignal = input.getText().trim();
		String reverse = "";
		
		for(int i = orignal.length() - 1; i >= 0; i--)
		{
			reverse  += orignal.charAt(i);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			output.setText(reverse);
	}

	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
}