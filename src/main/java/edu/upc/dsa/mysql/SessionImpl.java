package edu.upc.dsa.mysql;

import edu.upc.dsa.models.LogInParams;
import edu.upc.dsa.models.User;
import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
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
            int i = 1;

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
            //rs.next();
            ResultSetMetaData rsmd = rs.getMetaData();

            int numberOfColumns = rsmd.getColumnCount();

            Object o = theClass.newInstance();
            /*int i=1;
            while (i<=numberOfColumns)
            {
                ObjectHelper.setter(o, rsmd.getColumnName(i), rs.getObject(i));
                i++;
            }*/
            while (rs.next()){
                for (int i=1; i<=numberOfColumns; i++){
                    String columnName = rsmd.getColumnName(i);
                    ObjectHelper.setter(o, columnName, rs.getObject(i));
                }
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



    public void update(Class theClass, String SET, String valueSET, String WHERE, String valueWHERE) {
        String updateQuery = QueryHelper.createQueryUPDATE(theClass, SET, WHERE);
        //System.out.println(updateQuery);
        ResultSet rs;
        PreparedStatement pstm;

        //boolean empty = true;

        try {
            pstm = conn.prepareStatement(updateQuery);
            pstm.setObject(1, valueSET); //son los ?
            pstm.setObject(2, valueWHERE);
            pstm.executeQuery();
//            rs.next();
//            ResultSetMetaData rsmd = rs.getMetaData();
//
//            int numberOfColumns = rsmd.getColumnCount();
//
//            Object o = theClass.newInstance();
//            int i=1;
//            while (i<=numberOfColumns)
//            {
//                ObjectHelper.setter(o, rsmd.getColumnName(i), rs.getObject(i));
//                i++;
//            }
//            return o;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
//         catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
        //return null;

    }

    public void delete(Object object) {

    }

    @Override
    public int deleteUser(LogInParams logInParams) {
        String insertQuery = QueryHelper.createQueryDeleteUser(logInParams);
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.executeQuery();

            return 1;


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public void saveUser(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT_Encrypted(entity);

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
        String query = QueryHelper.createQuerySELECTAll(theClass);
        PreparedStatement pstm =null;
        ResultSet rs;
        List<Object> list = new LinkedList<>();
        try {
            pstm = conn.prepareStatement(query);
            pstm.executeQuery();
            rs = pstm.getResultSet();

            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();

            while (rs.next()){
                Object o = theClass.newInstance();
                for (int j=1; j<=numberOfColumns; j++){
                    String columnName = metadata.getColumnName(j);
                    ObjectHelper.setter(o, columnName, rs.getObject(j));
                }
                list.add(o);

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }

    public List<Object> findAll(Class theClass, HashMap params) {
        String query = QueryHelper.createQuerySelectWithP(theClass, params);
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(query);


            int i = 1;
            for(Object v : params.values()){
                pstm.setObject(i++, v);
            }


            pstm.executeQuery();

            ResultSet rs = pstm.getResultSet();

            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
            List<Object> l = new LinkedList<>();


            while (rs.next()){
                Object o = theClass.newInstance();
                for (int j=1; j<=numberOfColumns; j++){
                    String columnName = metadata.getColumnName(j);
                    ObjectHelper.setter(o, columnName, rs.getObject(j));
                }
                l.add(o);
            }


            return l;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }
    public Object getByUsername(Class theClass, String username) {
        String query = QueryHelper.createQuerySELECTbyUsername(theClass, username);
        ResultSet rs;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();


            Object o = theClass.newInstance();

            while (rs.next()){
                for (int i=1; i<=numberOfColumns; i++){
                    String columnName = metadata.getColumnName(i);

                    ObjectHelper.setter(o, columnName, rs.getObject(i));
                }
            }
            if(ObjectHelper.getter(o,"id") == null){
                return null;
            }

            return o;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }

    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
