package cn.edu.nuc.onlinestore.utils;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel{
	    private boolean DEBUG=false;
        private String[] columnNames={"商品ID","商品名称","商品价格","商品数量","操作"};
        //仓库里的所有商品
        private Object[][] goodsStore={
        		{"1","疯狂JAVA","43","10",new Boolean(true)},
        		{"2","C++","32","11", new Boolean(false)},
        		{"3","UML","55","12", new Boolean(true)},
        };
        
        public Object[][] getGoodsStore() {
			return goodsStore;
		}
		public void setGoodsStore(Object[][] goodsStore) {
			this.goodsStore = goodsStore;
		}
		//table有多少列
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnNames.length;
		}
        //table有多少行
		public int getRowCount() {
			// TODO Auto-generated method stub
			return goodsStore.length;
		}
		//返回列名
		public String getColumnName(int col) {
            return columnNames[col];
        }
		//某一商品的某一列
		public Object getValueAt(int row, int col) {
            return goodsStore[row][col];
        }
		/*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
       /*
        * Don't need to implement this method unless your table's
        * editable.
        */
       //table cell是否可编辑
      public boolean isCellEditable(int row, int col) {
           //Note that the data/cell address is constant,
           //no matter where the cell appears onscreen.
           if (col < 4) {
               return false;
           } else {
               return true;
           }
       }
      /*
       * Don't need to implement this method unless your table's
       * data can change.
       */
     public void setValueAt(Object value, int row, int col) {
          if (DEBUG) {
              System.out.println("Setting value at " + row + "," + col
                                 + " to " + value
                                 + " (an instance of "
                                 + value.getClass() + ")");
          }
          
          goodsStore[row][col] = value;
          
          fireTableCellUpdated(row, col);

          if (DEBUG) {
              System.out.println("New value of data:");
              printDebugData();
          }
      }

      private void printDebugData() {
          int numRows = getRowCount();
          int numCols = getColumnCount();

          for (int i=0; i < numRows; i++) {
              System.out.print("    row " + i + ":");
              for (int j=0; j < numCols; j++) {
                  System.out.print("  " + goodsStore[i][j]);
              }
              System.out.println();
          }
          System.out.println("--------------------------");
      }
      @Override
    public void fireTableRowsDeleted(int firstRow, int lastRow) {
    // TODO Auto-generated method stub
          super.fireTableRowsDeleted(firstRow, lastRow);
    }
		
}

