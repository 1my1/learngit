package cn.edu.nuc.onlinestore.model;

import java.io.Serializable;

public class Admin implements Serializable{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String name;
	    private String pass;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((pass == null) ? 0 : pass.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Admin other = (Admin) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (pass == null) {
				if (other.pass != null)
					return false;
			} else if (!pass.equals(other.pass))
				return false;
			return true;
		}
		
      
}
