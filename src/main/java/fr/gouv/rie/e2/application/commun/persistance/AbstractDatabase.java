package fr.gouv.rie.e2.application.commun.persistance;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class AbstractDatabase<T> {
    
    public Query query() {
        
        return new Query();
    }
    
    public ResultSet queryWithStatement(String query) {
        
        try (Connection connection = ((DataSource) ((Context) (new InitialContext().lookup("java:/comp/env"))).lookup("jdbc/bacasable")).getConnection()) {
            
            Statement statement = connection.createStatement();
            
            if (query.toLowerCase().startsWith("select")) { return statement.executeQuery(query); }
            else { statement.executeUpdate(query); }
        }
        catch (SQLException e) {
            System.out.println("Query failure.");
            e.printStackTrace();
        }
        catch (NamingException e) {
            System.out.println("Database lookup naming error.");
            e.printStackTrace();
        }
        
        return null;
    }
    
    public T queryOne(Query query) {
    
        try (Connection connection = ((DataSource) ((Context) (new InitialContext().lookup("java:/comp/env"))).lookup("jdbc/bacasable")).getConnection()) {
        
            Statement statement = connection.createStatement();
        
            if (query.type == QueryType.SELECT) { return getEntity(statement.executeQuery(query.build())); }
            else { statement.executeUpdate(query.build()); }
        }
        catch (SQLException e) {
            System.out.println("Query failure.");
            e.printStackTrace();
        }
        catch (NamingException e) {
            System.out.println("Database lookup naming error.");
            e.printStackTrace();
        }
        
        return null;
    }
    
    public List<T> queryList(Query query) {
    
        try (Connection connection = ((DataSource) ((Context) (new InitialContext().lookup("java:/comp/env"))).lookup("jdbc/bacasable")).getConnection()) {
        
            Statement statement = connection.createStatement();
        
            if (query.type == QueryType.SELECT) { return getEntities(statement.executeQuery(query.build())); }
            else { statement.executeUpdate(query.build()); }
        }
        catch (SQLException e) {
            System.out.println("Query failure.");
            e.printStackTrace();
        }
        catch (NamingException e) {
            System.out.println("Database lookup naming error.");
            e.printStackTrace();
        }
    
        return null;
    }
    
    public T getEntity(ResultSet resultSet) {
        
        try {
            
            T entity = newInstance();
            
            Field[] declaredFields = entity.getClass().getDeclaredFields();
            
            for (Field field : declaredFields) {
    
                field.setAccessible(true);
    
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
    
    protected String getEntityName() {
        
        return ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName().replaceFirst(".*\\.", "").toLowerCase();
    }
    
    public class Query {
        
        QueryType type = QueryType.SELECT;
        private String schema = "bacasable";
        private String where  = "";
        
        public Query all() {
            
            type = QueryType.SELECT;
            schema = "bacasable";
            where = "";
            
            return this;
        }
        
        public Query type(QueryType type) {
            
            this.type = type;
            return this;
        }
        
        public Query schema(String schema) {
            
            this.schema = schema;
            return this;
        }
        
        public Query where(String where) {
            
            this.where = where;
            return this;
        }
        
        protected String build() {
            
            String query = "";
            
            switch (type) {
                case SELECT:
                    query += "SELECT * ";
                    break;
                case UPDATE:
                    query += "UPDATE ";
                    break;
                case DELETE:
                    query += "DELETE ";
                    break;
            }
            
            query += " FROM " + schema + "." + getEntityName() + " ";
            
            if (!where.equals("")) { query += " WHERE " + where; }
            
            return query;
        }
    }
}