package com.lowleveldesign.crms.AOP.Logging;

import com.lowleveldesign.crms.Controllers.BuildingController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingServiceAspect {
    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    @Before(value = "execution(* com.lowleveldesign.crms.Services.*.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        logger.info("Incoming Request to SERVICE LAYER "+ joinPoint.getSignature() + " STARTED");
    }

    @After(value = "execution(* com.lowleveldesign.crms.Services.*.*.*(..))")
    public void afterAdvice(JoinPoint joinPoint){
        logger.info("Incoming Request to SERVICE LAYER "+ joinPoint.getSignature() + " ENDED");
    }
}
