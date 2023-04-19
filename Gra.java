//Paweł Ganobis
import javax.swing.*;

import java.awt.event.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gra implements ActionListener {

    JFrame gra = new JFrame("Kolko i Krzyzk");
    JPanel plansza = new JPanel();
    JMenu menu = new JMenu("Menu"), nowa_gra_menu = new JMenu("Nowa Gra"), zaladuj_plik = new JMenu("Zaladuj plik");
    JMenuItem nowa_gra, zakoncz_gre, o_grze, zaladuj_X, zaladuj_Y, zaczyna_komp;
    JMenuBar mb = new JMenuBar();
    static boolean zaczyna_gracz = true;
    static boolean zmiana_stanow = false;
    static boolean zaladujX = false;
    static boolean zaladujY = false;

    public Gra() {
        gra.setMinimumSize(new Dimension(400, 400));
        gra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plansza.setLayout(new GridLayout(3, 3));
        nowa_gra = new JMenuItem("Nowa Gra");
        zakoncz_gre = new JMenuItem("Zakoncz gre");
        o_grze = new JMenuItem("O grze");
        zaladuj_X = new JMenuItem("Zaladuj X");
        zaladuj_Y = new JMenuItem("Zaladuj O"); 
        zaczyna_komp = new JMenuItem("Zaczyna komputer");
        nowa_gra.addActionListener(this);
        o_grze.addActionListener(this);
        zakoncz_gre.addActionListener(this);
        zaladuj_X.addActionListener(this);
        zaladuj_Y.addActionListener(this);
        zaczyna_komp.addActionListener(this);
        menu.add(nowa_gra_menu);
        menu.add(zakoncz_gre);
        menu.add(o_grze);
        nowa_gra_menu.add(nowa_gra);
        nowa_gra_menu.add(zaladuj_plik);
        nowa_gra_menu.add(zaczyna_komp);
        zaladuj_plik.add(zaladuj_X);
        zaladuj_plik.add(zaladuj_Y);
        mb.add(menu);
        gra.setJMenuBar(mb);
        gra.add(plansza);
        gra.setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.nowa_gra) {
            Element a[][] = new Element[3][3];
            int wyniki[][] = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    a[i][j] = new Element();
                    a[i][j].gra = this;
                    wyniki[i][j] = 0;
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    a[i][j].addActionListener(new elementyListener(a, wyniki, plansza));
                    this.plansza.add(a[i][j]);
                }
            }
            gra.add(plansza);
            gra.setVisible(true);
        }
        if (e.getSource() == this.o_grze) {
            JOptionPane.showMessageDialog(null, "To gra w kolko i krzyzyk");
        }
        if (e.getSource() == this.zakoncz_gre) {
            System.exit(0);
        }
        if(e.getSource() == this.zaladuj_X){
            zaladujX = true;
        }
        if(e.getSource() == this.zaladuj_Y){
            zaladujY = true;
        }
        if(e.getSource() == this.zaczyna_komp){
            JOptionPane.showMessageDialog(null, "Nacisnij dowolne pole by zaczal komputer");
            zaczyna_gracz = false;
            zmiana_stanow = true;
        }

    }

    public static void main(String[] args) {
        new Gra();

    }
}

class JObrazek extends JComponent {
    public ImageIcon ikona;
    
    public JObrazek() {
        setPreferredSize(new Dimension(100, 100));
        
    }
    public void załaduj(String nazwaPliku) {
        this.ikona = (nazwaPliku == null) ? null : new ImageIcon(nazwaPliku);
        repaint();
    }
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        if (this.ikona != null) {
            g.drawImage(this.ikona.getImage(), 0, 0, width, height, this);
        }
    }
}
class Element extends JButton {
    JObrazek x,y;
    Gra gra;
    int stan = 0;

    public Element() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(Gra.zmiana_stanow){
            if(stan == 1){
                if(Gra.zaladujY){
                    this.y = new JObrazek();
                    this.y.załaduj("o.jpg");
                    g.drawImage(this.y.ikona.getImage(), 0, 0,gra.gra.getWidth()/3,gra.gra.getHeight()/3, this.y);
                    repaint();
                    return;
                }
                else{
                    g.drawOval(0, 0, gra.gra.getWidth()/3,gra.gra.getHeight()/3);
                    repaint();
                    return;
                }
            }
            else if(stan == 2){
                if(Gra.zaladujX){
                    this.x = new JObrazek();
                    this.x.załaduj("x.jpg");
                    g.drawImage(this.x.ikona.getImage(), 0, 0,gra.gra.getWidth()/3,gra.gra.getHeight()/3, this.x);
                    repaint();
                    return;
                      
                  }
                  else{
                      g.drawLine(0, 0,(gra.gra.getWidth()/3), (gra.gra.getHeight()/3));
                      g.drawLine((gra.gra.getWidth()/3), 0, 0, (gra.gra.getHeight()/3));
                      repaint();
                      return;
                  }
            }
        }
        
