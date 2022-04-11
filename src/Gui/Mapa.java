/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author gruiboter
 */
public class Mapa extends JFrame {

    char[][] terreno = {
            {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '1', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}
    };

    int posFila;
    int posColumna;

    JLabel[][] casillas;

    // Constantes

    final int FILAS = terreno.length;
    final int COLUMNAS = terreno[0].length;
    final int LABEL_SIZE = 50;

    final Color HIERBA = new Color(79, 169, 113);
    final Color PIEDRA = Color.GRAY;

    final String PERSONAJE = String.valueOf((char) 167);
    final Font FUENTE_BASICA = new Font("Serif", Font.PLAIN, 24);


    public Mapa() {

        super("Mapa");

        posColumna = 0;
        posFila = 0;

        setLayout(new GridLayout(FILAS, COLUMNAS, 1, 1));

        setSize(COLUMNAS * LABEL_SIZE, FILAS * LABEL_SIZE);

        casillas = new JLabel[FILAS][COLUMNAS];

        inicializarCasillas();

        casillas[posFila][posColumna].setText(PERSONAJE);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Introducimos el Key Listener

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_W:
                        try {
                            boolean movimientoValido = terreno[posFila - 1][posColumna] != '1';
                            if (movimientoValido) {
//                                int botonAnterior [] = {posFila, posColumna};
//                                int botonSiguiente [] = {posFila - 1, posColumna};
                                casillas[posFila][posColumna].setText("");
                                posFila--;
                                casillas[posFila][posColumna].setText(PERSONAJE);
                            }
                            System.out.println("Moviendose con W");
                        } catch (Exception e) {
                        }
                        break;
                    case KeyEvent.VK_S:
                        try {
                            boolean movimientoValido = terreno[posFila + 1][posColumna] != '1';
                            if (movimientoValido) {
                                casillas[posFila][posColumna].setText("");
                                posFila++;
                                casillas[posFila][posColumna].setText(PERSONAJE);
                            }
                            System.out.println("Moviendose con S");
                        } catch (Exception e) {
                        }
                        break;
                    case KeyEvent.VK_D:
                        try {
                            boolean movimientoValido = terreno[posFila][posColumna + 1] != '1';
                            if (movimientoValido) {
                                casillas[posFila][posColumna].setText("");
                                posColumna++;
                                casillas[posFila][posColumna].setText(PERSONAJE);
                            }
                            System.out.println("Moviendose con D");
                        } catch (Exception e) {
                        }
                        break;
                    case KeyEvent.VK_A:
                        try {
                            boolean movimientoValido = terreno[posFila][posColumna - 1] != '1';
                            if (movimientoValido) {
                                casillas[posFila][posColumna].setText("");
                                posColumna--;
                                casillas[posFila][posColumna].setText(PERSONAJE);
                            }
                            System.out.println("Moviendose con A");
                        } catch (Exception e) {
                        }
                        break;

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public void mostrarterreno() {
        for (int i = 0; i < terreno.length; i++) {

            for (int j = 0; j < terreno[i].length; j++) {
                System.out.print(terreno[i][j]);
            }
            System.out.println("");
        }
    }

    private void inicializarCasillas() {

        casillas = new JLabel[FILAS][COLUMNAS];


        for (int i = 0; i < terreno.length; i++) {
            for (int j = 0; j < terreno[i].length; j++) {

                casillas[i][j] = new JLabel("", JLabel.CENTER);
                casillas[i][j].setOpaque(true);
                casillas[i][j].setSize(LABEL_SIZE, LABEL_SIZE);
                casillas[i][j].setFont(FUENTE_BASICA);

                switch (terreno[i][j]) {
                    case '0':
                        casillas[i][j].setBackground(HIERBA);
                        break;
                    case '1':
                        casillas[i][j].setBackground(PIEDRA);
                        break;
                }
                add(casillas[i][j]);
            }

        }

    }
}
