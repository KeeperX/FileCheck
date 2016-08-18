import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 05.08.2016.
 */
public class FileCHecker {
    static Button btpisma;
    static Desktop desk;
    static SystemTray tray = SystemTray.getSystemTray();
    private static int port=33157;

   // static Image image = Toolkit.getDefaultToolkit().getImage("tray.gif");

    public static void main(String[] args){
        if (!OnlyOne.isRunning(port)) {
            System.out.println("Первый запуск");

            BufferedImage image = null;
            try {

                image = ImageIO.read(new File("res//icon.gif"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            PopupMenu menu = new PopupMenu("Меню");
            MenuItem exit = new MenuItem("Exit");
            exit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            menu.add(exit);
            TrayIcon trayIcon = new TrayIcon(image, "Проверка папок", menu);
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }

            final JLabel jl;
            String path = "d://1/";
            final String pisma = path + "Письма//";
            final String prikazy = path + "Приказы//";
            final int time = 20;
            // создаём окошко видимое неслолько секунд
            int timeWindow = 2;
            jl = new JLabel();
            jl.setText("");
            final JDialog window = new JDialog();
            window.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            btpisma = new Button();
            Button btprikazy = new Button();
            btpisma.setLabel("Письма");
            btprikazy.setLabel("Приказы");
            JPanel jp = new JPanel();
            jp.setLayout(new FlowLayout());

            jp.add(btpisma);
            JLabel jl1 = new JLabel("0");
            jp.add(jl1);
            window.getContentPane().add(jp, BorderLayout.NORTH);
            jp = new JPanel();
            jp.add(btprikazy);
            JLabel jl2 = new JLabel("0");

            jp.add(jl2);
            window.getContentPane().add(jp, BorderLayout.SOUTH);
            window.setForeground(Color.CYAN);
            window.setBackground(Color.CYAN);
            window.setUndecorated(true);
            window.setSize(100, 75);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            int x = dim.width - 100;
            final int y = dim.height - 115;
            window.setLocation(x, y);
            window.setAlwaysOnTop(true);
            //window.setLocationRelativeTo();
            //window.add(jl);
            //=========================================
            btpisma.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    try {
                        Desktop.getDesktop().open(new File(pisma));
                    } catch (Exception ert) {
                    }

                }
            });
            btprikazy.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().open(new File(prikazy));
                    } catch (Exception ert) {
                    }

                }
            });

            while (true) {
                File files = new File(pisma);
                File[] listfiles = files.listFiles();

                int ipisma = listfiles.length;
                files = new File(prikazy);
                listfiles = files.listFiles();

                int iprikazy = listfiles.length;
                if ((iprikazy != 0) || (ipisma != 0)) {
                    String s = "Писем " + ipisma + "\n Приказов " + iprikazy;
                    jl1.setText(ipisma + "");
                    jl2.setText(iprikazy + "");
                    window.setVisible(true);

                }
                if ((iprikazy == 0) && (ipisma == 0)) {
                    window.setVisible(false);
                }


                try {
                    TimeUnit.SECONDS.sleep(time);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
     else {
            System.out.println("Одна из версий уже запущена");
        }


    }


}