        if (stan == 1) {
            if(Gra.zaladujX){
              this.x = new JObrazek();
              this.x.załaduj("x.jpg");
              g.drawImage(this.x.ikona.getImage(), 0, 0,gra.gra.getWidth()/3,gra.gra.getHeight()/3, this.x);
              repaint();
              return;
                
            }
            else{
                g.drawLine(0, 0,(gra.gra.getWidth()/3), (gra.gra.getHeight()/3));
                g.drawLine((gra.gra.getWidth()/3), 0, 0, (gra.gra.getHeight()/3));
                repaint();
                return;
            }
            
        } else if (stan == 2) {
            if(Gra.zaladujY){
                this.y = new JObrazek();
                this.y.załaduj("o.jpg");
                g.drawImage(this.y.ikona.getImage(), 0, 0,gra.gra.getWidth()/3,gra.gra.getHeight()/3, this.y);
                repaint();
                return;
            }
            else{
                g.drawOval(0, 0, gra.gra.getWidth()/3,gra.gra.getHeight()/3);
                repaint();
                return;
            }
            
        }   
        
    }

}

class elementyListener implements ActionListener {
    private Element[][] elementy;
    private int[][] ints;
    JPanel plansza;

    public elementyListener(Element[][] elementy, int[][] ints, JPanel plansza) {
        this.elementy = elementy;
        this.ints = ints;
        this.plansza = plansza;
    }

    private boolean sprawdz_pola(int a, boolean t) {
        if (a == 1) {
            
            if (ints[0][0] == 1 && ints[0][1] == 1 && ints[0][2] == 1) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygrales!!");
                return true;
            }

            if (ints[1][0] == 1 && ints[1][1] == 1 && ints[1][2] == 1) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygrales!!");
                return true;
            }

            if (ints[2][0] == 1 && ints[2][1] == 1 && ints[2][2] == 1) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygrales!!");
                return true;
            }
        }
        if (a == 2) {
            
            if (ints[0][0] == 1 && ints[1][0] == 1 && ints[2][0] == 1) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygrales!!");
                return true;
            }

            if (ints[0][1] == 1 && ints[1][1] == 1 && ints[2][1] == 1) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygrales!!");
                return true;
            }

            if (ints[0][2] == 1 && ints[1][2] == 1 && ints[2][2] == 1) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygrales!!");
                return true;
            }

            if (ints[0][0] == 1 && ints[1][1] == 1 && ints[2][2] == 1) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygrales!!");
                return true;
            }

            if (ints[0][2] == 1 && ints[1][1] == 1 && ints[2][0] == 1) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygrales!!");
                return true;
            }

            if (ints[0][2] == 2 && ints[1][1] == 2 && ints[2][0] == 2) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygral komputer!!");
                return true;
            }

            if (ints[0][0] == 2 && ints[0][1] == 2 && ints[0][2] == 2) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygral komputer!!");
                return true;
            }

            if (ints[1][0] == 2 && ints[1][1] == 2 && ints[1][2] == 2) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygral komputer!!");
                return true;
            }

            if (ints[2][0] == 2 && ints[2][1] == 2 && ints[2][2] == 2) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygral komputer!!");
                return true;
            }
            
            if (ints[0][0] == 2 && ints[1][0] == 2 && ints[2][0] == 2) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygral komputer!!");
                return true;
            }

            if (ints[0][1] == 2 && ints[1][1] == 2 && ints[2][1] == 2) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygral komputer!!");
                return true;
            }

            if (ints[0][2] == 2 && ints[1][2] == 2 && ints[2][2] == 2) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygral komputer!!");
                return true;
            }

            if (ints[0][0] == 2 && ints[1][1] == 2 && ints[2][2] == 2) {
                if (t)
                    JOptionPane.showMessageDialog(null, "Wygral komputer!!");
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Element source = (Element) e.getSource();
        int x = 0, y = 0;

        boolean koniec = false;
        if(Gra.zaczyna_gracz){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (elementy[i][j] == source)
                    {
                        x = i;
                        y = j;
                        break;
                    }
                }
            }
            elementy[x][y].stan = 1;
        ints[x][y] = 1;
        koniec = sprawdz_pola(1, true);

        if (koniec) {

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    elementy[i][j].stan = 0;
                    ints[i][j] = 0;
                }
            }
            plansza.repaint();

        }


        }
        Gra.zaczyna_gracz = true;
        
         

        
        Random rn = new Random();
        int rn1 = 0, rn2 = 0;

        int time = 0;
        if (koniec == false) {
            while (true) {
                time++;
                rn1 = rn.nextInt(3);
                rn2 = rn.nextInt(3);
                if (time > 70)
                {
                    koniec = true;
                    break;
                }
                if (elementy[rn1][rn2].stan == 0) {
                    elementy[rn1][rn2].stan = 2;
                    ints[rn1][rn2] = 2;
                    break;
                } else {
                    continue;
                }

            }
        }

        plansza.repaint();
        koniec = sprawdz_pola(2, true);
        boolean end = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ints[i][j] == 0)
                    end = false;
            }
        }
        if (sprawdz_pola(1, false) == false && sprawdz_pola(2, false) == false && end == true) {
            JOptionPane.showMessageDialog(null, "Remis!");
            koniec = true;
        }
        if (koniec)
        {

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    elementy[i][j].stan = 0;
                    ints[i][j] = 0;
                }
            }
            plansza.repaint();
            koniec = false;

        }

    }
}