package com.cloud.cqc.client.ueditor.entity;

import java.util.ArrayList;
import java.util.List;

public class MultiResultState extends ResultState {

	private List<ResultState> list = new ArrayList<>(0);

	public MultiResultState() {
	}

	public MultiResultState(boolean success, int infoCode) {
		super(success, infoCode);
	}

	public List<ResultState> getList() {
		return list;
	}

	public void setList(List<ResultState> list) {
		this.list = list;
	}

	public void addState(ResultState state) {
		this.list.add(state);
	}

}
