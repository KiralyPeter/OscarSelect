/*
* File: DbQuery.java
* Author: Király Péter
* Copyright: 2023, Király Péter
* Group: Szoft 1/2/E
* Date: 2023-05-13
* Github: https://github.com/KiralyPeter/OscarSelect.git
* Licenc: GNU GPL
*/

import java.sql.*;
import java.lang.ClassNotFoundException;

public class DbQuery {
    private String url;    
    private String database;
    private String user;
    private String password;
    private Connection connection;

    public DbQuery(String url, String database, String user, String password) {
        this.url = url;
        this.database = database;
        this.user = user;
        this.password = password;
        this.connection = null;
    

    try {    
        Class.forName("org.mariadb.jdbc.Driver");        
    } catch (ClassNotFoundException ex) {
        System.out.println("Nem találom a driver-t!"+ ex);
    }
    
    try { 
        this.connection = DriverManager.getConnection(url+database, user, password);

    } catch (SQLException ex) {
        System.out.println("Hiba az adatbázis csatlakozásnban!"+ ex);
    }
    
    if (connection !=null)
    {
        System.out.println("Sikeres kapcsolódás az adatbázishoz.");
    }
}

public void dbQuery(String sqlQuery){
    
    
    if (connection!= null)
    {
        Statement createStatement = null;        
        ResultSet rs= null;
        String sql = sqlQuery;
        // String sql="SELECT cim, ev, jelol, dij FROM filmek WHERE jelol = dij AND ev >= 1960 ORDER BY jelol DESC, ev DESC;";
        
        try {
            createStatement = connection.createStatement();
        } catch (SQLException ex) {
           System.out.println("Baj van! Hiba a Statement létrehozásában!"+ ex);
        }
        
        if (createStatement!=null)
        {
            try {
                rs=createStatement.executeQuery(sql);
                int counter = 0;

                ResultSetMetaData metaData = rs.getMetaData();
                int columNumber = metaData.getColumnCount();
                for (int i = 1; i<=columNumber; i++){
                    System.out.printf("%-50s", metaData.getColumnName(i));
                }
                System.out.printf("\n");
                for (int i = 1; i<=columNumber; i++){
                    System.out.printf("%-50s", "-------------------------------");
                }
                System.out.printf("\n");
  
               
                while(rs.next()){
                // "azon";"cim";"ev";"dij";"jelol"
                
                    for (int i = 1; i<=columNumber; i++){
                        System.out.printf("%-50s", rs.getString(i));
                    }

                    System.out.printf("\n");            
                    counter = counter + 1;
                }
                System.out.println("\nTalálatok száma: " + counter);
                
            } catch (SQLException ex) {
               System.out.println("Hiba a Query futtatásánál!"+ ex);
            }
        }
    }
}
}
