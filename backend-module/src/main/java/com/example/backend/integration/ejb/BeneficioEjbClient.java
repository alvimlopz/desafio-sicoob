package com.example.backend.integration.ejb;

import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicReference;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// Importe a interface que voce definiu
import com.example.ejb.BeneficioEjbRemote;

@Service
public class BeneficioEjbClient {

    private final AtomicReference<BeneficioEjbRemote> ejbRef = new AtomicReference<>();

    private final String providerUrl;
    private final String jndiName;

    public BeneficioEjbClient(
            @Value("${ejb.provider-url}") String providerUrl,
            @Value("${ejb.beneficio.jndi}") String jndiName
    ) {
        this.providerUrl = providerUrl;
        this.jndiName = jndiName;
    }

    public void transfer(Long fromId, Long toId, java.math.BigDecimal amount) {
        getEjb().transfer(fromId, toId, amount);
    }

    private BeneficioEjbRemote getEjb() {
        BeneficioEjbRemote instance = ejbRef.get();
        if (instance == null) {
            synchronized (this) {
                instance = ejbRef.get();
                if (instance == null) {
                    instance = lookupEjb();
                    ejbRef.set(instance);
                }
            }
        }
        return instance;
    }

    private BeneficioEjbRemote lookupEjb() {
        try {
            Hashtable<String, Object> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            env.put(Context.PROVIDER_URL, providerUrl);
            
            env.put("jboss.naming.client.ejb.context", true);

            Context ctx = new InitialContext(env);
            Object obj = ctx.lookup(jndiName);
            return (BeneficioEjbRemote) obj;

        } catch (NamingException e) {
            throw new IllegalStateException("Erro ao localizar EJB remoto: " + jndiName, e);
        }
    }
}