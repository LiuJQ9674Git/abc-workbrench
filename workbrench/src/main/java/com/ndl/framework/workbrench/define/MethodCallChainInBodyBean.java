package com.ndl.framework.workbrench.define;

import java.io.Serializable;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndl.framework.workbrench.util.SequenceGenerator;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlAttribute;

@SuppressWarnings("restriction")
@XmlRootElement(name = "MethodCallChainInBodyBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class MethodCallChainInBodyBean implements Cloneable,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2269487183265193751L;

	@XmlElement
	@XmlIDREF
	private MethodCallChainInBodyBean next;
	
	//源Service
	@XmlElement
	@XmlIDREF
	private ServiceBean fromServiceBean=null;
	
	//源DaoBean
	@XmlElement
	@XmlIDREF
	private DaoBean fromDaoBean=null;
	
	//目标DaoBean
	@XmlElement
	@XmlIDREF
	private DaoBean toDaoBean=null;
	
	//执行源Service的哪个方法
	@XmlElement
	@XmlIDREF
	private MethodBean fromMethod=null;
	
	//执行目标Service的哪个方法
	@XmlElement
	@XmlIDREF
	private MethodBean toMethod=null;
	
	//入参执行源Service的方法的哪个参数
	@XmlElement
	@XmlIDREF
	private TableBean fromTableBean=null;
	
	
	//入参具体的说明
	@XmlElement
	private ColumnBean fromColumnBean=null;
	
	@XmlElement
	private ColumnBean fromResultType=null;
	
	//返回结果的类型
	@XmlElement
	private TableBean resultTableBean=null; 
	
	//返回结果的变量
	@XmlElement
	private ColumnBean resultColumnBean=null; 
	
	//目标Service
	@XmlElement
	@XmlIDREF
	private ServiceBean toServiceBean=null; 
	

	@XmlElement
	private TableBean toTableBean=null;
	
	@XmlElement
	private ColumnBean toColumnBean=null;
	
	@XmlElement
	private ColumnBean toResultType=null;
	
	
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
	private MethodRuntimeEnum catetory=MethodRuntimeEnum.CALL_OUTER;

	@XmlAttribute
    @XmlID	    		// should be unique across all entities.
    private String uuid; 

	@XmlElement
	private String todoComment;//注解为//方法描述，在当前方法体之后添加

    public String getUuid() {
        return uuid; 
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid.toString();
    }
    
    public MethodCallChainInBodyBean() {
    	uuid=SequenceGenerator.obtainSequenceUUID(MethodCallChainInBodyBean.class);
	}
    
	public MethodCallChainInBodyBean getNext() {
		return next;
	}

	public void setNext(MethodCallChainInBodyBean next) {
		this.next = next;
	}
	
	public ServiceBean getFromServiceBean() {
		return fromServiceBean;
	}

	public void setFromServiceBean(ServiceBean fromServiceBean) {
		this.fromServiceBean = fromServiceBean;
	}

	public MethodBean getFromMethod() {
		return fromMethod;
	}

	public void setFromMethod(MethodBean fromMethod) {
		this.fromMethod = fromMethod;
	}

	public TableBean getFromTableBean() {
		return fromTableBean;
	}

	public void setFromTableBean(TableBean fromTableBean) {
		this.fromTableBean = fromTableBean;
	}

	public ServiceBean getToServiceBean() {
		return toServiceBean;
	}

	public void setToServiceBean(ServiceBean toServiceBean) {
		this.toServiceBean = toServiceBean;
	}

	public MethodBean getToMethod() {
		return toMethod;
	}

	public void setToMethod(MethodBean toMethod) {
		this.toMethod = toMethod;
	}

	public TableBean getResultTableBean() {
		return resultTableBean;
	}

	public void setResultTableBean(TableBean resultTableBean) {
		this.resultTableBean = resultTableBean;
	}

	public ColumnBean getResultColumnBean() {
		return resultColumnBean;
	}

	public void setResultColumnBean(ColumnBean resultColumnBean) {
		this.resultColumnBean = resultColumnBean;
	}

	public MethodRuntimeEnum getCatetory() {
		return catetory;
	}

	public void setCatetory(MethodRuntimeEnum catetory) {
		this.catetory = catetory;
	}

	public ColumnBean getFromColumnBean() {
		return fromColumnBean;
	}


	public void setFromColumnBean(ColumnBean fromColumnBean) {
		this.fromColumnBean = fromColumnBean;
	}
	
	public ColumnBean getFromResultType() {
		return fromResultType;
	}

	public void setFromResultType(ColumnBean fromResultType) {
		this.fromResultType = fromResultType;
	}

	public ColumnBean getToResultType() {
		return toResultType;
	}

	public void setToResultType(ColumnBean toResultType) {
		this.toResultType = toResultType;
	}

	public TableBean getToTableBean() {
		return toTableBean;
	}

	public void setToTableBean(TableBean toTableBean) {
		this.toTableBean = toTableBean;
	}

	public ColumnBean getToColumnBean() {
		return toColumnBean;
	}

	public void setToColumnBean(ColumnBean toColumnBean) {
		this.toColumnBean = toColumnBean;
	}
	
	public DaoBean getFromDaoBean() {
		return fromDaoBean;
	}

	public void setFromDaoBean(DaoBean fromDaoBean) {
		this.fromDaoBean = fromDaoBean;
	}

	public DaoBean getToDaoBean() {
		return toDaoBean;
	}

	public void setToDaoBean(DaoBean toDaoBean) {
		this.toDaoBean = toDaoBean;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
