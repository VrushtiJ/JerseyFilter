# JerseyFilter
Implementation of ContainerRequestFilter in Jersey which will be used for validation any request coming from web service.

# Flow
User Id and password are received in JSON format. Filter demurrs out login request and standard services are called where the AzureAD token service to get the JWT will be called. 

The important parameters sent to the AzureAD in this call are:

          username – In the request body
          password –  In the request body
          client_secret – Fixed client secret generated by AzureAD at the time of registration.
          scope – opened (to let AzureAD server know that it has to return the JWT in response)


The important response parameters received from this AzureAD service are:

          access_token – will be used in signing
          refresh_token – will be used in signing
          id_token – This is the unsigned JWT received from AzuredAD which will be further signed and sent in the response body.
 If the id_token is received meaning user is authenticated and should allow access.
 
After getting the unsigned JWT from AzureAD server, another JWT is created which is signed using a different secret key (Any secret key can be used here. In this example the secret is 'verify') and sends it in the response. 
Except login all other requests should have signed token in their header. 

Before calling any services, Filter will identify if the request is correct or not by validating the signed token received in the request. 

 
# Note
Required DB information and Active Directory's client related info should be added in application.properties file. 

db.driver=
db.url=
db.username = 
db.password =
AD.azureRequestURL=
AD.azureRequestResponse=
AD.azureRequestClientId=
AD.azureRequestGrantType=password
AD.azureRequestScope=openid
AD.azureRequestClientSecret=


