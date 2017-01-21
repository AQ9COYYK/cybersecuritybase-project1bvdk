package sec.project.domain;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author bart
 */
@Entity

public class Event extends AbstractPersistable<Long> {
   private String name;
    private String address;
    //private Date date; 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Event() {
        super();
    }

    public Event(String name, String address) {
        this();
        this.name = name;
        this.address = address;
        //this.date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    /*public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }*/
    
    
}
