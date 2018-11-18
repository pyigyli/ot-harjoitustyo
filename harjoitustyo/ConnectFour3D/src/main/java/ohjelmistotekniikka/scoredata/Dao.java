
package ohjelmistotekniikka.scoredata;

import java.sql.*;
import java.util.*;

public interface Dao<T, K> {

    T findOne(K key) throws SQLException;

    List<T> findAll() throws SQLException;
    
    void save(T object) throws SQLException;
    
    void update(T object) throws SQLException;

    void delete(K key) throws SQLException;
}