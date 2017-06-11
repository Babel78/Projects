package BDMetodosJSON;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import jsong.Usuario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MetodosUsuariosJSON {
    JSONArray array;
    public void Actualizar(Usuario nuevo) throws ParseException{
        if(Existe(nuevo)){
            array=ObtenerArray();
            int in=Indice(nuevo, array);
            AuxActualizar(nuevo,in);    
            
        }
        else{
            System.out.println("Usuario no encontrado"); 
        }
    }
    private void AuxActualizar(Usuario nuevo,int indice){
            JSONObject obj=new JSONObject();
            obj.put("Nombre", nuevo.getNombre());
            obj.put("DNI", nuevo.getDni());
            obj.put("Telefono", nuevo.getTelefono());
            array.remove(indice);
            array.add(indice, obj);
          try {
            FileWriter f=new FileWriter("Usuarios.json");
            f.write(array.toJSONString());
            f.flush();
        } catch (IOException e) {
            System.out.println(e);
        }    
    }    
    public void Guardar(Usuario nuevo) throws ParseException{
        JSONObject obj=new JSONObject();
        obj.put("Nombre", nuevo.getNombre());
        obj.put("DNI", nuevo.getDni());
        obj.put("Telefono", nuevo.getTelefono());
        array=ObtenerArray(); 
        if(array==null){
            array=new JSONArray();
            array.add(obj);
        }
        else{
          if(Existe(nuevo)){
              System.out.println("DNI se encuentra registrado!");
          }
          else{
            array.add(obj);  
            try {
            FileWriter f=new FileWriter("Usuarios.json");
            f.write(array.toJSONString());
            f.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
          }
       }        

    }
    
    private JSONArray ObtenerArray() throws ParseException{
        JSONArray jarray=null;
        JSONParser parser = new JSONParser();
        try {
            Object obj=parser.parse(new FileReader("Usuarios.json"));
            jarray=(JSONArray) obj;
            //System.out.println(jarray);
        } catch (IOException e) {
            System.out.println(e);
        }
        return jarray;
    }
    
    public void Mostrar() throws ParseException{
        JSONArray msg=ObtenerArray();
        if(msg==null){
            System.out.println("No existe el archivo");
        }
        else{
            for (int i = 0; i < msg.size(); i++) {
            JSONObject obj = (JSONObject) msg.get(i);
            Usuario nuevo=new Usuario((String)obj.get("Nombre"),(String)obj.get("DNI"),(String)obj.get("Telefono"));
            System.out.println(nuevo.toString());
        }
        }
        
    }
    
    public Usuario ObtenerPorDNI(String dni) throws ParseException{
        JSONArray msg=ObtenerArray();
        Usuario nuevo=null;
        if(msg==null){
            System.out.println("No existe el archivo");
        }
        else{
            int i=0;
            while(i<msg.size()){
            JSONObject obj = (JSONObject) msg.get(i);
            if((obj.get("DNI")).equals(dni)){
            nuevo=new Usuario((String)obj.get("Nombre"),(String)obj.get("DNI"),(String)obj.get("Telefono"));
            break;
            }
            else{
                i++;
            }
            }
        }
        return nuevo;
    }
    
    public boolean Existe(Usuario us) throws ParseException{
        boolean existe=false;
        int i=0;
        JSONArray arr=ObtenerArray();
        while(i<arr.size() && !existe){
            JSONObject o=(JSONObject) arr.get(i);
            if(o.get("DNI").equals(us.getDni())){
                existe=true;
            }
            else{
                i++;
            }
        }
       return existe; 
    }
    private int Indice(Usuario us,JSONArray arr) throws ParseException{
        int i=0;
        int indice = 0;
        while (i<arr.size()) {
             JSONObject get = (JSONObject) arr.get(i);
            if(get.get("DNI").equals(us.getDni())){
                indice=i;
                break;
            }
            else{
                i++;
            }
        }
        return indice;
    }        
}
