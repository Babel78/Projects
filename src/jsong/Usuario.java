
package jsong;


public class Usuario {
    String nombre;
    String dni;
    String telefono;

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", dni=" + dni + ", telefono=" + telefono + '}';
    }
    
    public Usuario(String nom,String DNI,String telf){
        nombre=nom;
        dni=DNI;
        telefono=telf;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
