package net.yuanmomo.test.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.yuanmomo.test.business.TestBusiness;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spagettikod.optimist.ModifiedByAnotherUserException;

@Controller
@RequestMapping("/result.do")
public class TestController {

	@Resource(name = "testBusiness")
	private TestBusiness testBusiness = null;
	public void setTestBusiness(TestBusiness testBusiness) {
		this.testBusiness = testBusiness;
	}

	@RequestMapping
	public String viewUser(HttpServletRequest request, ModelMap map){
		try {
			testBusiness.threadA(1);
			map.put("message", "更新成功！");
		} catch (ModifiedByAnotherUserException e) {
			map.put("message", "随不起，你没有更新成功，请重新操作！！！");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "result";
	}
	@RequestMapping(params = "c=read")
	public String read(HttpServletRequest request, ModelMap map){
		try {
			testBusiness.threadB(1);
			map.put("message", "更新成功！");
		} catch (ModifiedByAnotherUserException e) {
			map.put("message", "随不起，你没有更新成功，请重新操作！！！");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "result";
	}
}