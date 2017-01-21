package sec.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.apache.catalina.Context;
import org.apache.catalina.session.StandardManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CyberSecurityBaseProjectApplication {

    @Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		TomcatContextCustomizer tc = new TomcatContextCustomizer() {
                    @Override
                    public void customize(Context context) {
                        
                        
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        MySessionManager msm = new MySessionManager();
                        context.setManager(msm);
                        context.getManager().getSessionIdGenerator().setSessionIdLength(5);
                    }
                };
                
                tomcat.addContextCustomizers(tc);
                //tomcat.addAdditionalTomcatConnectors(createStandardConnector());
		return tomcat;
	}
    
    public static void main(String[] args) throws Throwable {
        SpringApplication.run(CyberSecurityBaseProjectApplication.class);
    }
    
    public class MySessionManager extends StandardManager {

    @Override
    protected synchronized String generateSessionId() { 
        //String sessionId = <Your session id generation algo>;
        long epoch = System.currentTimeMillis();
        String e = String.valueOf(epoch);
        String sessionId = e;
        //String 
        //sessionId = "12345";
        return sessionId;
    }
   }
}
