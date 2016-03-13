package com.ndlan.framework.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.ndl.framework.workbrench.util.ClassHelper;

public class ClassHelperTest {

	@Test
	public void isPrimitiveOrWrapper(){
		 int s=3;
		 boolean b=ClassHelper.isPrimitiveOrWrapper(s);
		 assertTrue(b);
	}

}
