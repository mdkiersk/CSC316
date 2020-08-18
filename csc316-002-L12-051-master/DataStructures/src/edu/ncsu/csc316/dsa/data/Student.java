package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	// Student's first name 
	private String first;
	// Student's last name 
	private String last;
	// Student's id number 
	private int id;
	// Student's credit hours
	private int creditHours;
	// Student's GPA
	private double gpa;
	// Student's unity ID name
	private String unityId;
	
	/**
	 * Constructs a Student object and assigns a first name, last name, numerical ID, credit hours,
	 * GPA, and unity ID.
	 * @param first first name of Student
	 * @param last last name of Student
	 * @param id numerical ID of Student
	 * @param creditHours number of credit hours
	 * @param gpa student's GPA
	 * @param unityId student's unity ID
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityId) {
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setGpa(gpa);
		setUnityId(unityId);
	}

	/**
	 * Returns Student's first name
	 * @return first name
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Sets Student's first name
	 * @param first first name
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Returns Student's last name
	 * @return last name
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Sets last name
	 * @param last last name
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Returns ID
	 * @return Student's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets student's ID
	 * @param id ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns credit hours
	 * @return student's creditHours
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Sets student's credit hours
	 * @param creditHours creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Returns student's gpa
	 * @return student's gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Sets student's gpa
	 * @param gpa gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Returns student's unity ID
	 * @return the unityId
	 */
	public String getUnityId() {
		return unityId;
	}

	/**
	 * Sets student's unity ID
	 * @param unityId unityId to set
	 */
	public void setUnityId(String unityId) {
		this.unityId = unityId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (id != other.id)
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}

	/**
	 * Custom comparison function that considers last name, first name, then ID in
	 * determining equality between this student and a given student
	 */
	@Override
	public int compareTo(Student o) {
		if (this.getLast().compareTo(o.getLast()) > 0) {
			return this.getLast().compareTo(o.getLast());
		} else if (this.getLast().compareTo(o.getLast()) < 0) {
			return this.getLast().compareTo(o.getLast());
		}

		if (this.getFirst().compareTo(o.getFirst()) > 0) {
			return this.getFirst().compareTo(o.getFirst());
		} else if (this.getFirst().compareTo(o.getFirst()) < 0) {
			return this.getFirst().compareTo(o.getFirst());
		}

		if (this.getId() > o.getId()) {
			return 1;
		} else if (this.getId() < o.getId()) {
			return -1;
		}

		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getFirst() + " " + getLast() + ", " + getId();
	}

}
