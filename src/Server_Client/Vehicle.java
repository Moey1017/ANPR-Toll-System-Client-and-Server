/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_Client;

import java.util.Objects;

/**
 *
 * D00217017 Jing Sheng Moey 
 * SD2A
 */
public class Vehicle
{
    private String registrationNumber;

    /**
     *
     * @param registrationNumber
     */
    public Vehicle(String registrationNumber)
    {
        this.registrationNumber = registrationNumber;
    }

    /**
     *
     * @return
     */
    public String getRegistrationNumber()
    {
        return registrationNumber;
    }

    /**
     *
     * @param registrationNumber
     */
    public void setRegistrationNumber(String registrationNumber)
    {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString()
    {
        return "Vehicle{" + "registrationNumber=" + registrationNumber + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.registrationNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Vehicle other = (Vehicle) obj;
        if (!Objects.equals(this.registrationNumber, other.registrationNumber))
        {
            return false;
        }
        return true;
    }
    
}
