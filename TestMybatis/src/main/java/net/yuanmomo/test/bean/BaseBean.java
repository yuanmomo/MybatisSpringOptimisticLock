package net.yuanmomo.test.bean;

import com.spagettikod.optimist.Identity;
import com.spagettikod.optimist.Version;

public class BaseBean {

	@Identity("id")
	protected Long id;

	@Version("version")
	protected Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}