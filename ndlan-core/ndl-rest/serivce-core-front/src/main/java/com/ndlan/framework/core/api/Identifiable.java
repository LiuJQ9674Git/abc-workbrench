package com.ndlan.framework.core.api;

import java.io.Serializable;

/**
 * 主键标识
 * @author LiuJQ
 */
public interface Identifiable extends Serializable {
	/**
	 * 获取主键
	 * @author LiuJQ
	 * @return
	 */
	public String getDefaultId();

	/**
	 * 设置ID属性
	 * @param id
	 */
	public void setDefaultId(String defaultId);
}
