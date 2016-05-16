package com.ndlan.framework.core.status;

/**
	 * 字典类型枚举类型 dir 目录，data 数据
	 * @author LiuJQ
	 */
public enum EnumDictionaryType implements BaseEnum {
	DIR("目录"), DATA("数据");

	private String label;

	private EnumDictionaryType(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
