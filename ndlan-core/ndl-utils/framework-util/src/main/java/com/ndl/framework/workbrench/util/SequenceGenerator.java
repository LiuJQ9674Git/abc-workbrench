package com.ndl.framework.workbrench.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
public class SequenceGenerator {
	
	private static Map<Class,AtomicLong> sequence=new HashMap<Class,AtomicLong>();
	
	public static String obtainSequenceUUID(Class clzz){
		AtomicLong seq=sequence.get(clzz);
		if(null==seq){
			seq=new AtomicLong();
		}
		String uuid=UUID.randomUUID().toString()+"-"+clzz.getName()+"-"+seq;
		seq.incrementAndGet();
		sequence.put(clzz, seq);
		return uuid;
		
	}
}
