/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lilib
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import javax.imageio.ImageIO;

public class gui extends JFrame implements ActionListener {

    private JButton boton_izquierda, boton_derecha, boton_pausa;
    private JLabel label_estado, label_tiempo;
    Font customFont = new Font("Arial", Font.BOLD, 16);

    public gui() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Motor controller GUI");

        label_estado = new JLabel("The motor isn't moving");
        label_estado.setFont(customFont);
        label_estado.setBounds(134, 100, 300, 100);
        add(label_estado);

        // Escalar y cargar las imágenes
        ImageIcon img_flecha_izquierda = new ImageIcon(scaleImage("img/izquierda.png", 80, 80));
        boton_izquierda = new JButton(img_flecha_izquierda);
        boton_izquierda.setBounds(134, 200, 80, 80);
        boton_izquierda.addActionListener(this);
        add(boton_izquierda);

        ImageIcon img_pausa = new ImageIcon(scaleImage("img/pausa.png", 80, 80));
        boton_pausa = new JButton(img_pausa);
        boton_pausa.setBounds(218, 200, 80, 80);
        boton_pausa.addActionListener(this);
        add(boton_pausa);

        ImageIcon img_flecha_derecha = new ImageIcon(scaleImage("img/derecha.png", 80, 80));
        boton_derecha = new JButton(img_flecha_derecha);
        boton_derecha.setBounds(302, 200, 80, 80);
        boton_derecha.addActionListener(this);
        add(boton_derecha);

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = sdf.format(now);

        label_tiempo = new JLabel(formattedTime);
        label_tiempo.setFont(customFont);
        label_tiempo.setBounds(218, 250, 100, 100);
        add(label_tiempo);

        // Crear un Timer para actualizar el tiempo cada segundo
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la hora actual
                Date now = new Date();
                // Formatear la hora en el formato deseado (por ejemplo, HH:mm:ss)
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String formattedTime = sdf.format(now);
                // Actualizar el texto del JLabel con la hora formateada
                label_tiempo.setText(formattedTime);
            }
        });
        timer.start(); // Iniciar el Timer
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton_izquierda) {
            label_estado.setText("The motor is spinning to the left");
        }
        if (e.getSource() == boton_pausa) {
            label_estado.setText("The motor has stopped");
        }
        if (e.getSource() == boton_derecha) {
            label_estado.setText("The motor is spinning to the right");
        }
    }

    public static void main(String[] args) {
        gui Gui = new gui();
        Gui.setBounds(0, 0, 500, 500);
        Gui.setVisible(true);
        Gui.setResizable(false);
        Gui.setLocationRelativeTo(null);
    }

    public BufferedImage scaleImage(String imagePath, int width, int height) {
    try {
        // Cargar la imagen original usando getResourceAsStream
        InputStream inputStream = getClass().getResourceAsStream(imagePath);
        if (inputStream == null) {
            throw new IOException("No se pudo encontrar el archivo: " + imagePath);
        }
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Escalar la imagen a las nuevas dimensiones
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Crear una nueva imagen a partir de la imagen escalada
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(scaledImage, 0, 0, null);
        g.dispose();

        return resizedImage;
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}

}

