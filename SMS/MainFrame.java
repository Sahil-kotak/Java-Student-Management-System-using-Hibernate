import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;


class MainFrame extends JFrame
{
Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete,btnCharts;

MainFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 40));
c.setBackground(Color.GREEN);

btnAdd = new JButton("Add");
btnView = new JButton("View");
btnUpdate = new JButton("Update");
btnDelete = new JButton("Delete");
btnCharts = new JButton("Charts");

Font f = new Font("Times new Roman",Font.BOLD,30);
btnAdd.setFont(f);
btnView.setFont(f);
btnDelete.setFont(f);
btnUpdate.setFont(f);
btnCharts.setFont(f);

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);
c.add(btnCharts);

ActionListener a1 = (ae) -> {AddFrame a = new AddFrame();dispose();};
btnAdd.addActionListener(a1);

ActionListener a2 = (ae) -> {ViewFrame a = new ViewFrame();dispose();};
btnView.addActionListener(a2);

ActionListener a3 = (ae) -> {UpdateFrame a = new UpdateFrame();dispose();};
btnUpdate.addActionListener(a3);

ActionListener a4 = (ae) -> {DeleteFrame a = new DeleteFrame();dispose();};
btnDelete.addActionListener(a4);

ActionListener a5 = (ae) -> {ChartsFrame a = new ChartsFrame();dispose();};
btnCharts.addActionListener(a5); 

setTitle("S.M.S.");
setSize(500, 400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}

