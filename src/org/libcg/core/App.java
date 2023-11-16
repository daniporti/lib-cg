package org.libcg.core;

import java.sql.*;
import java.util.Arrays;
import java.util.Map;
import org.libcg.database.DbInit;

public class App {
    private static App instance;
    private static AppProvider provider;
    private Map<String, Object> instances;
    
    private App() {}
    
    public static App getInstance() {
        if (App.instance == null) {
            App.instance = new App();
        }
        
        return App.instance;
    }
    
    public static void useProvider(AppProvider provider) {
        App.provider = provider;
        
        App.provider.register(App.getInstance());
    }
    
    public <T> void singleton(Class<T> clazz, AppSingletonCallback<T> function) {
        this.instances.put(clazz.getName(), function.call());
    }
    
    public <T> T make(Class<T> clazz) {
        return (T) this.instances.get(clazz.getName());
    }
    
    public static <T extends Controller> void run(Class<T> entrypoint) {
        try {
            Connection connection = Persistence.getConnection();
            
            for (String query : Arrays.asList(DbInit.run())) {
                connection.prepareCall(query).execute();
            }
            
            App.getInstance().make(entrypoint).principal();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public <T extends View> void render(Class<T> clazz) {
        try {
            T view = (T) clazz.getDeclaredConstructor().newInstance();
            
            view.render();
        } catch(ReflectiveOperationException e) {
            e.printStackTrace();
        }
        
    }
}
