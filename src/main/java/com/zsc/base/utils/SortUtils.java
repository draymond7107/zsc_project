package com.zsc.base.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SortUtils {
	public static String sortNumber(String number){
		int[] intarr = NumberUtils.str2IntArr(number);
		Arrays.sort(intarr);
		return StringUtils.intArrayToStr(intarr);
	}
	/**
	 * 把一个有分割符号的字符串进行排序,返回用,分割的字符串
	 * 如 04,08,29,15,18,23,28,7 > 04,07,08,15,18,23,28,29
	 * @param numbers
	 * @return
	 */
	public static String sortStringSplit(String numbers){
		int[] nums = NumberUtils.str2IntArrSplit(numbers);
		Arrays.sort(nums);
		return StringUtils.intArrayToStr(nums,",");
	}
	public static int[] sortStrArr(String[] arr){
		int[] intarr = NumberUtils.strArr2IntArr(arr);
		Arrays.sort(intarr);
		return intarr;
	}
	/**
	 * 对一个数组进行排序,排序后进行两位格式化
	 * 如 04,08,29,15,18,23,28,7 > 04,07,08,15,18,23,28,29
	 * @param arr
	 * @return
	 */
	public static String[] sortAndFmt(String[] arr){
		int[] intarr = NumberUtils.strArr2IntArr(arr);
		Arrays.sort(intarr);
		String[] nums = new String[intarr.length];
		for(int i=0;i<intarr.length;i++){
			String tnum = "";
			if (intarr[i] <= 9){
				tnum = "0" + intarr[i];
			}else{
				tnum = String.valueOf(intarr[i]);
			}
			nums[i] = tnum;
		}
		return nums;
	}
	public static String sortAndFmt2Str(String[] arr,String split){
		int[] intarr = NumberUtils.strArr2IntArr(arr);
		Arrays.sort(intarr);
		String[] nums = new String[intarr.length];
		for(int i=0;i<intarr.length;i++){
			String tnum = "";
			if (intarr[i] <= 9){
				tnum = "0" + intarr[i];
			}else{
				tnum = String.valueOf(intarr[i]);
			}
			nums[i] = tnum;
		}
		return StringUtils.join(nums,split);
	}
	
	//****签名那个类的排序方法
    final public static void sortArray(Object[] array, boolean isDescOrder) {
        sortArray(array, null, isDescOrder);
    }

    final public static void sortArray(Object[] array, String propName, boolean isDescOrder) {
        List<Object> objectList = Arrays.asList(array);
        sortList(objectList, propName, isDescOrder);
    }

    @SuppressWarnings({"rawtypes"})
    public static final void sortList(List aList, boolean isDescOrder) {
        sortList(aList, null, isDescOrder);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    final public static void sortList(List aList, String propName, boolean isDescOrder) {
        Collections.shuffle(aList);
        ListComparator comp = new ListComparator(propName, isDescOrder);
        Collections.sort(aList, comp);
    }

    final public static int compareValue(Object val1, Object val2) {
        int result = 0;
        if(val1 == null) result = -1;
        else if(val2 == null) result = 1;
        else if(val1 instanceof Integer) result = ((Integer) val1 - (Integer) val2) * -1;
        else if(val1 instanceof String) {
            String strVal1 = (String) val1;
            String strVal2 = (String) val2;
            result = strVal2.compareTo(strVal1);
        } else if(val1 instanceof Boolean) {
            if((Boolean) val1 == true) {
                result = 1;
            }
        } else {
            result = 0;
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static class ListComparator implements Comparator {
        private String propName;
        private boolean desc;

        public ListComparator(boolean isDesc) {
            this.desc = isDesc;
        }

        public ListComparator(String propName, boolean isDesc) {
            this.desc = isDesc;
            this.propName = propName;
        }

        public int compare(Object o1, Object o2) {
            int result = 0;
            int order = 1;
            if(o1.getClass().equals(o2.getClass())) {
                if(!desc) order = -1;
                if(propName != null && propName.length() != 0) {
                    Object val1 = getProperty(o1, propName);
                    Object val2 = getProperty(o2, propName);
                    result = compareValue(val1, val2) * order;
                } else {
                    result = compareValue(o1, o2) * order;
                }
            } else {
                result = 0;
            }
            return result;
        }
    }

    @SuppressWarnings("rawtypes")
	public static final Object getProperty(Object object, String propName) {
    	if(object instanceof Map){
    		return ((Map)object).get(propName);
    	}
        if(object == null) return null;
        Class<? extends Object> clazz = object.getClass();
        String methodName = "getInstance" + propName.substring(0, 1).toUpperCase() + propName.substring(1);
        try {
            Method getMethod = clazz.getMethod(methodName);
            return getMethod.invoke(object);
        } catch (Exception e) {
            return null;
        }
    }
}
