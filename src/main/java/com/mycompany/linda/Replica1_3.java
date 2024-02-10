/*
 * Let's suppose that the server managing the 1-3 touple length needs an extra 
 * security level,given how we don't want to lose the stored data, in order to 
 * avoid losing data we need to create a replica server
 * if the primary server 
 * Is down, the replica will recieve the operations intended for the primary 
 * server and it will keep the system functional.
 */
package com.mycompany.linda;

/**
 *
 * @author gusti
 */
public class Replica1_3 {
    
}
