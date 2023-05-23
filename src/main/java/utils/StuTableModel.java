package utils;

/**
 * An adaptor, transforming the JDBC interface to the TableModel interface.
 */
/**在Swing中，表格分解为表格界面（JTable）和表格模型（TableModel）两部分。
 * JTable控制数据的显示方式；
 * TableModel控制数据本身；
 * 构造JTable之前通常会先构造TableModel；
 * TableModel是一个接口，用户可以完整实现此接口，
 * 也可以继承抽象类AbstractTableModel并实现其中的抽象方法，
 * 还可以使用DefaultTableModel类，该类实现了TableModel接口。
 */

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class StuTableModel extends AbstractTableModel {
	public String[] columnNames = {};
	public List<Object> rows = new ArrayList<Object>();
	public ResultSetMetaData metaData;
	/**
	 * 利用ResultSet的getMetaData的方法可以获得ResultSetMeta对象，
	 * 而ResultSetMetaData存储了ResultSet的MetaData。所谓的MetaData 在英文中的解释为“Data about
	 * Data”，直译成中文则为 “有关数据的数据”或者“描述数据的数据”，实际上就是描述及解释
	 * 含义的数据。以Result的MetaData为例，ResultSet是以表格的形式
	 * 存在，所以getMetaData就包括了数据的字段名称、类型以及数目 等表格所必须具备的信息。
	 **/
	public int row; // 表格中的行号
	public String[] set; // 当鼠标点击表格中的某一行时，把该行的数据保存到数组set中

	public StuTableModel() {
	}

	// 获取当前行号
	public int getRow() {
		System.out.println(row);
		return row;
	}

	// 当鼠标点击表格中的第row行时，把该行的数据保存到数组set中
	public String[] getString(int row) {
		set=new String[columnNames.length];
		if (row >= 0) {
			for (int i = 0; i < columnNames.length; i++) {
				set[i] = getValueAt(row, i).toString();
			}
		}
		return set;
	}

	// MetaData
	// 获取字段名字
	@Override
	public String getColumnName(int column) {
		if (columnNames[column] != null) {
			return columnNames[column];
		} else {
			return "";
		}
	}

	// 根据数据表字段的数据类型转化成Java中相应的数据类型
	@Override
	public Class getColumnClass(int column) {
		int type;
		try {
			type = metaData.getColumnType(column + 1);
		} catch (SQLException e) {
			return super.getColumnClass(column);
		}

		switch (type) {
			case Types.CHAR:
			case Types.VARCHAR:
			case Types.LONGVARCHAR:
				return String.class;

			case Types.BIT:
				return Boolean.class;

			case Types.TINYINT:
			case Types.SMALLINT:
			case Types.INTEGER:
				return Integer.class;

			case Types.BIGINT:
				return Long.class;

			case Types.FLOAT:
			case Types.DOUBLE:
				return Double.class;

			case Types.DATE:
				return java.sql.Date.class;

			default:
				return Object.class;
		}
	}
	@Override
	public boolean isCellEditable(int row, int column) {

		return false; // 表示表格JTable的单元格是不可编辑的
	}
	@Override
	// 获取字段的数目
	public int getColumnCount() {
		return columnNames.length;
	}

	// Data methods
	@Override
	// 获取记录的数目
	public int getRowCount() {
		return rows.size();
	}
	@Override
	// 返回 aColumn 和 aRow 位置的单元格值
	public Object getValueAt(int aRow, int aColumn) {
		List row = (List) rows.get(aRow); // 对应一条记录
		this.row = aRow;
		return row.get(aColumn);
	}

	public String dbRepresentation(int column, Object value) {
		int type;

		if (value == null) {
			return "null";
		}

		try {
			type = metaData.getColumnType(column + 1);
		} catch (SQLException e) {
			return value.toString();
		}

		switch (type) {
			case Types.INTEGER:
			case Types.DOUBLE:
			case Types.FLOAT:
				return value.toString();
			case Types.BIT:
				return ((Boolean) value).booleanValue() ? "1" : "0";
			case Types.DATE:
				return value.toString(); // This will need some conversion.
			default:
				return "\"" + value.toString() + "\"";
		}
	}
	@Override
	// 将 column 和 row 位置的单元格中的值设置为 value。
	public void setValueAt(Object value, int row, int column) {
		try {
			String tableName = metaData.getTableName(column + 1);
			// Some of the drivers seem buggy, tableName should not be null.
			if (tableName == null) {
				System.out.println("Table name returned null.");
			}
			String columnName = getColumnName(column);
			String query = "update " + tableName + " set " + columnName + " = "
					+ dbRepresentation(column, value) + " where ";
			// We don't have a model of the schema so we don't know the
			// primary keys or which columns to lock on. To demonstrate
			// that editing is possible, we'll just lock on everything.
			for (int col = 0; col < getColumnCount(); col++) {
				String colName = getColumnName(col);
				if (colName.equals("")) {
					continue;
				}
				if (col != 0) {
					query = query + " and ";
				}
				query = query + colName + " = "
						+ dbRepresentation(col, getValueAt(row, col));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Update failed");
		}
		List dataRow = (List) rows.get(row);
		dataRow.set(column, value);
	}
}
