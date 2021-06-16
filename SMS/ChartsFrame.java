import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import java.io.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;

class ChartsFrame extends JFrame 
{
ChartsFrame() {
	DefaultCategoryDataset ds = new DefaultCategoryDataset();	
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
			String name = i.getName();
			int S1 = i.getSm1();
			int S2 = i.getSm2();
			int S3 = i.getSm3();
			ds.addValue(S1,name,"S1");
			ds.addValue(S2,name,"S2");
			ds.addValue(S3,name,"S3");
			/*data =data + i +"\n";
			ds.addValue();*/
		}
	}
	catch(Exception e ) {
		System.out.println("issue " + e);
	}
	finally {
		s.close();
		System.out.println("close");
	}
	

	


	JFreeChart chart = ChartFactory.createBarChart("Student's Perf","Subject","Marks",ds,
	PlotOrientation.VERTICAL,true,true,true);

	ChartPanel panel = new ChartPanel(chart);
	setContentPane(panel);

	setTitle("S.M.S.");
	setSize(500, 400);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
}
}