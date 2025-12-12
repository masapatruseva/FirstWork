package jdbc;

import annotation.Id;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {
    private final Connection connection;

    public JdbcTemplate(Connection connection) {
            this.connection = connection;
    }

    public void create(T objectData) {
        Class<?> clazz = objectData.getClass();
        String tableName = clazz.getSimpleName();
        Field id = getIdField(clazz);
        id.setAccessible(true);
        List<Field> fields = getNotIdFields(clazz);

        String columns = String.join(", ", fields.stream().map(Field::getName).toList());
        String values = String.join(", ", fields.stream().map(field -> "?").toList());

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, values);
        try(var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            for(int i = 0; i < fields.size(); i++){
                fields.get(i).setAccessible(true);
                statement.setObject(i + 1, fields.get(i).get(objectData));
            }
            statement.execute();
            var key = statement.getGeneratedKeys();
            if(key.next()){
                id.set(objectData, key.getLong(1));
            }

        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(T objectData) {
        Class<?> clazz = objectData.getClass();
        String tableName = clazz.getSimpleName();
        Field id = getIdField(clazz);
        id.setAccessible(true);
        List<Field> fields = getNotIdFields(clazz);

        String set = String.join(", ", fields.stream().map(field -> field.getName() + " = ?").toList());
        String sql = String.format("UPDATE %s SET %s WHERE %s = ?", tableName, set, id.getName());
        try(var statement = connection.prepareStatement(sql)){
            for(int i = 0; i < fields.size(); i++){
                fields.get(i).setAccessible(true);
                statement.setObject(i + 1, fields.get(i).get(objectData));
            }
            statement.setObject(fields.size() + 1, id.get(objectData));
            statement.executeUpdate();

        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public T load(Long clazzId, Class<T> clazz){
        String tableName = clazz.getSimpleName();
        Field id = getIdField(clazz);

        String sql = String.format("SELECT * FROM %s WHERE %s = ?", tableName, id.getName());
        try(var statement = connection.prepareStatement(sql)) {
            statement.setObject(1, clazzId);
            var res = statement.executeQuery();
            if(!res.next()){
                return null;
            }
            T object = clazz.getDeclaredConstructor().newInstance();
            for(Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                field.set(object, res.getObject(field.getName()));
            }
            return object;
        } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Field> getNotIdFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        for(Field field : clazz.getDeclaredFields()){
            if(!field.isAnnotationPresent(Id.class)){
                fields.add(field);
            }
        }
        return fields;

    }


    private Field getIdField(Class<?> clazz) {
        for(Field field : clazz.getDeclaredFields()) {
            if(field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        throw new RuntimeException("аннотация id не найдена");
    }
}
