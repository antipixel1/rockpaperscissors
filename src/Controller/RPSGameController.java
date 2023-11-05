/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import RPSGame.RPSGameWindow;
/**
 *
 * @author Derek
 */
public class RPSGameController extends Thread {
    RPSGameWindow window;
    public boolean gameOver = false;
    public RPSGameController(RPSGameWindow window){
        this.window = window;
    }
    public void run(){
        try {
            while(!gameOver){
                window.controlSim();
                sleep(1000);
            }
        } catch (Exception e) {
            System.err.println("Error en la ejecucion del juego");
        }
    
    }//fin del metodo run
}
