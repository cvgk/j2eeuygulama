package londonmet.cs.j2ee;

import java.io.Serializable;

//ADD Persistence API
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table (name = "STUDENTMARK")
public class StudentMark implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ADD Id and Column annotations for OR mapping of the fields to columns
	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "MARKID")
    private int id;
	@Column (name = "STUDENTID")
    private String studentId;
	@Column (name = "MODULECODE")
    private String moduleCode;
	@Column (name = "MARK")
    private int markValue;

    public StudentMark(int id, String studentId, String moduleCode, int markValue) {
    	this.id = id;
        this.studentId = studentId;
        this.moduleCode = moduleCode;
        this.markValue = markValue;
    }

  // ADD zero-argument constructor
    public StudentMark() {
        this.id = 0;
        this.studentId = null;
        this.moduleCode = null;
        this.markValue = 0;
    }
    
    public int getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public int getMarkValue() {
        return markValue;
    }

    public void setMarkValue(int markValue) {
        this.markValue = markValue;
    }
}
