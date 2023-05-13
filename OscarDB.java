import java.sql.*;
import java.lang.ClassNotFoundException;

public class OscarDB {
    private String url;    
    private String database;
    private String user;
    private String password;
    private Connection connection;

    public OscarDB(String url, String database, String user, String password) {
        this.url = url;
        this.database = database;
        this.user = user;
        this.password = password;
        this.connection = null;
    

    try {    
        Class.forName("org.mariadb.jdbc.Driver");        
    } catch (ClassNotFoundException ex) {
        System.out.println("Baj van! Nem találom a driver-t!"+ ex);
    }
    
    try { 
        this.connection = DriverManager.getConnection(url+database, user, password);

    } catch (SQLException ex) {
        System.out.println("Baj van! Hiba az adatbázis csatlakozásnban!"+ ex);
    }
    
    if (connection !=null)
    {
        System.out.println("Sikeresen kapcsolódtunk");
    }
}

    

    public void query1(){
        System.out.println("\n1. feladat: Készítsen listát az összes filmről a címük és elnyert díjaik számának feltüntetésével! A listát");
        System.out.println("rendezze a jelölések száma szerint csökkenő rendbe, ezen belül cím szerint névsorba!\n");
        if (connection!= null)
        {
            Statement createStatement = null;        
            ResultSet rs= null;
            String sql="SELECT cim AS CIM, dij AS OSCAR FROM filmek ORDER by OSCAR DESC, cim;";

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
                    
                    while(rs.next())
                    // "azon";"cim";"ev";"dij";"jelol"
                    {
                        System.out.println(
                                "Film címe: " +
                                rs.getString(1)+
                                "\n Elnyert Oscar díjak száma: "
                                +rs.getString(2)
                                                                
                                );
                        counter = counter + 1;
                    }
                    System.out.println(
                        "\nTalálatok száma: "
                        +counter);
                    
                } catch (SQLException ex) {
                   System.out.println("Baj van! Hiba a Query futtatásánál!"+ ex);
                }
            }
        }
    }

    public void query2(){
        System.out.println("\n2. feladat: Listázza ki az 1950 után legalább 3 Oscar-díjjal jutalmazott filmek címét, a díjak és jelölések");
        System.out.println("számát! A listát rendezze a filmek címe szerint névsorba!\n");
        if (connection!= null)
        {
            Statement createStatement = null;        
            ResultSet rs= null;
            String sql="SELECT cim AS CIM, dij AS OSCAR, jelol AS JELOLESEK FROM filmek WHERE ev >= 1950 AND dij >= 3 ORDER by cim;";

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
                    
                    while(rs.next())
                    // "azon";"cim";"ev";"dij";"jelol"
                    {
                        System.out.println(
                                "Film címe: " +
                                rs.getString(1)+
                                "\n Elnyert Oscar díjak száma: "
                                +rs.getString(2)+
                                "\n Jelölések száma: "
                                +rs.getString(3)
                                                                
                                );
                        counter = counter + 1;
                    }
                    System.out.println(
                        "\nTalálatok száma: "
                        +counter);
                    
                } catch (SQLException ex) {
                   System.out.println("Baj van! Hiba a Query futtatásánál!"+ ex);
                }
            }
        }
    }

    public void query3(){
        System.out.println(
            "\n3. feladat: Készítsen listát az 5 legtöbb jelölést kapott film címéről, a díjazás évéről!\n");
        if (connection!= null)
        {
            Statement createStatement = null;        
            ResultSet rs= null;
            String sql="SELECT cim AS CIM, ev as EV FROM filmek ORDER by jelol DESC LIMIT 5;";

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
                    
                    while(rs.next())
                    // "azon";"cim";"ev";"dij";"jelol"
                    {
                        System.out.println(
                                "Film címe: " +
                                rs.getString(1)+
                                "\n Díjazás éve: "
                                +rs.getString(2)
                               
                                                                
                                );
                        counter = counter + 1;
                    }
                    System.out.println(
                        "\nTalálatok száma: "
                        +counter);
                    
                } catch (SQLException ex) {
                   System.out.println("Baj van! Hiba a Query futtatásánál!"+ ex);
                }
            }
        }
    }

    public void query4(){
        System.out.println(
            "\n4. feladat: Készítsen listát az 2000 után 5 legtöbb díjat kapott film címéről és a díjak számáról! Kezelje az esetleges holtversenyt!\n");
        if (connection!= null)
        {
            Statement createStatement = null;        
            ResultSet rs= null;
            String sql="SELECT cim AS CIM, dij AS DIJAK_SZAMA FROM filmek WHERE ev >= 2000 ORDER by dij DESC, cim LIMIT 5;";

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
                    
                    while(rs.next())
                    // "azon";"cim";"ev";"dij";"jelol"
                    {
                        System.out.println(
                                "Film címe: " +
                                rs.getString(1)+
                                "\n Díjak száma: "
                                +rs.getString(2)
                               
                                                                
                                );
                        counter = counter + 1;
                    }
                    System.out.println(
                        "\nTalálatok száma: "
                        +counter);
                    
                } catch (SQLException ex) {
                   System.out.println("Baj van! Hiba a Query futtatásánál!"+ ex);
                }
            }
        }
    }

    public void query5(){
        System.out.println("\n5. feladat: Listázza ki az összes olyan film adatát, amely címében szerepel a King szó vagy szórészlet!");
        System.out.println("Jelenítse meg a film címét, a jelölések és az elnyert díjak számát! Rendezze a listát a");
        System.out.println("jelölések, ezen belül a díjak száma szerint csökkenő sorrendbe!\n");
        if (connection!= null)
        {
            Statement createStatement = null;        
            ResultSet rs= null;
            String sql="SELECT cim AS CIM, jelol AS JELOLESEK_SZAMA, dij AS DIJAK_SZAMA FROM filmek WHERE cim LIKE \"%King%\" ORDER by jelol DESC, dij DESC;";

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
                    
                    while(rs.next())
                    // "azon";"cim";"ev";"dij";"jelol"
                    {
                        System.out.println(
                                "Film címe: " +
                                rs.getString(1)+
                                "\n Jelölések száma: "
                                +rs.getString(2)+
                                "\n Díjak száma: "
                                +rs.getString(3)
                               
                                                                
                                );
                        counter = counter + 1;
                    }
                    System.out.println(
                        "\nTalálatok száma: "
                        +counter);
                    
                } catch (SQLException ex) {
                   System.out.println("Baj van! Hiba a Query futtatásánál!"+ ex);
                }
            }
        }
    }

    public void query6(){
        System.out.println("\n6. feladat: Listázza ki azoknak a filmeknek minden adatát, amelyek minden jelölést megnyertek! A");
        System.out.println("listát rendezze év szerint, ezen belül cím szerint növekvő sorrendbe!\n");
        
        if (connection!= null)
        {
            Statement createStatement = null;        
            ResultSet rs= null;
            String sql="SELECT cim, ev, jelol, dij FROM filmek WHERE jelol = dij ORDER BY ev, cim;";

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
                    
                    while(rs.next())
                    // "azon";"cim";"ev";"dij";"jelol"
                    {
                        System.out.println(
                                "Film címe: " +
                                rs.getString(1)+
                                "\n Jelölés éve: "
                                +rs.getString(2)+
                                "\n Jelölések száma: "
                                +rs.getString(3)+
                                "\n Díjak száma: "
                                +rs.getString(4)
                               
                                                                
                                );
                        counter = counter + 1;
                    }
                    System.out.println(
                        "\nTalálatok száma: "
                        +counter);
                    
                } catch (SQLException ex) {
                   System.out.println("Baj van! Hiba a Query futtatásánál!"+ ex);
                }
            }
        }
    }

    public void query7(){
        System.out.println("\n7. feladat: Listázza ki a II. világháború alatt legalább 3 Oscar-díjat kapott filmek címét és a díjazás évét!");
        System.out.println("A listát rendezze a díjazás éve, ezen belül a film címe szerint növekvő sorrendbe!\n");
        
        if (connection!= null)
        {
            Statement createStatement = null;        
            ResultSet rs= null;
            String sql="SELECT cim, ev FROM filmek WHERE ev BETWEEN 1939 AND 1945 ORDER BY ev, cim;";

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
                    
                    while(rs.next())
                    // "azon";"cim";"ev";"dij";"jelol"
                    {
                        System.out.println(
                                "Film címe: " +
                                rs.getString(1)+
                                "\n Jelölés éve: "
                                +rs.getString(2)
                                
                               
                                                                
                                );
                        counter = counter + 1;
                    }
                    System.out.println(
                        "\nTalálatok száma: "
                        +counter);
                    
                } catch (SQLException ex) {
                   System.out.println("Baj van! Hiba a Query futtatásánál!"+ ex);
                }
            }
        }
    }

    public void query8(){
        System.out.println("\n8. feladat: Listázza ki azokat a filmeket, amelyek az Ön vagy édesanyja születési évében kaptak több,");
        System.out.println("mint 4 jelölést! Jelenítse meg a filmek címét névsorban! Kezelje az esetleges holtversenyt!\n");
        
        if (connection!= null)
        {
            Statement createStatement = null;        
            ResultSet rs= null;
            String sql="SELECT cim, ev FROM filmek WHERE ev = 1949 AND jelol >= 4 ORDER BY ev, cim;";

            try {
                createStatement = connection.createStatement();
            } catch (SQLException ex) {
               System.out.println("Baj van! Hiba a Statement létrehozásában!"+ ex);
            }
            
            if (createStatement!=null)
            {
                try {
                    rs=createStatement.executeQuery(sql);
                    // ResultSetMetaData rsmd = rs.getMetaData();
                    // int coulums = rsmd.getColumnCount();                    
                    // System.out.println("Colums: " + coulums);
                    int counter = 0;
                    
                    while(rs.next())
                    // "azon";"cim";"ev";"dij";"jelol"
                    {
                        System.out.println(
                                "Film címe: " +
                                rs.getString(1)+
                                "\n Jelölés éve: "
                                +rs.getString(2)
                                
                                
                               
                                                                
                                );
                        counter = counter + 1;
                    }
                    System.out.println(
                        "\nTalálatok száma: "
                        +counter);
                    
                } catch (SQLException ex) {
                   System.out.println("Baj van! Hiba a Query futtatásánál!"+ ex);
                }
            }
        }
    }

    public void query9(){
        System.out.println("\n9. feladat: Készítsen listát azokról a filmcímekről, melyek a The szóval kezdődnek! Rendezze a listát");
        System.out.println("névsorba!\n");
        
        if (connection!= null)
        {
            Statement createStatement = null;        
            ResultSet rs= null;
            String sql="SELECT cim FROM filmek WHERE cim LIKE \"The%\" ORDER by cim;";

            try {
                createStatement = connection.createStatement();
            } catch (SQLException ex) {
               System.out.println("Baj van! Hiba a Statement létrehozásában!"+ ex);
            }
            
            if (createStatement!=null)
            {
                try {
                    rs=createStatement.executeQuery(sql);
                    // ResultSetMetaData rsmd = rs.getMetaData();
                    // int coulums = rsmd.getColumnCount();                    
                    // System.out.println("Colums: " + coulums);
                    int counter = 0;
                    
                    while(rs.next())
                    // "azon";"cim";"ev";"dij";"jelol"
                    {
                        System.out.println(
                                "Film címe: " +
                                rs.getString(1)
                                
                                
                               
                                                                
                                );
                        counter = counter + 1;
                    }
                    System.out.println(
                        "\nTalálatok száma: "
                        +counter);
                    
                } catch (SQLException ex) {
                   System.out.println("Baj van! Hiba a Query futtatásánál!"+ ex);
                }
            }
        }
    }

    public void query10(){
        System.out.println("\n10. feladat: Listázza ki azoknak a filmeknek minden adatát, melyek 1960 után készültek és minden");
        System.out.println("jelölést megnyertek! Rendezze a listát a jelölés és ev szerint csökkenő sorrendbe!\n");
        
        if (connection!= null)
        {
            Statement createStatement = null;        
            ResultSet rs= null;
            String sql="SELECT cim, ev, jelol, dij FROM filmek WHERE jelol = dij AND ev >= 1960 ORDER BY jelol DESC, ev DESC;";

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

                    

                    System.out.printf("%-50s", "Film címe");
                    System.out.printf("%30s", "Oscar jelölés éve");
                    System.out.printf("%30s", "Oscar jelölések száma");
                    System.out.printf("%30s", "Kapott Oscar díjak");
                    System.out.printf("\n");

                    System.out.printf("%-50s", "-------------------------------");
                    System.out.printf("%30s", "--------------------");
                    System.out.printf("%30s", "--------------------");
                    System.out.printf("%30s", "--------------------");
                    System.out.printf("\n");
                   
                    while(rs.next()){
                    // "azon";"cim";"ev";"dij";"jelol"
                    {
                        // System.out.printf(
                        //         "Film címe: " + "'%15s'", rs.getString(1)+
                        //         "\n Jelölés éve: "
                        //         +rs.getString(2)+
                        //         "\n Jelölések száma: "
                        //         +rs.getString(3)+
                        //         "\n Díjak száma: "
                        //         +rs.getString(4)
                               
                                                                
                        //         );

                                System.out.printf("%-50s", rs.getString(1));
                                System.out.printf("%30s", rs.getString(2));
                                System.out.printf("%30s", rs.getString(3));
                                System.out.printf("%30s", rs.getString(4));
                                System.out.printf("\n");
                    }
                               
                                                                
                                
                        counter = counter + 1;
                    }
                    System.out.println(
                        "\nTalálatok száma: "
                        +counter);
                    
                } catch (SQLException ex) {
                   System.out.println("Baj van! Hiba a Query futtatásánál!"+ ex);
                }
            }
        }
    }
}








