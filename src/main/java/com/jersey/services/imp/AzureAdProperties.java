/**
 * 
 */
package com.jersey.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author vrjoshi
 *
 */
public class AzureAdProperties {

	String azureRequestURL;
	public String getAzureRequestURL() {
		return azureRequestURL;
	}
	public void setAzureRequestURL(String azureRequestURL) {
		this.azureRequestURL = azureRequestURL;
	}
	public String getAzureRequestResponse() {
		return azureRequestResponse;
	}
	public void setAzureRequestResponse(String azureRequestResponse) {
		this.azureRequestResponse = azureRequestResponse;
	}
	public String getAzureRequestClientId() {
		return azureRequestClientId;
	}
	public void setAzureRequestClientId(String azureRequestClientId) {
		this.azureRequestClientId = azureRequestClientId;
	}
	public String getAzureRequestGrantType() {
		return azureRequestGrantType;
	}
	public void setAzureRequestGrantType(String azureRequestGrantType) {
		this.azureRequestGrantType = azureRequestGrantType;
	}
	public String getAzureRequestScope() {
		return azureRequestScope;
	}
	public void setAzureRequestScope(String azureRequestScope) {
		this.azureRequestScope = azureRequestScope;
	}
	public String getAzureRequestClientSecret() {
		return azureRequestClientSecret;
	}
	public void setAzureRequestClientSecret(String azureRequestClientSecret) {
		this.azureRequestClientSecret = azureRequestClientSecret;
	}
	String azureRequestResponse;
	String azureRequestClientId;
	String azureRequestGrantType;
	String azureRequestScope;
	String azureRequestClientSecret;


}
