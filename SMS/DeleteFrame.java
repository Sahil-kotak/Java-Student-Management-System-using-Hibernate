import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;

class DeleteFrame extends JFrame
{
Container c;
JLabel lblRno;
JTextField txtRno;
JButton btnSave,btnBack;

DeleteFrame()
{
c = getContentPane();
c.setLayout(null);
c.setBackground(Color.BLUE);

lblRno = new JLabel("Enter Rno:");
txtRno = new JTextField(3);
btnSave = new JButton("Save");
btnBack = new JButton("Back");

lblRno.setBounds(130,10,300,50);
txtRno.setBounds(75,70,300,50);
btnSave.setBounds(185,200,100,60);
btnBack.setBounds(185,270,100,60);

Font f = new Font("Times New Roman",Font.BOLD,30);
lblRno.setFont(f);
txtRno.setFont(f);
btnSave.setFont(f);
btnBack.setFont(f);

c.add(lblRno);
c.add(txtRno);
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
				s.delete(stu);
				t.commit();	
				JOptionPane.showMessageDialog(new JDialog(),"record deleted ");
			}
			else {
				JOptionPane.showMessageDialog(new JDialog(),"Rno "+rno + " does not exists ");
			}
		}
		catch(Exception e ) {
			JOptionPane.showMessageDialog(new JDialog(),"Issue "+e);
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

setTitle("Delete Student");
setSize(500,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
