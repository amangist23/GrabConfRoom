package com.lowleveldesign.crms.AOP.Logging;

import com.lowleveldesign.crms.Controllers.UserController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingControllerAspect {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Before(value = "execution(* com.lowleveldesign.crms.Controllers.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        logger.info("Incoming Request to CONTROLLER "+ joinPoint.getSignature() + " STARTED");
    }

    @After(value = "execution(* com.lowleveldesign.crms.Controllers.*.*(..))")
    public void afterAdvice(JoinPoint joinPoint){
        logger.info("Incoming Request to CONTROLLER "+ joinPoint.getSignature() + " ENDED");
    }
}
