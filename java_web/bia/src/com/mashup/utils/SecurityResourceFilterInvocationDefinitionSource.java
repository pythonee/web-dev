package com.mashup.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.*;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.ConfigAttributeEditor;
import org.springframework.security.intercept.web.FilterInvocation;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.security.util.AntUrlPathMatcher;
import org.springframework.security.util.RegexUrlPathMatcher;
import org.springframework.security.util.UrlMatcher;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mashup.service.ISecurityManager;
import com.mashup.service.impl.SecurityManagerSupport;

import sun.util.logging.resources.logging;


public class SecurityResourceFilterInvocationDefinitionSource implements FilterInvocationDefinitionSource, InitializingBean {

	private UrlMatcher urlMatcher;

    private boolean useAntPath = true;
    
    private boolean lowercaseComparisons = false;
    
    private ISecurityManager securityManager;

    /**
     * @param useAntPath the useAntPath to set
     */
    public void setUseAntPath(boolean useAntPath) {
        this.useAntPath = useAntPath;
    }
    
    /**
     * @param lowercaseComparisons
     */
    public void setLowercaseComparisons(boolean lowercaseComparisons) {
        this.lowercaseComparisons = lowercaseComparisons;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        
        // default url matcher will be RegexUrlPathMatcher
        this.urlMatcher = new RegexUrlPathMatcher();
        
        if (useAntPath) {  // change the implementation if required
            this.urlMatcher = new AntUrlPathMatcher();
        }
        
        // Only change from the defaults if the attribute has been set
        if ("true".equals(lowercaseComparisons)) {
            if (!this.useAntPath) {
                ((RegexUrlPathMatcher) this.urlMatcher).setRequiresLowerCaseUrl(true);
            }
        } else if ("false".equals(lowercaseComparisons)) {
            if (this.useAntPath) {
                ((AntUrlPathMatcher) this.urlMatcher).setRequiresLowerCaseUrl(false);
            }
        }
        
    }
    
    /* (non-Javadoc)
     * @see org.springframework.security.intercept.ObjectDefinitionSource#getAttributes(java.lang.Object)
     */
    public ConfigAttributeDefinition getAttributes(Object filter) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) filter;
        String requestURI = filterInvocation.getRequestUrl();
        
        Map<String, String> urlAuthorities = securityManager.loadUrlAuthorities();
        
        String grantedAuthorities = null;
        for(Iterator<Map.Entry<String, String>> iter = urlAuthorities.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, String> entry = iter.next();
            String url = entry.getKey();
            
            if(urlMatcher.pathMatchesUrl(url, requestURI)) {
                grantedAuthorities = entry.getValue();
                break;
            }
            
        }
        
        if(grantedAuthorities != null) {
            ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
            configAttrEditor.setAsText(grantedAuthorities);
            return (ConfigAttributeDefinition) configAttrEditor.getValue();
        }
        
        return null;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.intercept.ObjectDefinitionSource#getConfigAttributeDefinitions()
     */
    @SuppressWarnings("unchecked")
	public Collection getConfigAttributeDefinitions() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.intercept.ObjectDefinitionSource#supports(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
        return true;
    }

	/**
	 * @return the securityManager
	 */
	public ISecurityManager getSecurityManager() {
		return securityManager;
	}

	/**
	 * @param securityManager the securityManager to set
	 */
	public void setSecurityManager(ISecurityManager securityManager) {
		this.securityManager = securityManager;
	}
}
