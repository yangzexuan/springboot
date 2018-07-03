package com.dome.springboot.mongodb.bean;

import java.io.Serializable;

public class Comment implements Serializable {
	private int good;
	private int bad;
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"good=" + good +
				", bad=" + bad +
				'}';
	}
}