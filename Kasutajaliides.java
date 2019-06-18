import javax.swing.*;
import java.awt.*;
import javax.swing.JFileChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.*;


public class Kasutajaliides
{
    public Kasutajaliides()
    {
        Teisendused teisendus = new Teisendused();
        String pealdis = "On avatud fail nimega ";

        class Infokast
        {
            Font kirja_stiil = new Font(Font.MONOSPACED, Font.BOLD, 12);

            JTextArea tekstiala;
            private JLabel tekstiala_silt;
            JPanel tekstipaneel;

            Infokast(boolean kas_kirja_murtakse,
                     boolean kas_s6nahaaval,
                     String pealkiri)
            {
                tekstiala = new JTextArea();
                tekstiala.setMargin(new Insets(7, 7, 7, 7));
                tekstiala.setFont(kirja_stiil);

                tekstiala.setLineWrap(kas_kirja_murtakse);
                tekstiala.setWrapStyleWord(kas_s6nahaaval);

                tekstipaneel = new JPanel();
                tekstipaneel.setLayout(new BorderLayout());

                if ( !pealkiri.isEmpty() ) 
                {
                    tekstiala_silt = new JLabel(pealkiri);
                    tekstiala_silt.setHorizontalAlignment(SwingConstants.LEFT);                    
                    tekstipaneel.add(tekstiala_silt, BorderLayout.NORTH);
                }

                tekstipaneel.add(new JScrollPane(tekstiala), BorderLayout.CENTER);
            }
        }

        Infokast ylemine        = new Infokast(true,true,"");
        ylemine.tekstiala.setBackground(new Color(25, 155, 220));
        ylemine.tekstiala.setEditable(false);

        Infokast nukleotiidid   = new Infokast(true,true," Nukleotiidid");
        Infokast aminohapped    = new Infokast(true,false," Aminohapped");
        Infokast valemid        = new Infokast(false,false," Valemid");
        Infokast statistika     = new Infokast(false,false," Statistika");

        JButton faili_avamise_nupp          = new JButton("Ava fail");
        JButton transleerimise_nupp         = new JButton("Transleeri");
        JButton statistika_tekitamise_nupp  = new JButton("Tekita statistika");
        JButton k6ige_puhastamise_nupp      = new JButton("Puhasta kõik");

        JPanel nupuriba = new JPanel();
        nupuriba.setLayout(new FlowLayout(FlowLayout.CENTER));
        nupuriba.add(faili_avamise_nupp);
        nupuriba.add(transleerimise_nupp);
        nupuriba.add(statistika_tekitamise_nupp);
        nupuriba.add(k6ige_puhastamise_nupp);

        ylemine.tekstipaneel.add(nupuriba, BorderLayout.SOUTH);

        JSplitPane r6htjagaja = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                nukleotiidid.tekstipaneel,
                aminohapped.tekstipaneel);
        r6htjagaja.setOneTouchExpandable(true);
        r6htjagaja.setDividerSize(10);

        JSplitPane pystjagaja = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                r6htjagaja,
                valemid.tekstipaneel);
        pystjagaja.setOneTouchExpandable(true);
        pystjagaja.setDividerSize(10);

        JPanel P6histruktuur = new JPanel(new BorderLayout());
        P6histruktuur.add(ylemine.tekstipaneel, BorderLayout.NORTH);
        P6histruktuur.add(pystjagaja, BorderLayout.CENTER);
        P6histruktuur.add(statistika.tekstipaneel, BorderLayout.WEST);

/*      JPanel mainPanel = new JPanel(new GridLayout(1,3));
        mainPanel.add(...);    */

        transleerimise_nupp.addActionListener((e) -> {

            if(teisendus.kas_nukleotiide_on_muudetud)
            {
                aminohapped.tekstiala.setText( teisendus.transleeri() );
                valemid.tekstiala.setText( teisendus.teisenda_valemiteks() );
            }
            else if(teisendus.koodonid.size() > 0)
            {
                aminohapped.tekstiala.setText( teisendus.transleeri() );
                valemid.tekstiala.setText( teisendus.teisenda_valemiteks() );
            }
        });

        statistika_tekitamise_nupp.addActionListener( (e) -> 
            statistika.tekstiala.setText( teisendus.anna_statistika()) );


        k6ige_puhastamise_nupp.addActionListener((e) -> {

            nukleotiidid.tekstiala.setText(null);
            aminohapped .tekstiala.setText(null);            
            statistika  .tekstiala.setText(null);
            valemid     .tekstiala.setText(null);
            ylemine     .tekstiala.setText(null);
            teisendus.koodonid.clear();
            teisendus.uus_tekst = "";            
        });

        faili_avamise_nupp.addActionListener((e) -> {

            JFileChooser failiValimisDialoog = new JFileChooser();
            failiValimisDialoog.setCurrentDirectory(new File(System.getProperty("user.home")));

            int valik = failiValimisDialoog.showOpenDialog(null);

            if (valik == JFileChooser.APPROVE_OPTION)
            {
                File fail = failiValimisDialoog.getSelectedFile();

                String tekst = "";
                try
                {
                    InputStream sisendfaili_voog = new FileInputStream(new File(fail.getAbsolutePath()));
                    ylemine.tekstiala.setText( pealdis + fail.getName() );

                    teisendus.koodonid.clear();

                    byte [] koodon = new byte[3];

                    while ( sisendfaili_voog.read(koodon,0,3) != -1 )
                    {
                        teisendus.koodonid.add(new String(koodon));
                    }
                    for (String kdn: teisendus.koodonid)
                    {
                        tekst += (kdn + " ");
                    }
                }
                catch(IOException a)
                {
                    JOptionPane.showMessageDialog(null, "Ei leitud sisendfaili.");
                }

                nukleotiidid.tekstiala.setText(tekst);
            }
        });

        nukleotiidid.tekstiala.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                teisendus.kas_nukleotiide_on_muudetud = true;
                teisendus.uus_tekst = nukleotiidid.tekstiala.getText().replaceAll("[^a-zA-Z]", "");
                //teisendus.uus_tekst = nukleotiidid.tekstiala.getText().replaceAll("\\s+", "");
            }
        });


        JFrame aken = new JFrame();

        aken.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aken.setSize(new Dimension(790, 640));        
        aken.setLocationRelativeTo(null);
        aken.setTitle("DNA uurimine.");

        aken.setLayout(new BorderLayout());
        aken.add(P6histruktuur, BorderLayout.CENTER);

        aken.setVisible(true);

        r6htjagaja.setDividerLocation(r6htjagaja.getHeight() / 2);
        pystjagaja.setDividerLocation(pystjagaja.getWidth() / 2);


        nukleotiidid.tekstiala.setText("ATG");
        aminohapped.tekstiala.setText("M");
        valemid.tekstiala.setText("CH3-S-(CH2)2-CH(NH2)-COOH");
        statistika.tekstiala.setText("Nukleotiidide\nesinemissagedus.");        
    }
}
