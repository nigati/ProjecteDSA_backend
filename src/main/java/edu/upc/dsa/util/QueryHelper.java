package edu.upc.dsa.util;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");
        Integer passwordPos = null;
        String [] fields = ObjectHelper.getFields(entity);

        sb.append("ID");
        for (int i=0;i<fields.length;i++) {
            if (fields[i].equals("password")){
                passwordPos= i;
            }
            sb.append(", ").append(fields[i]);
        }

        sb.append(") VALUES (?");

        for (int i = 0; i< fields.length ;i++){
            if(passwordPos!=null && passwordPos == i){
                sb.append(", MD5(?)");
            }else {
                sb.append(", ?");
            }
        }

        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

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

}
