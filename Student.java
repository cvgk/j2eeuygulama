package londonmet.cs.j2ee;

import java.io.Serializable;
// ADD Persistence API
import javax.persistence.*;

//ADD Entity and Table annotation for OR mapping of the class to a table
@Entity @Table (name = "STUDENT")
public class Student implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ADD Id and Column annotations for OR mapping of the fields to columns
	@Id
	@Column (name = "ID")
    private String id;
    @Column (name="NAME")
    private String name;
    @Column (name="ADDR")
    private String addr;

    // Constructors
    public Student(String id, String name, String addr) {
        this.id = id;
        this.name = name;
        this.addr = addr;
    }

    public Student() {
        this(null, null, null);
    }

    // Accesser methods
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    // Mutator methods - note you cannot change the id
    public void setName(String newName) {
        name = newName;
    }

    public void setAddr(String newAddr) {
        addr = newAddr;
    }

    public String toString() {
        return "Student:  " + id + "  " + name + "  " + addr;
    }
}

