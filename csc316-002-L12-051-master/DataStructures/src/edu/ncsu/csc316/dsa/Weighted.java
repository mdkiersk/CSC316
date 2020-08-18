package edu.ncsu.csc316.dsa;

/**
 * Represents the interface for all things weighted 
 * @author Matthew Kierski
 *
 */
public interface Weighted {
	/**
	 * Returns the weight of this edge
	 * @return the weight of this edge
	 */
	int getWeight();
	
	/**
	 * Example class that implements the Weighted interface to use in tests
	 * @author Matthew Kierski
	 *
	 */
	public class Highway implements Weighted {
		/** Name of this highway */
		private String name;
	    /** Length of this highway */   
		private int length;
		
		/**
		 * Constructs a Highway
		 * @param n name to set
		 * @param l length to set
		 */
		public Highway(String n, int l) {      
			setName(n);     
			setLength(l);   
		}
     
		/**
		 * Sets the name of this highway
		 * @param name name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * Gets the length of this highway
		 * @return the length of this highway
		 */
		public int getLength() {
			return length;
		}
		
		/**
		 * Sets the length of this highway
		 * @param length length to set
		 */
		public void setLength(int length) {
			this.length = length;       
		}
		
		/**
		 * Returns the name of this highway
		 * @return name of this highway
		 */
		public String getName() {
			return name;
		}
		@Override
		public int getWeight() {
			return getLength();
		}
	}
}