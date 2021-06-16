import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;

class UpdateFrame extends JFrame
{
Container c;
JLabel lblRno,lblName,lblSM1,lblSM2,lblSM3;
JTextField txtRno,txtName,txtSM1,txtSM2,txtSM3;
JButton btnSave,btnBack;

UpdateFrame()
{
c = getContentPane();
c.setLayout(new GridLayout(0, 2));
c.setBackground(Color.ORANGE);

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
			Student stu = (Student)s.get(Student.class, rno);
			if(stu != null) {
				String name = txtName.getText();
				int sm1 = Integer.parseInt(txtSM1.getText());
				int sm2 = Integer.parseInt(txtSM2.getText());
				int sm3 = Integer.parseInt(txtSM3.getText());
				stu.setName(name);
				stu.setSm1(sm1);
				stu.setSm2(sm2);
				stu.setSm3(sm3);
				s.save(stu);
				t.commit();	
				JOptionPane.showMessageDialog(new JDialog(),"record updated ");
			}
			else {
				JOptionPane.showMessageDialog(new JDialog(),rno + "does not exists ");
			}
		}
		catch(Exception e ) {
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

setTitle("Update Student");
setSize(500,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
