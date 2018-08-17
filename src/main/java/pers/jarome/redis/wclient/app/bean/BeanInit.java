package pers.jarome.redis.wclient.app.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

/**
 * @author jarome
 * @date 2018/1/5
 **/
@Component
public class BeanInit {

    @Bean
    public ParameterNameDiscoverer parameterNameDiscoverer() {
        return new LocalVariableTableParameterNameDiscoverer();
    }

}
