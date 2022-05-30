package edu.upc.dsa.mysql;

import edu.upc.dsa.models.User;
import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;

import java.sql.*;
import java.util.HashMap;
import java.util.List;


public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.setObject(1, 0);
            int i = 2;

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object get(Class theClass, String key, Object value) {

        String selectQuery =  QueryHelper.createQuerySELECT(theClass, key);
        // "Select * from User WHERE username = ?"

        ResultSet rs;
        PreparedStatement pstm;

        boolean empty = true;

        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, value); //son los ?
            rs = pstm.executeQuery();
            rs.next();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();

            Object o = theClass.newInstance();
            int i=1;
            while (i<=numberOfColumns)
            {
                ObjectHelper.setter(o, rsmd.getColumnName(i), rs.getObject(i));
                i++;
            }
            return o;


        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Object getS(Class theClass, String username) {
        return null;
    }

    public void update(Object object) {

    }

    public void delete(Object object) {

    }

    @Override
    public boolean isUserRegistered(Class class1,User user) {
        String selectQuery = QueryHelper.createQueryUserExists(class1);

        ResultSet rs;
        PreparedStatement pstm;

        boolean empty = true;

        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, user.getUsername());
            rs = pstm.executeQuery();
            while(rs.next()) {
                empty = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(empty == true)
            return false;
        else
            return true;
    }

    public List<Object> findAll(Class theClass) {
        return null;
    }

    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
