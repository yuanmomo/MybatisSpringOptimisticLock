package net.yuanmomo.test.business;

import net.yuanmomo.test.bean.Test;
import net.yuanmomo.test.mybatis.mapper.TestMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spagettikod.optimist.ModifiedByAnotherUserException;

@Service
public class TestBusiness{
	private Logger logger=Logger.getLogger(TestBusiness.class);
	
	@Autowired
	private TestMapper testMapper=null;

	//*********    setter and getter   *************//
	public TestMapper getTestMapper() {
		return testMapper;
	}

	public void setTestMapper(TestMapper testMapper) {
		this.testMapper = testMapper;
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation =Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
	public boolean threadA(int id) throws Exception {
		Test old = this.testMapper.get(id);
		logger.info("AAAAAAAAAAAAAAAAAAAAAAAA, A will sleeps for 10 seconds, now the NUMBER is "+old.getNumber());
		Thread.sleep(5000);
		logger.info("AAAAAAAAAAAAAAAAAAAAAAAA, A wakes up, add 1 on NUMBER, then update the NUMBER into DB.");
		old.setNumber(old.getNumber()+1);
		
		this.testMapper.update(old);
		
		logger.info("AAAAAAAAAAAAAAAAAAAAAAAA, A finishes updating");
		Test newTestObj=this.testMapper.get(id);
		logger.info("AAAAAAAAAAAAAAAAAAAAAAAA, After A executing, query from DB that the new NUMBER is "+newTestObj.getNumber());
		return true;
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation =Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
	public boolean threadB(int id) throws Exception {
		Test old = this.testMapper.get(id);
		logger.info("BBBBBBBBBBBBBBBBBBBBBBBB, B is starting to update, and now the NUMBER is "+old.getNumber());
		old.setNumber(old.getNumber()+2);
		logger.info("BBBBBBBBBBBBBBBBBBBBBBBB, B will add 2 on NUMBER, then update the NUMBER into DB.");
		try {
			this.testMapper.update(old);
		} catch (ModifiedByAnotherUserException e) {
			throw e;
		}
		logger.info("BBBBBBBBBBBBBBBBBBBBBBBB, B finishes updating");
		Test newTestObj=this.testMapper.get(id);
		logger.info("BBBBBBBBBBBBBBBBBBBBBBBB, After B executing, query from DB that the new NUMBER is " +newTestObj.getNumber());
		return true;
	}
}
