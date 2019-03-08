/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajachicav2;

import com.cajachica.dao.UsuarioDao;
import com.cajachica.pojos.Usuario;
import com.cajachica.view.vtnLogin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.Conexion;

/**
 *
 * @author reynaldo
 */
public class CajaChicaV2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        vtnLogin login = new vtnLogin();
        login.setVisible(true);

        //Pruebas
        UsuarioDao udao = new UsuarioDao();
        Usuario user = new Usuario();
        try
        {
         /*   udao.UsuarioById(15);
            Conexion con = new Conexion();
            if (con.getConectar() != null) {
                System.out.print("CONEXION REALIZADA");
            } else {
                System.out.print("CONEXION FALLIDA");
            }*/

        } catch (Exception ex) {
            Logger.getLogger(CajaChicaV2.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR EN LA RECUPERACION DEL OBJETO");
        }

    }

}
