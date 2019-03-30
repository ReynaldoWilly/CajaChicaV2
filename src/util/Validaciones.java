/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author Reynaldo
 */
public class Validaciones {

    public static boolean validarCampos(List<String> campos) {
        for (String nombre : campos) {
            if (nombre.equals("")) {
                return false;
            }
        }
        return true;
    }

    //Funcion que da el formato a la fecha
    public static String formatoFecha(Date dateString) {
        Date date = null;
        String formatofecha = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //date = df.parse(dateString);
            formatofecha = df.format(dateString);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return formatofecha;
    }

    //metodo que realiza la validacion de un JtextFiel de ingreso de numeros
    public static void validaNumeros(JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }

    //metodo que realiza la validacion de un JtextFiel de ingreso de texto
    public static void validaTexto(JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }
}
