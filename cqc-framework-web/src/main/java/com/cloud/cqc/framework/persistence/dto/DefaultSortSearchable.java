package com.cloud.cqc.framework.persistence.dto;

public class DefaultSortSearchable extends Searchable {

	private static final long serialVersionUID = 1L;

	static final Ordered[] DEFAULT_ORDERED = new Ordered[] { new Ordered("sort"), new Ordered("id", Ordered.DESC) };

	public Ordered[] getOrdereds() {
		return super.getOrdereds() == null ? DEFAULT_ORDERED : super.getOrdereds();
	}
}
