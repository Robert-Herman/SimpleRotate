import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Start extends JFrame {
    private static final long serialVersionUID = 1L;
    private static Triangle r = new Triangle();
    private static double[][] triangle = r.triangleXYZ;
    private static double[][][] pyramid;
    private static double[][] vectorsN;

    public Start() {
        GamePanel game = new GamePanel();
        this.setBounds(200, 200, 0, 0);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(game);
        this.pack();
        this.setVisible(true);
    }

    public class GamePanel extends JPanel implements ActionListener {
        private static final long serialVersionUID = 1L;
        private Color[] color;
        private Color[] colorN = { new Color(255,0,0), new Color(0,255,0), new Color(0,0,255),new Color(0,0,0) };
        private InputManager im;
        Timer timer = new Timer(40, this);
        
        public GamePanel() {
            im = new InputManager();
            this.addKeyListener(im);
            this.setFocusable(true);
            
            color = new Color[4];
            for (int i=0; i<color.length; i++) {
                color[i] = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
            }
            r.scale(new double[] {100,100,100});
            r.translate(new double[] {300,150,0});
            triangle = r.triangleXYZ;
            pyramid = r.pyramid;
            timer.start();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(600, 300);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            super.paintComponent(g2d);
            
            for (int i=0; i<triangle[0].length; i++) {
                g2d.setColor(color[i]);
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(colorN[i]);
                if (i != 3) {
                    if (vectorsN[2][i] < 0) {
                        g2d.drawLine((int)pyramid[i][0][0], (int)pyramid[i][1][0], (int)pyramid[i][0][1], (int)pyramid[i][1][1]);
                        g2d.drawLine((int)pyramid[i][0][0], (int)pyramid[i][1][0], (int)pyramid[i][0][2], (int)pyramid[i][1][2]);
                        g2d.drawLine((int)pyramid[i][0][1], (int)pyramid[i][1][1], (int)pyramid[i][0][2], (int)pyramid[i][1][2]);
                    }
                }
                else {
                    if (vectorsN[2][i] > 0) {
                        g2d.drawLine((int)pyramid[i][0][0], (int)pyramid[i][1][0], (int)pyramid[i][0][1], (int)pyramid[i][1][1]);
                        g2d.drawLine((int)pyramid[i][0][0], (int)pyramid[i][1][0], (int)pyramid[i][0][2], (int)pyramid[i][1][2]);
                        g2d.drawLine((int)pyramid[i][0][1], (int)pyramid[i][1][1], (int)pyramid[i][0][2], (int)pyramid[i][1][2]);
                    }
                }
            } 
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == timer) {
                int[] keyVector = {(im.isKeyDown(KeyEvent.VK_LEFT) ? -1 : 0) + (im.isKeyDown(KeyEvent.VK_RIGHT) ? 1 : 0),
                                   (im.isKeyDown(KeyEvent.VK_UP) ? -1 : 0)   + (im.isKeyDown(KeyEvent.VK_DOWN)  ? 1 : 0)};
                r.translate(new double[] {-300,-150,0});
                r.scale(new double[] {0.1,0.1,0.1});
                r.rotateY((double)keyVector[0]*4);
                r.rotateX((double)keyVector[1]*4);
                vectorsN = r.normalVector();
                r.scale(new double[] {10,10,10});
                r.translate(new double[] {300,150,0});
                triangle = r.triangleXYZ;
                pyramid = r.pyramid;
                repaint();
                im.update();
            }
        }
    }
    public static void main(String[] args) {
        Start start = new Start();
    }
}