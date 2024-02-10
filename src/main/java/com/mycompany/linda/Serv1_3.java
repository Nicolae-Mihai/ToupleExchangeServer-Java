/* If the primary server is down, the replica will recieve the operations 
 *  intended for the primary server and it will keep the system functional.
 * The first server is managing touple operations that have a length of 1-3
 * This exercise consists in creating a Coordonation Distributed system LINDA.
 * The system must include:
 *   * PostNote (PN)
 *   * RemoveNote (RN)
 *   * ReadNote (ReadN) 
 * A template will be a special touple type in which variables can appear, only 
 *  with RN or ReadN operations.Example:
 * ["John","20","Maried"] is a simple 3 element touple
 *  ["John","?X","?Y"] is a simple 3 element touple with 2 variables,"X" and "Y".
 * Internaly in every one of these servers the touples are stored in memory. 
 */
package com.mycompany.linda;

/**
 *
 * @author gusti
 */
public class Serv1_3 {
    
}
