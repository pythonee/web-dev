package com.mashup.utils;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.actions.DispatchAction;
import org.apache.log4j.*;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.time.StopWatch;
import org.springframework.aop.framework.ReflectiveMethodInvocation; 
import org.apache.commons.lang.StringUtils; 

public class TrackLoggingAdvisor implements MethodInterceptor{
	
	private static Logger log;
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		StopWatch clock = new StopWatch();
		
		log = Logger.getLogger(this.getClass().getName());

		String methodname = invocation.getMethod().getName();
		String classname = invocation.getMethod().getDeclaringClass().getName();
		
        Class[] params = invocation.getMethod().getParameterTypes();  
        String[] simpleParams = new String[params.length];  
        for (int i = 0; i < params.length; i++) {  
            simpleParams[i] = params[i].getSimpleName();  
        }  
        
        Object[] args = invocation.getArguments();
        String[] simpleArgs = new String[args.length];
        
        for(int i = 0;i<args.length;i++){
        	simpleArgs[i] = args[i].toString();
        }
        
        String paratype = StringUtils.join(simpleParams,",");
        String paravalue = StringUtils.join(simpleArgs,",");
        
        String simpleMethod = methodname + "(params value:{" + paravalue + "} params type:{" + paratype +"})";
		
        
        //log.debug("Before the "  + simpleMethod + " of " + classname + " running ");
        
		clock.start();
        Object result = invocation.proceed();   
        clock.stop();
        
        log.debug("{"+clock.getTime()+" ms}"+ " finish "  + simpleMethod + " of " + classname);   	
       	
       	
        return result;  
	}

}
