/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.constants;

/**
 * Interfejs u okviru koga su definisane konstante koje su koriscene od strane servera.
 * @author Jelena Repac
 */
public interface ServerConstants {
    
    /**
     * Putanja do fajla dbconfig
     */
    public static final String DB_CONFIG_FILE_PATH = "./src/main/resources/dbconfig.properties";
    /**
     * Predstavlja kljuc za URL baze 
     */
    public static final String DB_CONFIG_URL = "url";
    /**
     * Predstavlja kljuc za username baze 
     */
    public static final String DB_CONFIG_USERNAME = "username";
    /**
     * Predstavlja kljuc za lozinku baze 
     */
    public static final String DB_CONFIG_PASSWORD = "password";
    /**
     * Putanja do fajla serverconfig
     */
    public static final String SERVER_CONFIG_FILE_PATH = "./src/main/resources/serverconfig.properties";
    /**
     * Predstavlja kljuc za port 
     */
    public static final String SERVER_CONFIG_PORT = "port";
}
 