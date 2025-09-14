package DAO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {

    private Gson gson = new Gson();

    public <T> List<T> leerJson(String ruta, Class<T> clase) {
        checkJson(ruta);
        try (FileReader reader = new FileReader(ruta)) {
            Type listType = TypeToken.getParameterized(List.class, clase).getType();
            List<T> lista = gson.fromJson(reader, listType);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public <T> void escribirJson(String ruta, List<T> datos) {
        checkJson(ruta);
        System.out.println(ruta);
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(datos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkJson(String ruta) {
        try {
            File archivo = new File(ruta);
            System.out.println(ruta);
            if (!archivo.exists()) {
                archivo.createNewFile();
                try (FileWriter writer = new FileWriter(archivo)) {
                    writer.write("[]");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
