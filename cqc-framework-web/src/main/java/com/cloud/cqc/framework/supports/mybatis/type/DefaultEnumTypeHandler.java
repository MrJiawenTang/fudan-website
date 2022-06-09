package com.cloud.cqc.framework.supports.mybatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import com.cloud.cqc.framework.core.constant.DefaultEnum;

/**
 * 
 * Mybatis 枚举类型处理
 * 
 * @author joy.zhou
 * @date 2017年5月15日
 */
@MappedJdbcTypes(value = JdbcType.INTEGER, includeNullJdbcType = true)
public class DefaultEnumTypeHandler<E extends DefaultEnum> extends BaseTypeHandler<E> {

	private Class<E> type;
	
	public DefaultEnumTypeHandler(){}

	public DefaultEnumTypeHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return convert(rs.getInt(columnName));
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return convert(rs.getInt(columnIndex));
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return convert(cs.getInt(columnIndex));
	}

	private E convert(int status) {

		for (E e : type.getEnumConstants()) {
			if (status == e.getValue()) {
				return e;
			}
		}
		return null;
	}

}
