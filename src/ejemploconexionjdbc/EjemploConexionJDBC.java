//https://www.youtube.com/watch?v=NeEC83DeAC0
package ejemploconexionjdbc;


import java.sql.*;//1.Incluir la libreria
import java.util.logging.Level;
import java.util.logging.Logger;
public class EjemploConexionJDBC {
    

    public static void main(String[] args) {
        //3.Definir la cadena de conexión
        String usuario = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/timcobd3";
        //4.Creación de objetos para establecer la conexión
        Connection conexion;//Permite establecer la conexion con la BD
        Statement statement;//Permite ejecutar sentencias SQL
        ResultSet rs;//Tiene la capacidad de recibir la respuesta desde la BD (Referencia de una tabla)
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//2.Instanciar o cargar el driver al proyecto - Bloque Try Catch para manejar las excepciones
            
            System.out.println("SE CONECTO CON LA BD Timco");
        } catch (ClassNotFoundException ex) {
            System.out.println("NO SE CONECTO CON LA BD Timco");
            Logger.getLogger(EjemploConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            conexion = DriverManager.getConnection(url,usuario,password);//5.Establecer la conexión
            statement = conexion.createStatement();
            
            //INSERTAR Y MOSTRAR TRABAJADOR
            statement.executeUpdate("INSERT INTO usuarios (Nombre_Completo, Usuario) VALUES ('julian','juli123')");
            rs = statement.executeQuery("SELECT * FROM usuarios");
            rs.next();
            do {
                System.out.println("Id: "+rs.getInt("idusuario")+", Nombre: "+rs.getString("Nombre_Completo")+", Usuario: "+rs.getString("Usuario"));
            } while (rs.next());
            
            //ELIMINAR TRABAJADOR POR ID
            statement.executeUpdate("DELETE FROM usuarios WHERE idusuario=14");
            rs= statement.executeQuery("SELECT * FROM usuarios");
            System.out.println("Se elimino");
            rs.next();
            do {
                System.out.println("Id: "+rs.getInt("idusuario")+", Nombre: "+rs.getString("Nombre_Completo")+", Usuario: "+rs.getString("Usuario"));
            } while (rs.next());
            
            
            
        } catch (SQLException ex) { 
            Logger.getLogger(EjemploConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
