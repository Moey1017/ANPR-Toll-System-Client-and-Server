/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA6;

/**
 *
 * D00217017 Jing Sheng Moey 
 * SD2A
 */
public class MainApp
{
    public static void main(String[] args)
    {
        try
        {
            TollSystem tollSystem = new TollSystem();
            tollSystem.run();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
