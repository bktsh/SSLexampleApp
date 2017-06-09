###Enable SSL/HTTP1.1 on tomcat
Let's say you have developer exampleApp and installed it on tomcat on port 8080 and you access it at [http://localhost:8080/exampleApp](http://localhost:8080/exampleApp)
1. Create a keystore file to store the server's private key and self-signed certificate by executing the following command:
~~~~~
$JAVA_HOME/bin/keytool -genkey -alias tomcat -keyalg RSA
and specify a password value of "changeit".
~~~~~

2. Edit server.xml inside tomcat and uncomment the "SSL HTTP/1.1 Connector" entry and modify as described in the Configuration section below.
~~~~~
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
               maxThreads="200"
	       SSLEnabled="true"
	       scheme="https"
	       secure="true"
	       keystoreFile="${user.home}/.keystore"
	       keystorePass="changeit"
	       clientAuth="false"
	       sslProtocol="TLS">
    </Connector>
~~~~~

3. Restart the server and got o [https://localhost:8443/exampleApp](https://localhost:8443/exampleApp)
4. To Force your application to use SSL make sure to add following to your web.xml filr:
~~~~
<security-constraint>
    <web-resource-collection>
        <web-resource-name>securedapp</web-resource-name>
        <url-pattern>/*</url-pattern>
    </web-resource-collection>
    <user-data-constraint>
        <transport-guarantee>CONFIDENTIAL</transport-guarantee>
    </user-data-constraint>
</security-constraint>
~~~~
