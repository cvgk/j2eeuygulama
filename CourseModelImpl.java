package londonmet.cs.j2ee;

import java.util.*;

import javax.ejb.Local;
import javax.ejb.Singleton;

//ADD JPA  LIBRARIES IMPORTED
import javax.persistence.*;
//END OF IMPORT

@Local @Singleton
public class CourseModelImpl implements CourseModel {

//ADD Entity Manager
	@PersistenceContext
	private EntityManager emgr;

// REMOVING THE IN-MEMORY DATA
//    private List<Student> students = new ArrayList<Student>();
//    private List<StudentMark> marks = new ArrayList<StudentMark>();
//    private List<Module> modules = new ArrayList<Module>();
    
//EMPTYING THE DATA POPULATION IN THE CONSTRUCTOR
    public CourseModelImpl() {

    }

    // Student state change methods
    /**----------------------------------------------------------
     * Adds the Student to the course model
     */
    public void addStudent(Student stud)
            throws CourseException {
       /* REPLACING ADD OPERATION WITH JPA VERSION*/
		try {
			emgr.persist(stud);
		} catch (EntityExistsException ex) {
			throw new CourseException("Duplicate Id : " + stud.getId());
		}
    }

    /**-------------------------------------------------------------
     * deletes the student from the course model
     */
    public void deleteStudent(Student stud)
            throws CourseException {
    	/* REPLACE DEL OPERATION WITH JPA VERSION*/
		String id = stud.getId();
		stud = emgr.find(Student.class, id);
		if (stud == null) {
			throw new CourseException("Record for " + id + " not found");
		} else {
			emgr.remove(stud);
		}
    }

    /**-------------------------------------------------------------
     * Updates the student in the course model
     */
    public void updateStudent(Student stud)
            throws CourseException {
    	/* REPLACE UPDATE OPERATION WITH JPA VERSION*/
		Student st = emgr.find(Student.class, stud.getId());
		if (st == null) {
			throw new CourseException("Student : " + stud.getId()
					+ " not found");
		} else {
			try {
				emgr.merge(stud);
				} catch(OptimisticLockException ole) {
				throw new CourseException("Record for " + stud.getId() 
						+ " has been modified since retrieval");
				}
		}
    }

    // Student state query methods
    /**-------------------------------------------------------------
     * Given an id, returns the Student from the model
     */
    public Student getStudent(String id)
            throws CourseException {
    	/* REPLACE GET OPERATION WITH JPA OPERATION FIND*/
		Student stud = emgr.find(Student.class, id);
		if (stud != null) {
			return stud;
		} else {
			throw new CourseException("Student : " + id + " not found");
		}
    }

    /**-------------------------------------------------------------
     * Returns all students in the course model
     */
    @SuppressWarnings("unchecked")
	public Student[] getAllStudents()
            throws CourseException {
    	/* REPLACE THE ARRAY OF STUDENTS WITH THE RESULT OF THE SQL SELECT FROM STUDENTS TABLE */
    	Query query = emgr.createNativeQuery("SELECT * FROM STUDENT",	Student.class);
		List<Student> students = new ArrayList<Student>();
		students = (List<Student>) query.getResultList();
		return (Student[]) students.toArray(new Student[0]);
    }

    @SuppressWarnings("unchecked")
	public StudentMark[] getAllStudentMarks(String studentId) throws CourseException {
    	/* REPLACE THE LIST OF STUDENTMARK WITH THE RESULT OF SQL QUERY */ 
    	Query query = emgr.createNativeQuery("SELECT * FROM STUDENTMARK WHERE STUDENTID = '"+studentId +"'",StudentMark.class);
		List<StudentMark> marks = new ArrayList<StudentMark>();
		marks = (List<StudentMark>) query.getResultList();

		if (marks.size() == 0) {
			throw new CourseException("Marks for " + studentId + " not found");
		} else {
			return (StudentMark[]) marks.toArray(new StudentMark[0]);
		}
    }

    public void addStudentMark(StudentMark sm) throws CourseException {
    	/* REPLACE ADD STUDENT MARKS WITH JPA OPERATION PERSIST */
		StudentMark[] marks = getAllStudentMarks(sm.getStudentId());
		for (int i = 0; i < marks.length; i++) {
			if (sm.getModuleCode().equals(marks[i].getModuleCode())) {
				throw new CourseException("Duplicate Marks : "
						+ sm.getStudentId() + " " + sm.getStudentId());
			}
		}
		emgr.persist(sm);
    }

    public void updateStudentMark(StudentMark sm) throws CourseException {
    	/* REPLACE UPDATE MARKS WITH JPA OPERATION MERGE */
		StudentMark mark = emgr.find(StudentMark.class, sm.getId());
		if ( mark == null) {
			throw new CourseException("Mark for: " + sm.getStudentId() + "in: "
					+ sm.getModuleCode() + " not found.");
		} else {
			emgr.merge(sm);
		}
    }

    @SuppressWarnings("unchecked")
	public Module[] getAllModules() throws CourseException {
    	/* REPLACE GET ALL MODULES WITH JPA NATIVE SQL */
    	
		Query query = emgr.createNativeQuery("SELECT * FROM MODULE", Module.class);
		List<Module> modules = new ArrayList<Module>(); 
		modules = (List<Module>) query.getResultList();
		return modules.toArray(new Module[0]);
    }

    public Module getModule(String code) throws CourseException {
    	/* REPLACE MODULE SEARCH WITH JPA OPERATION FIND */
		Module module = emgr.find(Module.class, code);
		if (module == null) {
			throw new CourseException("Module : " + code + " not found");
		} else {
			return module;
		}
    }

    public void addModule(Module module) throws CourseException {
    	/* REPLACE ADD MODULE WITH JPA OPERATION PERSIST*/
		try {
			emgr.persist(module);
		} catch (EntityExistsException exe) {
			throw new CourseException("Duplicate Module : " + module.getCode());
		}
    }

    public void updateModule(Module module) throws CourseException {
    	/* REPLACE UPDATE MODULE WITH JPA MERGE */
		Module m = emgr.find(Module.class, module.getCode());
		if (m == null) {
			throw new CourseException("Module : " + module.getCode()
					+ " not found");
		} else {
			emgr.merge(module);
		}

    }

    public void deleteModule(Module module) throws CourseException {
    	 /* REPLACE DELETE MODULE WITH JPA OPERATION REMOVE */
		String id = module.getCode();
		module = emgr.find(Module.class, id);
		if (module == null) {
			throw new CourseException("Module : " + id
					+ " not found");
		} else {
			emgr.remove(module);
		}
    }
}
