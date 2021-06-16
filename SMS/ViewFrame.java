import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;

class ViewFrame extends JFrame 
{
Container c;
TextArea taData;
JButton btnBack;

public static String ViewStudent()
{
	String data = "";
	Configuration cfg = new Configuration();
	cfg.configure("hibernate.cfg.xml");

	SessionFactory sf = cfg.buildSessionFactory();
	Session s = null;
	Transaction t = null;

	try {
		s = sf.openSession();
		System.out.println("open");
		java.util.List<Student> stu = new ArrayList<>();
		stu = s.createQuery("from Student").list();
		for(Student i: stu) {
		data =data + i +"\n";
		}
	}
	catch(Exception e ) {
		System.out.println("issue " + e);
	}
	finally {
		s.close();
		System.out.println("close");
	}
	return data;
}

ViewFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());
c.setBackground(Color.YELLOW);

taData = new TextArea(10, 30);
btnBack = new JButton("Back");

Font f = new Font("Times New Roman",Font.BOLD,20);
taData.setFont(f);
Font g = new Font("Times New Roman",Font.BOLD,30);
btnBack.setFont(g);

c.add(taData);
c.add(btnBack);

ActionListener a1 = (ae) -> {MainFrame a= new MainFrame();dispose();};
btnBack.addActionListener(a1);

// database coding in taData

taData.setText(ViewStudent());

setTitle("View Student");
setSize(500,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}


