/*
* File: OscarSelect.java
* Author: Király Péter
* Copyright: 2023, Király Péter
* Group: Szoft 1/2/E
* Date: 2023-05-13
* Github: https://github.com/KiralyPeter/OscarSelect.git
* Licenc: GNU GPL
*/

public class OscarSelect {
    public static void main(String[] args) throws Exception {
        DbQuery testSelect = new DbQuery(
            "jdbc:mariadb://localhost/",
            "oscar", 
            "", 
            "");

       
        try {
            switch(args[0]){
                case "1": 
                System.out.println("\n1. feladat: Készítsen listát az összes filmről a címük és elnyert díjaik számának feltüntetésével! A listát");
                System.out.println("rendezze a jelölések száma szerint csökkenő rendbe, ezen belül cím szerint névsorba!\n");
                System.out.println(">>A lekérdezést díj és cím szerint rendeztem sorba, mivel a feladat első felében a cím és >díj< szerepel.<<\n");
                testSelect.dbQuery("SELECT cim, dij FROM filmek ORDER by dij DESC, cim;");
                break;

                case "2": 
                System.out.println("\n2. feladat: Listázza ki az 1950 után legalább 3 Oscar-díjjal jutalmazott filmek címét, a díjak és jelölések");
                System.out.println("számát! A listát rendezze a filmek címe szerint névsorba!\n");
                testSelect.dbQuery("SELECT cim, dij, jelol FROM filmek WHERE ev >= 1950 AND dij >= 3 ORDER by cim;");
                break;
                
                case "3": 
                System.out.println("\n3. feladat: Készítsen listát az 5 legtöbb jelölést kapott film címéről, a díjazás évéről!\n");
                testSelect.dbQuery("SELECT cim, ev FROM filmek ORDER by jelol DESC LIMIT 5;");
                break;
                
                case "4": 
                System.out.println("\n4. feladat: Készítsen listát az 2000 után 5 legtöbb díjat kapott film címéről és a díjak számáról! Kezelje az esetleges holtversenyt!\n");
                testSelect.dbQuery("SELECT cim, dij FROM filmek WHERE ev >= 2000 ORDER by dij DESC, cim LIMIT 5;");
                break;
                
                case "5": 
                System.out.println("\n5. feladat: Listázza ki az összes olyan film adatát, amely címében szerepel a King szó vagy szórészlet!");
                System.out.println("Jelenítse meg a film címét, a jelölések és az elnyert díjak számát! Rendezze a listát a");
                System.out.println("jelölések, ezen belül a díjak száma szerint csökkenő sorrendbe!\n");
                testSelect.dbQuery("SELECT cim, jelol, dij FROM filmek WHERE cim LIKE \"%King%\" ORDER by jelol DESC, dij DESC;");
                break;
                
                case "6": 
                System.out.println("\n6. feladat: Listázza ki azoknak a filmeknek minden adatát, amelyek minden jelölést megnyertek! A");
                System.out.println("listát rendezze év szerint, ezen belül cím szerint növekvő sorrendbe!\n");
                testSelect.dbQuery("SELECT cim, ev, jelol, dij FROM filmek WHERE jelol = dij ORDER BY ev, cim;");
                break;
                
                case "7": 
                System.out.println("\n7. feladat: Listázza ki a II. világháború alatt legalább 3 Oscar-díjat kapott filmek címét és a díjazás évét!");
                System.out.println("A listát rendezze a díjazás éve, ezen belül a film címe szerint növekvő sorrendbe!\n");
                testSelect.dbQuery("SELECT cim, ev FROM filmek WHERE ev BETWEEN 1939 AND 1945 ORDER BY ev, cim;");
                break;
                
                case "8": 
                System.out.println("\n8. feladat: Listázza ki azokat a filmeket, amelyek az Ön vagy édesanyja születési évében kaptak több,");
                System.out.println("mint 4 jelölést! Jelenítse meg a filmek címét névsorban! Kezelje az esetleges holtversenyt!\n");
                testSelect.dbQuery("SELECT cim, ev FROM filmek WHERE ev = 1949 AND jelol >= 4 ORDER BY ev, cim;");
                break;
                
                case "9": 
                System.out.println("\n9. feladat: Készítsen listát azokról a filmcímekről, melyek a The szóval kezdődnek! Rendezze a listát");
                System.out.println("névsorba!\n");
                testSelect.dbQuery("SELECT cim FROM filmek WHERE cim LIKE \"The%\" ORDER by cim;");
                break;
                
                case "10": 
                System.out.println("\n10. feladat: Listázza ki azoknak a filmeknek minden adatát, melyek 1960 után készültek és minden");
                System.out.println("jelölést megnyertek! Rendezze a listát a jelölés és ev szerint csökkenő sorrendbe!\n");
                testSelect.dbQuery("SELECT cim, ev, jelol, dij FROM filmek WHERE jelol = dij AND ev >= 1960 ORDER BY jelol DESC, ev DESC;");
                break;
                
                default:
                System.out.println("Nem létező feladat szám: " + args[0]);
            }
        } catch (Exception e) {            
            System.out.println("Hiba, nem adott meg faldat számot!");
        }
        
        

            
            
        
    }

    
}
