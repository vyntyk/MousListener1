import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class KeyListenerExample extends JFrame implements KeyListener, MouseListener, MouseMotionListener {
    private int x = 0; // initial X position of the image
    private int y = 25; // initial Y position of the image
    private BufferedImage image;
    private int moveSpeed = 10; // normal movement speed
    private List<int[]> imagePositions; // List to hold positions of images

    public KeyListenerExample() {
        setTitle("Moving Image and Mouse Interaction");
        setSize(600, 605);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        try {
            // Load the image from a PNG file
            image = ImageIO.read(new File("src\\heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        imagePositions = new ArrayList<>();
        imagePositions.add(new int[]{x, y}); // Add initial image position

        addKeyListener(this); // Register KeyListener on the frame
        addMouseListener(this); // Register MouseListener on the frame
        addMouseMotionListener(this); // Register MouseMotionListener on the frame
        setFocusable(true); // Set focus on the frame
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int[] pos : imagePositions) {
            g.drawImage(image, pos[0], pos[1], null); // draw the image at the specified positions
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KeyListenerExample imageMovement = new KeyListenerExample();
            imageMovement.setVisible(true);
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SHIFT) {
            moveSpeed = 20; // double the speed when Shift is pressed
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            moveImage(-moveSpeed, 0); // move the image to the left
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moveImage(moveSpeed, 0); // move the image to the right
        } else if (keyCode == KeyEvent.VK_UP) {
            moveImage(0, -moveSpeed); // move the image up
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moveImage(0, moveSpeed); // move the image down
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SHIFT) {
            moveSpeed = 10; // revert to normal speed when Shift is released
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // Check if left mouse button is clicked
            int mouseX = e.getX();
            int mouseY = e.getY();
            imagePositions.add(new int[]{mouseX - image.getWidth() / 2, mouseY - image.getHeight() / 2});
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Not used in this example
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Not used in this example
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Not used in this example
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Not used in this example
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Not used in this example
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Not used in this example
    }

    public void moveImage(int dx, int dy) {
        x += dx;
        y += dy;
        if (x < 0) {
            x = getWidth() - image.getWidth();
        } else if (x + image.getWidth() > getWidth()) {
            x = 0;
        }
        if (y < 0) {
            y = getHeight() - image.getHeight();
        } else if (y + image.getHeight() > getHeight()) {
            y = 25;
        }
        repaint(); // redraw the window
    }
}
