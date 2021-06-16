import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;

class AddFrame extends JFrame
{
Container c;
JLabel lblRno,lblName,lblSM1,lblSM2,lblSM3;
JTextField txtRno,txtName,txtSM1,txtSM2,txtSM3;
JButton btnSave,btnBack;

AddFrame()
{
c = getContentPane();
c.setLayout(null);
c.setBackground(Color.CYAN);

lblRno = new JLabel("Enter Rno:");
txtRno = new JTextField(3);
lblName = new JLabel("Enter Name:");
txtName = new JTextField(15);
lblSM1 = new JLabel("Enter Sub Marks 1:");
txtSM1 = new JTextField(3);
lblSM2 = new JLabel("Enter Sub Marks 2:");
txtSM2 = new JTextField(3);
lblSM3 = new JLabel("Enter Sub Marks 3:");
txtSM3 = new JTextField(3);
btnSave = new JButton("Save");
btnBack = new JButton("Back");

lblRno.setBounds(180,0,200,30);
txtRno.setBounds(50,30,380,30);
lblName.setBounds(170,60,200,30);
txtName.setBounds(50,90,380,30);
lblSM1.setBounds(145,120,200,30);
txtSM1.setBounds(50,150,380,30);
lblSM2.setBounds(145,180,200,30);
txtSM2.setBounds(50,210,380,30);
lblSM3.setBounds(145,240,200,30);
txtSM3.setBounds(50,270,380,30);
btnSave.setBounds(185,302,100,30);
btnBack.setBounds(185,335,100,30);

Font f = new Font("Times New Roman",Font.BOLD,20);
lblRno.setFont(f);
txtRno.setFont(f);
lblName.setFont(f);
txtName.setFont(f);
lblSM1.setFont(f);
txtSM1.setFont(f);
lblSM2.setFont(f);
txtSM2.setFont(f);
lblSM3.setFont(f);
txtSM3.setFont(f);
btnSave.setFont(f);
btnBack.setFont(f);

c.add(lblRno);
c.add(txtRno);
c.add(lblName);
c.add(txtName);
c.add(lblSM1);
c.add(txtSM1);
c.add(lblSM2);
c.add(txtSM2);
c.add(lblSM3);
c.add(txtSM3);
c.add(btnSave);
c.add(btnBack);
btnSave.setAlignmentX(CENTER_ALIGNMENT);
btnBack.setAlignmentX(CENTER_ALIGNMENT);

ActionListener a1 = (ae) -> {MainFrame a = new MainFrame();dispose();};
btnBack.addActionListener(a1);

ActionListener a2 = (ae) -> {
	if (txtRno.getText().isEmpty()){
		txtRno.requestFocus();
		JOptionPane.showMessageDialog(c,"Enter Rno","Message",JOptionPane.INFORMATION_MESSAGE);
	}
	else if (!(txtRno.getText().matches("0|-?[1-9][0-9]*"))){
		txtRno.setText("");
		txtRno.requestFocus();
		JOptionPane.showMessageDialog(c,"Invalid Rno","Error",JOptionPane.ERROR_MESSAGE);
	}
	else if (Integer.parseInt(txtRno.getText())<0) {
		txtRno.setText("");
		txtRno.requestFocus();
		JOptionPane.showMessageDialog(c,"Rno cannot be negative","Error",JOptionPane.ERROR_MESSAGE);
	}
	else if (txtName.getText().isEmpty()){
		txtName.requestFocus();
		JOptionPane.showMessageDialog(c,"Enter Name","Message",JOptionPane.INFORMATION_MESSAGE);
	}
	else if (!(txtName.getText().matches("^[a-zA-Z]*$"))){
		txtName.setText("");
		txtName.requestFocus();
		JOptionPane.showMessageDialog(c,"Invalid Name","Error",JOptionPane.ERROR_MESSAGE);
	}
	else if (txtName.getText().length()<2) {
		txtName.setText("");
		txtName.requestFocus();
		JOptionPane.showMessageDialog(c,"Name must be of atleast 2 alphabets","Error",JOptionPane.ERROR_MESSAGE);
	}
	else if (txtSM1.getText().isEmpty()){
		txtSM1.requestFocus();
		JOptionPane.showMessageDialog(c,"Enter Subject 1 Marks","Message",JOptionPane.INFORMATION_MESSAGE);
	}
	else if (!(txtSM1.getText().matches("0|-?[1-9][0-9]*"))){
		txtSM1.setText("");
		txtSM1.requestFocus();
		JOptionPane.showMessageDialog(c,"Invalid Marks","Error",JOptionPane.ERROR_MESSAGE);
	}
	else if (Integer.parseInt(txtSM1.getText())<0 || Integer.parseInt(txtSM1.getText())>100){
		txtSM1.setText("");
		txtSM1.requestFocus();
		JOptionPane.showMessageDialog(c,"Marks should be in range of 0 to 100","Error",JOptionPane.ERROR_MESSAGE);
	}
	else if (txtSM2.getText().isEmpty()){
		txtSM2.requestFocus();
		JOptionPane.showMessageDialog(c,"Enter Subject 2 Marks","Message",JOptionPane.INFORMATION_MESSAGE);
	}
	else if (!(txtSM2.getText().matches("0|-?[1-9][0-9]*"))){
		txtSM2.setText("");
		txtSM2.requestFocus();
		JOptionPane.showMessageDialog(c,"Invalid Marks","Error",JOptionPane.ERROR_MESSAGE);
	}
	else if (Integer.parseInt(txtSM2.getText())<0 || Integer.parseInt(txtSM2.getText())>100){
		txtSM2.setText("");
		txtSM2.requestFocus();
		JOptionPane.showMessageDialog(c,"Marks should be in range of 0 to 100","Error",JOptionPane.ERROR_MESSAGE);
	}
	else if (txtSM3.getText().isEmpty()){
		txtSM3.requestFocus();
		JOptionPane.showMessageDialog(c,"Enter Subject 3 Marks","Message",JOptionPane.INFORMATION_MESSAGE);
	}
	else if (!(txtSM3.getText().matches("0|-?[1-9][0-9]*"))){
		txtSM3.setText("");
		txtSM3.requestFocus();
		JOptionPane.showMessageDialog(c,"Invalid Marks","Error",JOptionPane.ERROR_MESSAGE);
	}
	else if (Integer.parseInt(txtSM3.getText())<0 || Integer.parseInt(txtSM3.getText())>100){
		txtSM3.setText("");
		txtSM3.requestFocus();
		JOptionPane.showMessageDialog(c,"Marks should be in range of 0 to 100","Error",JOptionPane.ERROR_MESSAGE);
	}
	else {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory sf = cfg.buildSessionFactory();
		Session s = null;
		Transaction t = null;

		try {
			s = sf.openSession();
			System.out.println("open");
			t = s.beginTransaction();
			Console c = System.console();
			int rno = Integer.parseInt(txtRno.getText());
			String name = txtName.getText();
			int sm1 = Integer.parseInt(txtSM1.getText());
			int sm2 = Integer.parseInt(txtSM2.getText());
			int sm3 = Integer.parseInt(txtSM3.getText());
			Student stu = new Student(rno, name, sm1, sm2, sm3);
			s.save(stu);
			t.commit();
			JOptionPane.showMessageDialog(new JDialog(),"record inserted");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JDialog(),"issue"+e);
			System.out.println("issue " + e);
			t.rollback();
		}
		finally {
  			s.close();
			System.out.println("close");
		}
	}		
};
btnSave.addActionListener(a2);


setTitle("Add Student");
setSize(500,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
