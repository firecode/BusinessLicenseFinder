/*
 * @author Raghu Ram Bongula
 */
package com.util;

import com.google.wireless.gdata.data.StringUtils;

import android.os.DeadObjectException;
import android.os.IServiceManager;
import android.os.ServiceManagerNative;
import android.telephony.IPhone;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceUtil.
 */
public class ServiceUtil {

	/**
	 * Gets the phone interface.
	 * 
	 * @return the phone interface
	 * 
	 * @throws DeadObjectException
	 *             the dead object exception
	 */
	public static IPhone getPhoneInterface() throws DeadObjectException {
		IServiceManager sm = ServiceManagerNative.getDefault();
		IPhone phoneService = IPhone.Stub.asInterface(sm.getService("phone"));
		return phoneService;
	}
	
	
	/**
	 * Call.
	 * 
	 * @param phoneNum
	 *            the phone num
	 */
	public static void call(String phoneNum){
		if(!StringUtils.isEmpty(phoneNum) && phoneNum != null){
			IPhone phone;
			try {
				phone = ServiceUtil.getPhoneInterface();
				phone.call(phoneNum);
			} catch (DeadObjectException e) {
				e.printStackTrace();
			}
			
			
		}else{
			
		}
	}

}
