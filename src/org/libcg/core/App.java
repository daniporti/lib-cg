package org.libcg.core;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Arrays;
import org.libcg.database.DbInit;

public class App {
    public static <T extends Controller> void run(Class<T> entrypoint) {
        try {
            Connection connection = Persistence.getConnection();
            
            Arrays.asList(DbInit.run())
                    .stream()
                    .forEach(query -> {
                        try {
                            connection.prepareCall(query)
                                    .execute();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    });
            T instance = entrypoint.getDeclaredConstructor().newInstance();

            instance.main();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                InvocationTargetException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public <T> void call(Class<T> clazz) {
        call(clazz, null);
    }
    
    public <T> void call(Class<T> clazz, String methodName, Object... args) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();

            // Se um método específico foi fornecido, encontre-o
            if (methodName != null) {
                Method method = clazz.getMethod(methodName, getParameterTypes(args));
                method.invoke(instance, args);
            } else {
                // Se nenhum método foi fornecido, chame o método padrão (sem parâmetros)
                Method method = clazz.getMethod("main");
                method.invoke(instance);
            }

        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Class<?>[] getParameterTypes(Object[] args) {
        Class<?>[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                parameterTypes[i] = args[i].getClass();
            }
        }
        
        return parameterTypes;
    }
}
