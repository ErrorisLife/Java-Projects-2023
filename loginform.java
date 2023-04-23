
import java.sql.*;
import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window.Type;


public class loginform {

	private JFrame login;
	private JTextField userName;
	private JPasswordField password;
	private JLabel invalidLabel, picturelabel;
	private JRadioButton showPass;
	static Connection con;
	static ResultSet rs;
	static PreparedStatement pst;
	private int i=0;
	 	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginform window = new loginform();
					window.login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginform() {
		initialize();
		myConnection();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	
	//Establish Connection with MYSQL
	static void myConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/programenrollmentdb?useSSL=false", "root", "admin");			
		}catch(Exception e) {
			
		}
	}
	
	//login method	
	private void login() {
	
	
		try {
			String pass = String.valueOf(password.getPassword());
			pst=con.prepareStatement("SELECT * FROM admin where username=? AND password=?");
			pst.setString(1, userName.getText());
			pst.setString(2, pass);
			rs=pst.executeQuery();
			
			if(i !=3) {
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "Welcome Admin");						    
					
				} else {			
					showPrompt();							
					i++;
				} 
			 } else {
				JOptionPane.showMessageDialog(null, "Invalid logins. Please wait for 60 seconds. ");			 	
				 System.exit(i);
			 }
				 
			
		} catch (SQLException e) {		
			}
		
	}
	
	private void initialize() {
		login = new JFrame();
		login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Image admin =new ImageIcon(this.getClass().getResource("/admin.png")).getImage();
		login.setIconImage(admin);
		login.setResizable(false);
		login.setTitle("Login");
		login.setBounds(100, 100, 873, 349);
		login.setUndecorated(true);
		login.setLocationRelativeTo(null);
		
		JPanel mainpanel = new JPanel();
		mainpanel.setBackground(new Color(0, 255, 51));
		login.getContentPane().add(mainpanel, BorderLayout.CENTER);
		mainpanel.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(313, 40, 527, 229);
		mainpanel.add(panel);
		panel.setLayout(null);
		
		userName = new JTextField();
		userName.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				clearPrompt();
			}
		});
		userName.setHorizontalAlignment(SwingConstants.LEFT);
		userName.setForeground(Color.BLACK);
		userName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userName.setBackground(new Color(204, 255, 204));
		userName.setBounds(138, 45, 364, 46);
		panel.add(userName);
		
		password = new JPasswordField();
		password.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				clearPrompt();
			}
		});
		password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		password.setBackground(new Color(204, 255, 204));
		password.setBounds(138, 143, 364, 46);
		panel.add(password);
		
		
		JLabel label1 = new JLabel("Username");
		label1.setForeground(Color.BLACK);
		label1.setBackground(Color.BLACK);
		label1.setFont(new Font("Lucida Sans", Font.PLAIN, 25));
		label1.setBounds(10, 11, 173, 31);
		panel.add(label1);
		
		JLabel label2 = new JLabel("Password");
		label2.setForeground(Color.BLACK);
		label2.setFont(new Font("Lucida Sans", Font.PLAIN, 25));
		label2.setBackground(Color.BLACK);
		label2.setBounds(10, 113, 173, 31);
		panel.add(label2);
		
		showPass = new JRadioButton("Show Password");
		showPass.setSelected(true);
		showPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showPass();
			}
		});
		showPass.setForeground(new Color(0, 0, 0));
		showPass.setFont(new Font("Tahoma", Font.ITALIC, 15));
		showPass.setBackground(new Color(204, 255, 204));
		showPass.setBounds(148, 199, 252, 23);
		panel.add(showPass);
		
		invalidLabel = new JLabel("");
		invalidLabel.setBackground(new Color(102, 255, 153));
		invalidLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		invalidLabel.setForeground(Color.BLACK);
		invalidLabel.setLabelFor(login);
		invalidLabel.setBounds(470, -2, 292, 36);
		mainpanel.add(invalidLabel);
		
		JButton logIn = new JButton("Log In");
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		logIn.setForeground(Color.BLACK);
		logIn.setBackground(new Color(51, 255, 51));
		logIn.setFont(new Font("Tahoma", Font.BOLD, 20));
		logIn.setBounds(313, 290, 158, 42);
		mainpanel.add(logIn);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		clear.setForeground(Color.BLACK);
		clear.setBackground(new Color(51, 255, 51));
		clear.setFont(new Font("Tahoma", Font.BOLD, 20));
		clear.setBounds(501, 290, 158, 42);
		mainpanel.add(clear);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		cancel.setForeground(Color.BLACK);
		cancel.setBackground(new Color(51, 255, 51));
		cancel.setFont(new Font("Tahoma", Font.BOLD, 20));
		cancel.setBounds(683, 290, 158, 42);
		mainpanel.add(cancel);
		
		picturelabel = new JLabel("");
		picturelabel.setBounds(21, 40, 270, 265);
		ImageIcon pic =new ImageIcon(this.getClass().getResource("/member.png"));
		Image scaled = pic.getImage().getScaledInstance(270, 265, Image.SCALE_SMOOTH);
		pic= new ImageIcon(scaled);
		picturelabel.setIcon(pic);
	
		
		mainpanel.add(picturelabel);
		
		

	}
	
	//when user press the textfield  this method will be executed and "Invalid Username or Password" will be clear from displaying.
	private void clearPrompt() {
		invalidLabel.setText("");
	}
	
	//when user input is invalid it will prompt "Invalid Username or Password" and this method will execute.
	private void showPrompt() {
		invalidLabel.setText("Invalid Username or Password.");
	}
	
	//if user click the "Show Password" this method will be executed.
	private void showPass() {
		if(showPass.isSelected()) {
			password.setEchoChar((char)0);
		} else {
			password.setEchoChar('●');
		}		
	}
	
	//this method will be executed when user press the Clear Button.
	private void clear() {
		userName.setText("");
		password.setText("");
	}
	
	//this method will run if user press the Cancel Button.
	private void cancel() {
		if(JOptionPane.showConfirmDialog(login, "Are you sure?", "System", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
			System.exit(0);
		}
	}
}
