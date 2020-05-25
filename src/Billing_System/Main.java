/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Billing_System;

import Exceptions.DaoException;
import java.util.ArrayList;

/**
 *
 * D00217017 Jing Sheng Moey SD2A
 */
public class Main
{

    /**
     * Main app 
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            TollBillingSystem bs = new TollBillingSystem();
            bs.run();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

}
