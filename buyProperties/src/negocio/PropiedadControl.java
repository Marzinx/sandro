package negocio;

import datos.PropiedadDAO;
import entidades.Propiedad;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class PropiedadControl {
    private final PropiedadDAO DATOS;
    private Propiedad obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public PropiedadControl(){
        this.DATOS=new PropiedadDAO();
        this.obj=new Propiedad();
        this.registrosMostrados=0;
    }
    
    public DefaultTableModel listar(String texto){
        List<Propiedad> lista=new ArrayList();
        lista.addAll(DATOS.listar(texto));
        
        String[] titulos={"Id","Nombre","Direccion","Alquiler","Caracteristicas","Estado"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        
        
        String estado;
        String[] registro = new String[6];
        
        this.registrosMostrados=0;
        for (Propiedad item:lista){
            if (item.isActivo()){
                estado="Activo";
            } else{
                estado="Inactivo";
            }
            registro[0]=Integer.toString(item.getId());
            registro[1]=item.getNombre();
            registro[2]=item.getDireccion();
            registro[3]=Double.toString(item.getPrecioAlquiler());
            registro[4]=item.getCaracteristicas();
            registro[5]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    
    public String insertar(String nombre, String direccion, double precioAlquiler, String caracteristicas) {
    if (DATOS.existe(nombre)) {
        return "El registro ya existe.";
    } else {
        obj.setNombre(nombre);
        obj.setDireccion(direccion);  // Asegúrate de tener un método setDireccion en tu clase Propiedad
        obj.setPrecioAlquiler(precioAlquiler);    // Asegúrate de tener un método setPrecioAlquiler en tu clase Propiedad
        obj.setCaracteristicas(caracteristicas);  // Asegúrate de tener un método setCaracteristicas en tu clase Propiedad

        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el registro.";
        }
    }
}
    
    public String actualizar(int id, String nombre, String nombreAnt, String direccion, double precioAlquiler, String caracteristicas) {
    if (nombre.equals(nombreAnt)) {
        obj.setId(id);
        obj.setNombre(nombre);
        obj.setDireccion(direccion);
        obj.setPrecioAlquiler(precioAlquiler);
        obj.setCaracteristicas(caracteristicas);

        if (DATOS.actualizar(obj)) {
            return "OK";
        } else {
            return "Error en la actualización.";
        }
    } else {
        if (DATOS.existe(nombre)) {
            return "El registro ya existe.";
        } else {
            obj.setId(id);
            obj.setNombre(nombre);
            obj.setDireccion(direccion);
            obj.setPrecioAlquiler(precioAlquiler);
            obj.setCaracteristicas(caracteristicas);

            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "Error en la actualización.";
            }
        }
    }
}



    
    public String desactivar(int id){
        if (DATOS.desactivar(id)){
            return "OK";
        }else{
            return "No se puede desactivar el registro";
        }
    }
    
    public String activar(int id){
        if (DATOS.activar(id)){
            return "OK";
        }else{
            return "No se puede activar el registro";
        }
    }
    
    public int total(){
        return DATOS.total();
    }
    
    public int totalMostrados(){
        return this.registrosMostrados;
    }
    
    public String eliminar(int id) {
        if (DATOS.eliminar(id)) {
            return "OK";
        } else {
            return "Error en la eliminación de datos.";
        }
    }
}
