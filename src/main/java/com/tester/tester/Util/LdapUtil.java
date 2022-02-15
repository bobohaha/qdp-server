package com.tester.tester.Util;

import com.tester.tester.Constants.JenkinsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

public class LdapUtil {


    private static Logger logger = LoggerFactory.getLogger(LdapUtil.class);

    public static boolean isConnLdapSuccess(String username, String password, String url) {

        boolean connSuccess = false;
        if (connLdap(username, password, url) != null) {
            connSuccess = true;
        }
        return connSuccess;
    }

    private static LdapContext connLdap(String username, String password, String url) {
        String BASE_DN = String.format(JenkinsConstants.LDAP_DN, username);

        Hashtable<String, String> env = new Hashtable();
        logger.debug("===" + username + "开始认证LDAP===");

        env.put(Context.SECURITY_PRINCIPAL, BASE_DN);
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put(Context.PROVIDER_URL, url);
        env.put(Context.INITIAL_CONTEXT_FACTORY, JenkinsConstants.LDAP_FACTORY);
        env.put("com.sun.jndi.ldap.connect.timeout", "3000");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");//认证类型

        LdapContext ldapContext = null;
        try {
            ldapContext = new InitialLdapContext(env, null);//开始连接
        } catch (NamingException e) {
            logger.debug("username or password error");
        }
        return ldapContext;
    }

    public static void logoutLdap(String username, String password, String url) {
        LdapContext ctx = connLdap(username, password, url);
        if (ctx != null) {
            try {
                ctx.close();
            } catch (NamingException e) {
                logger.debug(String.valueOf(e));
            }
        }
    }
}
