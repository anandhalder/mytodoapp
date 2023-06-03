package com.example.mytodoapp.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class ObjectUpdateUtility {

	public static void mergeObjects(Object source, Object target) {

		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}

	public static String[] getNullPropertyNames(Object source) {

		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();
		Set<String> emptyNames = new HashSet<>();

		for (PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) {
				emptyNames.add(pd.getName());
			}
		}

		String[] res = new String[emptyNames.size()];
		return emptyNames.toArray(res);
	}

}
