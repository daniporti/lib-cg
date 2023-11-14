package org.libcg.core;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model<T> {
    protected Connection connection;
    protected int id;
    protected String tableName = getClass().getSimpleName();
    
    public Model() {
        id = 0;
        
        try {
            connection = Persistence.getConnection();
        } catch (SQLException e) {
            System.out.println("Falha ao obter conexão");
            System.out.println(e.toString());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getTableName() {
        return tableName;
    }
    
    public static <K extends Model> List<K> findAll(Class<K> clazz) {
        try {
            K model = (K) clazz.getDeclaredConstructor().newInstance();

            List<K> resultList = new ArrayList<>();

            String sql = "SELECT * FROM " + model.getTableName();
            try (PreparedStatement preparedStatement = model.getConnection().prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    K instance = (K) model.mapResultSetToObject(resultSet);
                    resultList.add(instance);
                }
            }
            
            return resultList;
        } catch (SQLException | ReflectiveOperationException e) {
            System.out.println("Erro ao buscar registros");
            System.out.println(e.toString());
        }
        
        return null;
    }
    
    public static <K extends Model> K findOne(int id, Class<K> clazz) {
        try {
            K model = (K) clazz.getDeclaredConstructor().newInstance();
            
            String sql = "SELECT * FROM " + model.getTableName() + " WHERE id=?";
            try (PreparedStatement preparedStatement = model.getConnection().prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return (K) model.mapResultSetToObject(resultSet);
                    }
                }
            }
        } catch (SQLException | ReflectiveOperationException e) {
            System.out.println("Erro ao buscar registro por ID");
            System.out.println(e.toString());
        }

        return null;  // Retornar null se nenhum registro for encontrado
    }
    
    public void save() {
        try {
            Field idField = getClass().getDeclaredField("id");
            idField.setAccessible(true);
            Object idValueObject =  idField.get(this);
        
            if (idValueObject == null) {
                insert();
            } else {
                Integer idValue = (Integer) idValueObject;
                
                if (idValue > 0) {
                    update();
                } else {
                    insert();
                }
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
    
    protected void insert() {
        try {
            String sql = generateInsertStatement();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                setPreparedStatementValues(preparedStatement, false);

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("A inserção falhou, nenhum registro afetado.");
                }

                // Recuperar a chave primária gerada, se aplicável
                try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException | ReflectiveOperationException e) {
            System.out.println("Erro ao inserir modelo no banco de dados.");
            e.printStackTrace();
        }
    }
    
    protected void update() {
        try {
            String sql = generateUpdateStatement();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                setPreparedStatementValues(preparedStatement, true);

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("A atualização falhou, nenhum registro afetado.");
                }
            }
        } catch (SQLException | ReflectiveOperationException e) {
            System.out.println("Erro ao atualizar modelo no banco de dados.");
            e.printStackTrace();
        }
    }

    private String generateUpdateStatement() {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE ");
        sqlBuilder.append(tableName).append(" SET ");

        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equals("id")) { // Não incluir o ID na cláusula SET
                sqlBuilder.append(field.getName()).append("=?, ");
            }
        }

        // Remover a última vírgula
        sqlBuilder.setLength(sqlBuilder.length() - 2);

        sqlBuilder.append(" WHERE id=?");

        return sqlBuilder.toString();
    }

    private void setPreparedStatementValues(PreparedStatement preparedStatement, boolean isUpdate) throws SQLException, ReflectiveOperationException {
        Field[] fields = getClass().getDeclaredFields();
        int fieldIndex = 0;
        int statementCount = 1;
        
        while (fieldIndex < fields.length) {
            Field field = fields[fieldIndex];
            field.setAccessible(true);
            
            if (field.getName().equals("id")) {
                fieldIndex++;
                continue;
            }

            Object value = field.get(this); // Obter valor do campo no objeto atual
            preparedStatement.setObject(statementCount, value);
            
            statementCount++;
            fieldIndex++;
        }
        
        if (isUpdate) {
            // Definir o valor para a cláusula WHERE (ID)
            Field idField = getClass().getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue = idField.get(this);
            preparedStatement.setObject(statementCount, idValue);
        }
    }

    private String generateInsertStatement() {
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
        sqlBuilder.append(tableName).append(" (");

        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("id")) {
                continue;
            }
            
            sqlBuilder.append(field.getName()).append(", ");
        }

        // Remover a última vírgula
        sqlBuilder.setLength(sqlBuilder.length() - 2);

        sqlBuilder.append(") VALUES (");

        for (Field field : fields) {
            if (field.getName().equals("id")) {
                continue;
            }
            sqlBuilder.append("?, ");
        }

        // Remover a última vírgula
        sqlBuilder.setLength(sqlBuilder.length() - 2);

        sqlBuilder.append(")");

        return sqlBuilder.toString();
    }

    private void setId(int id) throws ReflectiveOperationException {
        Field idField = getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(this, id);
    }
    
    public T mapResultSetToObject(ResultSet resultSet) throws SQLException {
        try {
            Class<?> clazz = getClass();
            T instance = (T) clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                String columnName = field.getName();

                try {
                    Object value = resultSet.getObject(columnName, field.getType());
                    field.set(instance, value);
                } catch (Exception e) {
                    // Lidar com exceções de mapeamento, se necessário
                }
            }
            return instance;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Erro ao mapear ResultSet para objeto.", e);
        }
    }
}
