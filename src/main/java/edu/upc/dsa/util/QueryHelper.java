package edu.upc.dsa.util;

import edu.upc.dsa.models.LogInParams;

import java.util.HashMap;
import java.util.Objects;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");
        Integer passwordPos = null;
        String [] fields = ObjectHelper.getFields(entity);

        //sb.append("ID");
        for (int i=0;i<fields.length;i++) {
            if (fields[i].equals("password")){
                passwordPos= i;
            }
            sb.append(fields[i]).append(", ");
        }
        sb.delete(sb.length()-2,sb.length());
        sb.append(") VALUES (");
        for (int i = 0; i< fields.length ;i++){
            if(passwordPos!=null && passwordPos == i){
                sb.append("MD5(?), ");
            }else {
                sb.append("?, ");
            }
        }
        sb.delete(sb.length()-2,sb.length());
        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECT(Class clase, String Where) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(clase.getSimpleName());
        sb.append(" WHERE ");
        sb.append(Where);
        sb.append(" = ?");

        return sb.toString();
    }
    public static String createQuerySELECTbyUsername(Class theClass, String username) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE ").append("username = '").append(username).append("'");

        return sb.toString();
    }

    public static String createQueryUPDATE(Class clase, String SET, String Where) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(clase.getSimpleName());
        if (Objects.equals(SET, "PASSWORD")){
            sb.append(" SET ").append(SET);
            sb.append(" = MD5(?) ");
            sb.append(" WHERE ");
            sb.append(Where);
            sb.append(" = ?");
        }
        else{
            sb.append(" SET ").append(SET);
            sb.append(" = ? ");
            sb.append(" WHERE ");
            sb.append(Where);
            sb.append(" = ?");
        }

        //UPDATE personas
        //SET apellido2 = 'RODRIGUEZ'
        //WHERE nombre = 'ANTONIO'

        return sb.toString();
    }


    public static String createQueryUPDATE2(Class clase, String SET, String Where, String Where2) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(clase.getSimpleName());
        if (Objects.equals(SET, "PASSWORD")){
            sb.append(" SET ").append(SET);
            sb.append(" = MD5(?) ");
            sb.append(" WHERE ");
            sb.append(Where);
            sb.append(" = ?");
        }
        else{
            sb.append(" SET ").append(SET);
            sb.append(" = ? ");
            sb.append(" WHERE ");
            sb.append(Where);
            sb.append(" = ? ");
            sb.append(" AND ");
            sb.append(Where2);
            sb.append(" = ?");
        }

        //UPDATE personas
        //SET apellido2 = 'RODRIGUEZ'
        //WHERE nombre = 'ANTONIO'

        return sb.toString();
    }

    public static String createQuerySELECTAll(Class clase){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(clase.getSimpleName());
        return sb.toString();
    }

    public static String createQuerySelectWithP (Class clase, HashMap<String, Object> parameters){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(clase.getSimpleName());
        sb.append(" WHERE (");

        parameters.forEach((k,v) ->{
            //k = k.substring(0, 1).toUpperCase() + k.substring(1);
            if(k.equals("password")){
                sb.append(k).append(" = MD5(").append("?").append(") AND ");
            }else {
                sb.append(k).append(" = ").append("?").append(" AND ");
            }
        });
        sb.delete(sb.length()-4, sb.length()-1);
        sb.append(")");

        return sb.toString();
    }





    public static String createQueryUserExists(Class class1){
        StringBuffer sb = new StringBuffer("SELECT ID FROM ");
        sb.append(class1.getSimpleName()).append(" ");
        return sb.toString();
    }
    public static String createQueryINSERT_Encrypted(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        sb.append(fields[0]);
        Integer passwordPos = null;
        for (int i = 1; i< fields.length ;i++){
            if(fields[i].equals("password"))
                passwordPos = i;
            sb.append(", ").append(fields[i]);

        }

        sb.append(") VALUES (?");

        for (int i = 1; i< fields.length ;i++){
            if(passwordPos!=null && passwordPos == i){
                sb.append(", MD5(?)");
            }else {
                sb.append(", ?");
            }
        }

        sb.append(")");

        return sb.toString();
    }
    public static String createQueryDELETE(Object object) {
        /*StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ");
        sb.append(object.getClass().getSimpleName()).append(" ");
        sb.append(" WHERE ").append("username = '").append(logInParams.username).append("'");
        return sb.toString();*/
        return null;
    }
    public static String createQueryDeleteUser(String username) {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM User ");
        sb.append(" WHERE ").append("username = '").append(username).append("'");
        return sb.toString();
    }
}
