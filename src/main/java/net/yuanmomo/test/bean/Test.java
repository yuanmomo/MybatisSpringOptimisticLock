package net.yuanmomo.test.bean;

import com.spagettikod.optimist.OptimisticLocking;

@OptimisticLocking("TEST")
public class Test extends BaseBean{
	private int number;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
