package com.wsl.util;

import java.util.Map;
import java.util.Set;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

/**
 * Quick utility to create the JSON message.
 * I hate to do it this way, but no resort since I had to finish the POC today and that flow is only to prove the concept
 * @author ravi
 *
 */
public class JSONMessageCreator implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		StringBuffer buff = new StringBuffer("{\n");
		MuleMessage msg = eventContext.getMessage();
		/*Set names = msg.getInvocationPropertyNames();
		System.out.println(names);*/
		Map parent = (Map) msg.getInvocationProperty("parent");
		Map child = (Map) msg.getInvocationProperty("child");
		
		String parentSKU = (String)parent.get("sku");
		String childSKU = (String)child.get("sku");
		
		buff.append("\"parentsku\":\"").append(parentSKU).append("\"");
		buff.append(",\"childsku\":[\"").append(childSKU).append("\"]");
		
		buff.append("}");
		return buff.toString();
	}
	
	

}
