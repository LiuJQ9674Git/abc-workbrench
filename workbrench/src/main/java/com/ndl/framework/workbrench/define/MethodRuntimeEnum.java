package com.ndl.framework.workbrench.define;

public enum  MethodRuntimeEnum  {
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
	  
}
