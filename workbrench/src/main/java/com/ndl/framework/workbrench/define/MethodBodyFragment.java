package com.ndl.framework.workbrench.define;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("restriction")
@XmlRootElement(name = "MethodCallChainInBodyBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class MethodBodyFragment  implements Cloneable,Serializable{
	
	private static final long serialVersionUID = -8752398040258029005L;
	
	//@XmlElement//参数签名,orderName,pageSize
	//private String calleeAssignature;
	@XmlElement//实例变量+"."+调用方法签名
	private String calleeMethodName;
	@XmlElement
	private String comment;//todo
	/**
	  CALL_OUTER,//方法体的签名方法
	  CALL_INTER,//进入的第一个方法
	  CALL_END,//方法的最后一个方法
	  RESULT_DEFAULT,//以源服务执行结果为目标的入参，结果合并到源服务执行结果的某个指定变量中,结果为源服务
	  RESULT_MAIN,//以源服务执行结果为目标的入参，结果合并到指定对象的某个变量中，结果为指定服务
	  RESULT_VOID,//以源服务执行结果为目标的入参，目标服务结果不处理，结果为源服务源服务执行结果
	  RESULT_SINGLE_DESTATION,//以源服务执行结果为目标的入参，结果为目标服务执行结果
	  RESULT_DESTATION,//以源服务执行结果为目标的入参，结果合并到目标服务执行结果的某个指定变量中
	  REULT_LIST,//以源服务执行结果为目标的入参，目标服务结果不处理，结果为指定的LIST
	  REULT_MAP,//以源服务执行结果为目标的入参，目标服务结果不处理，结果为指定的MAP，其KEY为入参值
	  
	  SEQ_DEFAULT,//源服务和目标服务同一个入参，结果合并到源服务执行结果的某个指定变量中
	  SEQ_MAIN,//源服务和目标服务同一个入参，结果合并到指定对象的某个变量中
	  SEQ_VOID,//源服务和目标服务同一个入参，目标服务结果不处理
	  SEQ_SINGLE_DESTATION,//源服务和目标服务同一个入参，结果为目标服务执行结果
	  SEQ_DESTATION,//源服务和目标服务同一个入参，结果合并到目标服务执行结果的某个指定变量中
	  SEQ_LIST,//源服务和目标服务同一个入参，目标服务结果不处理，结果为指定的LIST
	  SEQ_MAP//源服务和目标服务同一个入参，目标服务结果不处理，结果为指定的MAP，其KEY为入参值
	 */
	@XmlElement
	private MethodRuntimeEnum catetory;
	// 有序Set,方法内对于入参验证使用
	@XmlElement(name = "calleeArgument")
	@XmlElementWrapper(name = "calleeArgumentList")
	private List<ColumnBean> calleeArgumentList = new ArrayList<ColumnBean>();
	
	@XmlElement//调用对象时返回参数的参数类型定义
	private String localFieldName;//String id; Order order;
	@XmlElement
	private String returnType;
	
	
	public List<ColumnBean> getCalleeArgumentList() {
		return calleeArgumentList;
	}

	public void setCalleeArgumentList(List<ColumnBean> calleeArgumentList) {
		this.calleeArgumentList = calleeArgumentList;
	}

	public void addCalleeArgument(ColumnBean calleeArgument) {
		this.calleeArgumentList.add(calleeArgument);
	}
	
	public String getLocalFieldName() {
		return localFieldName;
	}

	public void setLocalFieldName(String localFieldName) {
		this.localFieldName = localFieldName;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getCalleeMethodName() {
		return calleeMethodName;
	}
	
	public void setCalleeMethodName(String calleeMethodName) {
		this.calleeMethodName = calleeMethodName;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public MethodRuntimeEnum getCatetory() {
		return catetory;
	}
	
	public void setCatetory(MethodRuntimeEnum catetory) {
		this.catetory = catetory;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
