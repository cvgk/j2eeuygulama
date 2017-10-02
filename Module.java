package londonmet.cs.j2ee;

import java.io.Serializable;
//ADD Persistence API
import javax.persistence.*;

//ADD Entity and Table annotation for OR mapping of the class to a table
@Entity @Table (name = "MODULE")
public class Module implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ADD Id and Column annotations for OR mapping of the fields to columns
	@Id
	@Column (name = "CODE")
    private String code;
	@Column (name = "NAME")
    private String name;
	@Column (name = "CREDIT")
    private int mark;
    
    public Module (String code, String name, int mark) {
        this.code = code;
        this.name = name;
        this.mark = mark;
    }

    
// ADD zero-argument constructor
    public Module() {
    	this(null,null,0);
        
    }
    
    // Methods to return the private values of this object
    public int getMark() {
        return mark;
    }

    public String getCode() {
        return code;
    }

    public void setMark(int newMark) {
        mark = newMark;
    }

    public String toString() {
        return "Module:  " + code + "  " + mark;
    }
}
