/*
Name: Jing Sheng Moey 
DNumber : D00217017
Class: SD2A
 */
package PartB;

import CA6.*;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * D00217017 Jing Sheng Moey 
 * SD2A
 */
public class Utilities
{

    /**
     *
     */
    public static String INPUT_MISS_MATCH = "Input doesn't Match!";

    /**
     *
     */
    public static String ERROR_MESSAGE = "Something went wrong, pls try again!";

    /**
     *
     */
    public static Scanner input = new Scanner(System.in);

    /**
     * ask user to enter a integer check if it's valid or in range
     *
     * @param min minimum value of the range
     * @param max maximum value of the range
     * @param msg Message that ask user to input
     * @return a valid integer in range of min and max
     */
    public static int getValidInt(int min, int max, String msg)
    {
        int user_input = -1;
        boolean isValidInput = false;
        while (isValidInput != true)
        {
            try
            {
                System.out.print(msg + "> ");
                user_input = input.nextInt();
                input.nextLine();
                while ((user_input < min) || (user_input > max))
                {
                    System.out.print("Invalid - [" + min + "," + max + "] > ");
                    user_input = input.nextInt();
                    input.nextLine();
                }
                isValidInput = true;
            } catch (InputMismatchException e)
            {
                input.nextLine();
                System.out.println(INPUT_MISS_MATCH);
            }
        }
        return user_input;
    }

    /**
     * ask user to enter a integer check if it's valid or in range
     *
     * @param min minimum value of the range
     * @param msg Message that ask user to input
     * @return a valid integer in no lesser than min
     */
    public static int getValidInt(int min, String msg)
    {
        int user_input = -1;
        boolean isValidInput = false;
        while (isValidInput != true)
        {
            try
            {
                System.out.print(msg + "> ");
                user_input = input.nextInt();
                input.nextLine();
                while (user_input < min)
                {
                    System.out.println("Invalid, Pls Enter at lease more than " + min);
                    user_input = input.nextInt();
                    input.nextLine();
                }
                isValidInput = true;
            } catch (InputMismatchException e)
            {
                input.nextLine();
                System.out.println(INPUT_MISS_MATCH);
            }
        }
        return user_input;
    }

    /**
     * ask user to enter a double check if it's valid
     *
     * @param min minimum value of the range
     * @param max maximum value of the range
     * @param msg Message that ask user to input
     * @return a valid integer in no lesser than min
     */
    public static double getValidDouble(double min, double max, String msg)
    {
        boolean isValidInput = false;
        double user_input = 0;
        while (isValidInput != true)
        {
            try
            {
                System.out.println(msg);
                user_input = input.nextDouble();
                input.nextLine();
                while ((user_input < min) || (user_input > max))
                {
                    System.out.println("Invalid - [" + min + "," + max + "] only");
                    user_input = input.nextDouble();
                    input.nextLine();
                }
                isValidInput = true;
            } catch (InputMismatchException e)
            {
                input.nextLine();
                System.out.println(INPUT_MISS_MATCH);
            }
        }
        return user_input;
    }

    /**
     * ask user to input T/F to return a boolean status with passing in message
     *
     * @return a valid boolean either true of false
     */
    public static boolean getValidBoolean()
    {
        System.out.println("Input T/F");
        String user_input = input.nextLine();

        while (!user_input.toLowerCase().matches("[t|f|T|F]"))
        {
            System.out.println("Input T/F");
            user_input = input.nextLine();
        }
        if (user_input.equals("t"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * ask user to input T/F to return a boolean status
     *
     * @param msg Message that ask user to input
     * @return a valid boolean
     */
    public static boolean getValidBoolean(String msg)
    {
        boolean validated_Boolean = false;
        System.out.println(msg);
        String user_input = input.nextLine().toLowerCase();

        while (!user_input.matches("y|n"))
        {
            System.out.println(msg);
            user_input = input.nextLine().toLowerCase();
        }
        if (user_input.equals("y"))
        {
            validated_Boolean = true;
        }
        else
        {
            validated_Boolean = false;
        }
        return validated_Boolean;
    }

    /**
     *
     * @param msg 
     * @param validation
     * @return boolean of true and false
     */
    public static boolean getValidBoolean(String msg, String validation)
    {
        boolean validated_Boolean = false;
        System.out.println(msg);
        String user_input = input.nextLine();

        while (!user_input.matches(validation))
        {
            System.out.println(msg);
            user_input = input.nextLine();
        }
        if (user_input.toLowerCase().startsWith("y"))
        {
            validated_Boolean = true;
        }
        else
        {
            validated_Boolean = false;
        }
        return validated_Boolean;
    }

    /**
     * system key in t to return true else everything will return false, use for
     * system load in file
     *
     * @param b t/f to return true and false
     * @return true or false
     */
    public static boolean getBoolean(String b)
    {
        if (b.toLowerCase().startsWith("t"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     *
     * @param msg Message that ask user to input
     * @param validation validation passing in
     * @return a valid string matches the validation that user pass in
     */
    public static String getValidString(String msg, String validation)
    {
        System.out.println(msg);
        String user_input = input.nextLine();
        while (user_input.length() == 0 || !user_input.matches(validation))
        {
            System.out.println(msg);
            user_input = input.nextLine();
        }
        return user_input;
    }

    /**
     *
     * @param msg
     * @return
     */
    public static String getValidString(String msg)
    {
        System.out.println(msg);
        String user_input = input.nextLine();
        while (user_input.length() == 0)
        {
            System.out.println(msg);
            user_input = input.nextLine();
        }
        return user_input;
    }

    /**
     * print a symbol with a specific amount that pass in
     *
     * @param symbol the symbol pass in to print
     * @param numberOfSymbol the number of symbol print
     */
    public static void printLine(String symbol, int numberOfSymbol)
    {
        for (int i = 0; i < numberOfSymbol; i++)
        {
            System.out.print(symbol);
        }
        System.out.println("");
    }

    /**
     *
     * @param symbol
     * @param numberOfSymbol
     * @return
     */
    public static String getStringLine(String symbol, int numberOfSymbol)
    {
        String line = "";
        for (int i = 0; i < numberOfSymbol; i++)
        {
            line += symbol;
        }
        return line;
    }

    /**
     * Returns true if url is valid
     *
     * @param url String URL being passed in
     * @return
     */
    public static boolean isUrlValid(String url)
    {
        /* Try creating a valid URL */
        try
        {
            new URL(url).toURI();
            return true;
        } // If there was an Exception 
        // while creating URL object 
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     *
     */
    public static void awaitForEnter()
    {
        System.out.print("Press Enter to Continue.");
        input.nextLine();
    }

    /**
     * Get valid time stamp
     * @param msg msg being passed in 
     * @return a valid timestamp
     */
    public static Timestamp getValidSQLTimeStamp(String msg)
    {
        Timestamp ts = null;
        boolean valid = false;
        boolean running = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd['T'][ ]HH:mm:ss['Z'][]");
        while (valid != true && running != false)
        {
            System.out.println(msg); // "Enter date and time(yyyy-mm-dd hour-m-s)"
            String time = input.nextLine();
            if (time.equalsIgnoreCase("q"))
            {
                running = false;
            }
            else
            {
                try
                {
                    ts = Timestamp.valueOf(LocalDateTime.parse(time, formatter));
                    valid = true;
                } catch (IllegalArgumentException e)
                {
                    System.out.println("Date and Time Format incorrect!");
                } catch (Exception e)
                {
                    System.out.println("Something went wrong");
                }
            }
        }
        return ts;
    }
}
