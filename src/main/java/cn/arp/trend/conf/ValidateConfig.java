package cn.arp.trend.conf;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.validation.Validator;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/8
 * Time:下午7:23
 **/
@Configuration
public class ValidateConfig {

    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }
}
