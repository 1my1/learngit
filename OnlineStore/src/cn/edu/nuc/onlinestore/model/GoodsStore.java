package cn.edu.nuc.onlinestore.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class GoodsStore implements Serializable{
        private Set<Goods> gs;

		public Set<Goods> getGs() {
			
			return gs;
		}

		public void setGs(Set<Goods> gs) {
			this.gs = gs;
		}

		
}
