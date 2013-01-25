package net.yuanmomo.test.mybatis.mapper;

import net.yuanmomo.test.bean.Test;

public interface TestMapper {
	public int update(Test t) throws Exception;
	public Test get(int id) throws Exception;
}
