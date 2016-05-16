package com.ndl.framework.workbrench.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

public class ClassHelper {

    public static Class<?> forNameWithThreadContextClassLoader(String name)
        throws ClassNotFoundException {
        return forName(name, Thread.currentThread().getContextClassLoader());
    }

    public static Class<?> forNameWithCallerClassLoader(String name, Class<?> caller)
        throws ClassNotFoundException {
        return forName(name, caller.getClassLoader());
    }

    public static ClassLoader getCallerClassLoader(Class<?> caller) {
        return caller.getClassLoader();
    }
    
	/**
	 * get class loader 
	 * 
	 * @param cls
	 * @return class loader
	 */
    public static ClassLoader getClassLoader(Class<?> cls) {
    	ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = cls.getClassLoader();
        }
        return cl;
    }

    public static ClassLoader getClassLoader() {
    	return getClassLoader(ClassHelper.class);
    }

    /**
     * Same as <code>Class.forName()</code>, except that it works for primitive
     * types.
     */
    public static Class<?> forName(String name) throws ClassNotFoundException {
        return forName(name, getClassLoader());
    }

    public static Class<?> forName(String name, ClassLoader classLoader)
            throws ClassNotFoundException, LinkageError {

        Class<?> clazz = resolvePrimitiveClassName(name);
        if (clazz != null) {
            return clazz;
        }

        // "java.lang.String[]" style arrays
        if (name.endsWith(ARRAY_SUFFIX)) {
            String elementClassName = name.substring(0, name.length() - ARRAY_SUFFIX.length());
            Class<?> elementClass = forName(elementClassName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }

        // "[Ljava.lang.String;" style arrays
        int internalArrayMarker = name.indexOf(INTERNAL_ARRAY_PREFIX);
        if (internalArrayMarker != -1 && name.endsWith(";")) {
            String elementClassName = null;
            if (internalArrayMarker == 0) {
                elementClassName = name
                        .substring(INTERNAL_ARRAY_PREFIX.length(), name.length() - 1);
            } else if (name.startsWith("[")) {
                elementClassName = name.substring(1);
            }
            Class<?> elementClass = forName(elementClassName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }

        ClassLoader classLoaderToUse = classLoader;
        if (classLoaderToUse == null) {
            classLoaderToUse = getClassLoader();
        }
        return classLoaderToUse.loadClass(name);
    }

    public static Class<?> resolvePrimitiveClassName(String name) {
        Class<?> result = null;
        // Most class names will be quite long, considering that they
        // SHOULD sit in a package, so a length check is worthwhile.
        if (name != null && name.length() <= 8) {
            // Could be a primitive - likely.
            result = (Class<?>) primitiveTypeNameMap.get(name);
        }
        return result;
    }

    /** Suffix for array class names: "[]" */
    public static final String  ARRAY_SUFFIX            = "[]";
    /** Prefix for internal array class names: "[L" */
    private static final String INTERNAL_ARRAY_PREFIX   = "[L";

    /**
     * Map with primitive type name as key and corresponding primitive type as
     * value, for example: "int" -> "int.class".
     */
    private static final Map<String,Class<?>>    primitiveTypeNameMap    = new HashMap<String, Class<?>>(16);

    public static boolean isPrimitiveOrWrapper(Object type){
    	return ClassUtils.isPrimitiveOrWrapper(type.getClass());
    }
    
    public static boolean isPrimitiveOrWrapper(Class type){
    	return ClassUtils.isPrimitiveOrWrapper(type);
    }
    
    public static String getType(Object type){
    	return ClassUtils.getSimpleName(type.getClass());
    }
    
    public static boolean isSynthetic(Class type){
    	return type.isSynthetic();
    }
    
    public static boolean isSynthetic(Object type){
    	return type.getClass().isSynthetic();
    }
    
    public static boolean isArray(Class type){
    	return type.isArray();
    }
    
    public static boolean isArray(Object type){
    	return type.getClass().isArray();
    }
    
    public static boolean isString(Object type){
    	return type.getClass()==String.class;
    }
    
    public static boolean isDate(String type,String regex){
    	return type.matches(regex);
    }
    
    public static String toShortString(Object obj){
        if(obj == null){
            return "null";
        }
        return obj.getClass().getSimpleName() + "@" + System.identityHashCode(obj);
        
    }
    
    public static String lowerCaseUsingJavaMethod(String str){
    	if(StringUtils.isBlank(str)){
    		return null;
    	}
    	String lowerCaseStr = str;//.toLowerCase();
		
		
		if(isCapitalion(lowerCaseStr)){
			String firstA=lowerCaseStr.substring(0, 1);
			String lower=firstA.toLowerCase();
			lowerCaseStr=StringUtils.replace(lowerCaseStr, firstA, lower, 1);
		}
		return lowerCaseStr;
    }
    
    public static String namingUsingJavaMethod(String str){
    	if(StringUtils.isBlank(str)){
    		return null;
    	}
    	String lowerCaseStr = str;//.toLowerCase();
		String[] noDashArray = StringUtils.split(lowerCaseStr, '_');
		StringBuilder noDash = new StringBuilder(noDashArray[0]);
		for (int i = 1; i < noDashArray.length; i++) {
			noDash.append(StringUtils.capitalize(noDashArray[i]));
		}
		lowerCaseStr=noDash.toString();
		
		if(isCapitalion(lowerCaseStr)){
			String firstA=lowerCaseStr.substring(0, 1);
			String lower=firstA.toLowerCase();
			lowerCaseStr=StringUtils.replace(lowerCaseStr, firstA, lower, 1);
		}
		return lowerCaseStr;
    }
    
    public static String namingUsingJavaClass(String str){
    	if(StringUtils.isBlank(str)){
    		return null;
    	}
    	String lowerCaseStr = str;//.toLowerCase();
		String[] noDashArray = StringUtils.split(lowerCaseStr, '_');
		StringBuilder noDash = new StringBuilder(noDashArray[0]);
		for (int i = 1; i < noDashArray.length; i++) {
			noDash.append(StringUtils.capitalize(noDashArray[i]));
		}
		lowerCaseStr=noDash.toString();
		
		if(!isCapitalion(lowerCaseStr)){
			String firstA=lowerCaseStr.substring(0, 1);
			String lower=firstA.toUpperCase();
			lowerCaseStr=StringUtils.replace(lowerCaseStr, firstA, lower, 1);
		}
		return lowerCaseStr;
    }
    /**
     * 首字母大写返回true，否则返回false
     * @param str
     * @return
     */
    public static boolean isCapitalion(final String str) {
    	if(StringUtils.isBlank(str)){
    		return false;
    	}
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return false;
        }

        final char firstChar = str.charAt(0);
        if (Character.isLowerCase(firstChar)) {
            // already uncapitalized
            return false;
        }else{
        	return true;
        }

    }
    
    public static String getShortClassName(final Class<?> cls){
    	return ClassUtils.getShortClassName( cls);
    }
    
    
}