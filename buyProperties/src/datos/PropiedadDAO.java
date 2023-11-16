package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Conexion;
import datos.interfaces.CrudSimpleInterface;
import entidades.Propiedad;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PropiedadDAO implements CrudSimpleInterface<Propiedad> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public PropiedadDAO(){
        CON=Conexion.getInstancia();
    }
    
    
    @Override
    public List<Propiedad> listar(String texto) {
    List<Propiedad> registros = new ArrayList<>();
    try {
        ps = CON.conectar().prepareStatement("SELECT * FROM propiedades WHERE nombre LIKE ?");
        ps.setString(1, "%" + texto + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            Propiedad propiedad = new Propiedad();
            propiedad.setId(rs.getInt("id"));
            propiedad.setNombre(rs.getString("nombre"));
            propiedad.setDireccion(rs.getString("direccion"));
            propiedad.setPrecioAlquiler(rs.getDouble("precioAlquiler"));
            propiedad.setCaracteristicas(rs.getString("caracteristicas"));
            propiedad.setActivo(rs.getBoolean("activo"));
            
            registros.add(propiedad);
        }
        ps.close();
        rs.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
        ps = null;
        rs = null;
        CON.desconectar();
    }
    return registros;
}

    
    public List<Propiedad> seleccionar() {
    List<Propiedad> registros = new ArrayList<>();
    try {
        ps = CON.conectar().prepareStatement("SELECT id, nombre, direccion, precioAlquiler, caracteristicas FROM propiedades ORDER BY nombre ASC");
        rs = ps.executeQuery();
        while (rs.next()) {
            Propiedad propiedad = new Propiedad();
            propiedad.setId(rs.getInt("id"));
            propiedad.setNombre(rs.getString("nombre"));
            propiedad.setDireccion(rs.getString("direccion"));
            propiedad.setPrecioAlquiler(rs.getDouble("precioAlquiler"));
            propiedad.setCaracteristicas(rs.getString("caracteristicas"));
            propiedad.setActivo(rs.getBoolean("activo"));

            registros.add(propiedad);
        }
        ps.close();
        rs.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
        ps = null;
        rs = null;
        CON.desconectar();
    }
    return registros;
}

    
    

   @Override
    public boolean insertar(Propiedad obj) {
    resp = false;
    try {
        ps = CON.conectar().prepareStatement("INSERT INTO propiedades (nombre, direccion, precioAlquiler, caracteristicas, activo) VALUES (?, ?, ?, ?, 1)");
        ps.setString(1, obj.getNombre());
        ps.setString(2, obj.getDireccion());
        ps.setDouble(3, obj.getPrecioAlquiler());
        ps.setString(4, obj.getCaracteristicas());
        if (ps.executeUpdate() > 0) {
            resp = true;
        }
        ps.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
        ps = null;
        CON.desconectar();
    }
    return resp;
}

@Override
    public boolean actualizar(Propiedad obj) {
    resp = false;
    try {
        ps = CON.conectar().prepareStatement("UPDATE propiedades SET nombre=?, direccion=?, precioAlquiler=?, caracteristicas=? WHERE id=?");
        ps.setString(1, obj.getNombre());
        ps.setString(2, obj.getDireccion());
        ps.setDouble(3, obj.getPrecioAlquiler());
        ps.setString(4, obj.getCaracteristicas());
        ps.setInt(5, obj.getId());

        if (ps.executeUpdate() > 0) {
            resp = true;
        }
        ps.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
        ps = null;
        CON.desconectar();
    }
    return resp;
}

    @Override
    public boolean desactivar(int id) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE propiedades SET activo=0 WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean activar(int id) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE propiedades SET activo=1 WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public int total() {
        int totalRegistros=0;
        try {
            ps=CON.conectar().prepareStatement("SELECT COUNT(id) FROM propiedades");            
            rs=ps.executeQuery();
            
            while(rs.next()){
                totalRegistros=rs.getInt("COUNT(id)");
            }            
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("SELECT nombre FROM propiedades WHERE nombre=?");
            ps.setString(1, texto);
            rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()>0){
                resp=true;
            }           
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean eliminar(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("DELETE FROM propiedades WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
    
    
    
}
