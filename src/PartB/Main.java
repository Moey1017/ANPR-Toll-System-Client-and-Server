/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PartB;

import Exceptions.DaoException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Main
{

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
