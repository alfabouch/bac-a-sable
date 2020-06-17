package fr.gouv.rie.e2.application.commun.persistance;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDatabase<T> {
    
    public ResultSet queryWithStatement(String query) {
        
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/bacasable", "postgres", "postgres")) {
            
            Statement statement = connection.createStatement();
            
            if (query.toLowerCase().startsWith("select")) { return statement.executeQuery(query); }
            else { statement.executeUpdate(query); }
        }
        catch (SQLException e) {
            System.out.println("Query failure.");
            e.printStackTrace();
        }
        
        return null;
    }
    
    public T queryOne(String query) {
        
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/bacasable", "postgres", "postgres")) {
            
            Statement statement = connection.createStatement();
            
            if (query.toLowerCase().startsWith("select")) { return getEntity(statement.executeQuery(query)); }
            else { statement.executeUpdate(query); }
        }
        catch (SQLException e) {
            System.out.println("Query failure.");
            e.printStackTrace();
        }
        
        return null;
    }
    
    public List<T> queryList(String query) {
        
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/bacasable", "postgres", "postgres")) {
            
            Statement statement = connection.createStatement();
            
            if (query.toLowerCase().startsWith("select")) { return getEntities(statement.executeQuery(query)); }
            else { statement.executeUpdate(query); }
        }
        catch (SQLException e) {
            System.out.println("Query failure.");
            e.printStackTrace();
        }
        
        return null;
    }
    
    public T getEntity(ResultSet resultSet) {
        
        try {
            
            T entity = newInstance();
            
            Field[] declaredFields = entity.getClass().getDeclaredFields();
            
            for (Field field : declaredFields) {
                
                System.out.println();
                switch (field.getType().getName()) {
                    case "long":
                        field.set(entity, resultSet.getLong(field.getName()));
                        break;
                    case "java.lang.String":
                        field.set(entity, resultSet.getString(field.getName()));
                        break;
                    default:
                        System.out.println(field.getType().getName());
                        break;
                }
            }
            
            return entity;
        }
        catch (IllegalAccessException | SQLException e) {
            // TODO : Catcher cette exception correctement !
            e.printStackTrace();
        }
        
        return null;
    }
    
    protected List<T> getEntities(ResultSet resultSet) throws SQLException {
        
        List<T> entities = new ArrayList<>();
        
        while (resultSet.next()) {
            entities.add(getEntity(resultSet));
        }
        
        return entities;
    }
    
    T newInstance() {
        
        try {
            return (T) ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            // TODO : Catcher cette exception correctement !
            e.printStackTrace();
        }
        
        return null;
    }
}
