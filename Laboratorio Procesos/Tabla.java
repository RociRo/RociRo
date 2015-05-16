/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Roci
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
public class Tabla extends JFrame {
	public Tabla(ArrayList<String> InfoServicios) {
		int contador = 0;
                Object[][] datos = {
		{"PC", "255.255.255.255"}};
                JPopupMenu Pmenu;
                JMenuItem menuItem; 
		String[] columnNames = {"Proceso","Ubicación"};
		DefaultTableModel dtm= new DefaultTableModel(null,columnNames);
		final JTable table = new JTable(dtm);
                Iterator<String> nombreIterator = InfoServicios.iterator();
                while(nombreIterator.hasNext()){
                      contador++;
                      String elemento = nombreIterator.next();
                      Object[] newRow={"Proceso"+ Integer.toString(contador) , elemento};
                      dtm.addRow(newRow);
                }
                Pmenu = new JPopupMenu();
                menuItem = new JMenuItem("Finalizar");
                //menuItem.addActionListener(this);
                Pmenu.add(menuItem);
                table.setComponentPopupMenu(Pmenu);
                System.out.print("termina de construir tabla"  );                
                table.setPreferredScrollableViewportSize(new Dimension(250, 100));
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){System.exit(0);}
		});
                //nuevo codigo DSC
                
           menuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
           //System.out.print("metodo");  
           
           }
       });//DSC
}

  
	}
	/*public static void main(String[] args) {
		Tabla frame = new Tabla();
		frame.pack();
		frame.setVisible(true);
	}*/
//}