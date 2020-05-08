/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA6;

import java.util.Objects;
import java.time.Instant;

/**
 *
 * D00217017 Jing Sheng Moey 
 * SD2A
 */
public class TollEvent
{
    private String registration;
    private long    ImageID;
    private Instant timestamp;

    /**
     *
     * @param registration
     * @param ImageID
     * @param timestamp
     */
    public TollEvent(String registration, long ImageID, Instant timestamp)
    {
        this.registration = registration;
        this.ImageID = ImageID;
        this.timestamp = timestamp;
    }

    /**
     *
     * @param registration
     */
    public void setRegistration(String registration)
    {
        this.registration = registration;
    }

    /**
     *
     * @param ImageID
     */
    public void setImageID(long ImageID)
    {
        this.ImageID = ImageID;
    }

    /**
     *
     * @param timestamp
     */
    public void setTimestamp(Instant timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     */
    public String getRegistration()
    {
        return registration;
    }

    /**
     *
     * @return
     */
    public long getImageID()
    {
        return ImageID;
    }

    /**
     *
     * @return
     */
    public Instant getTimestamp()
    {
        return timestamp;
    }

    @Override
    public String toString()
    {
        return "TollEvent{" + "registration=" + registration + ", ImageID=" + ImageID + ", timestamp=" + timestamp + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.registration);
        hash = 23 * hash + (int) (this.ImageID ^ (this.ImageID >>> 32));
        hash = 23 * hash + Objects.hashCode(this.timestamp);
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
        final TollEvent other = (TollEvent) obj;
        if (this.ImageID != other.ImageID)
        {
            return false;
        }
        if (!Objects.equals(this.registration, other.registration))
        {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp))
        {
            return false;
        }
        return true;
    }
}
