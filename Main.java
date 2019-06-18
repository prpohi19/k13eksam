/*
Programm käsitleb ainult kõige tavalisemaid nukleotiidide järjendeid.
Looduses on esiteks palju erandeid ja teiseks palju muid huvitavaid seaduspäraseid nähtusi.

Programmi jaoks sobivate nukleotiidide järjendi otsimisel 
tuleb silmas pidada, et sobiv järjend peab algama 
tavalise startkoodoniga ATG.

Paar sobivat nukleotiidide jada:

Influenza A virus (A/California/07/2009(H1N1)) segment 2 polymerase PB1 (PB1) gene, complete cds; and nonfunctional PB1-F2 protein (PB1-F2) gene, complete sequence
https://www.ncbi.nlm.nih.gov/nuccore/NC_026435.1
 
Human adenovirus D15H9 partial fibershaft gene:
https://www.ncbi.nlm.nih.gov/nuccore/258683201

*/


import javax.swing.*;


public class Main extends Kasutajaliides
{
    public static void main(String[] args)
    {
        try
        {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception erind)
        {
            erind.printStackTrace();
        }

        SwingUtilities.invokeLater( () -> new Kasutajaliides() );
    }
}