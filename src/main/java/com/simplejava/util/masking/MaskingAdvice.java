package com.simplejava.util.masking;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/6/2023
 * Time: 12:04 AM
 */

@Aspect
@Component
public class MaskingAdvice {

    @Pointcut(value = "@annotation(MaskedService)")
    public void myPointcut() {

    }

    @Around("myPointcut()")
    public Object maskAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object responePayLoad = joinPoint.proceed();
        Object responseObject;
        if (responePayLoad instanceof ResponseEntity<?>) {
            ResponseEntity re = (ResponseEntity) responePayLoad;
            responseObject = re.getBody();
            if (null == responseObject) {
                return null;
            }
            if (responseObject instanceof List) {
                for (Object obj : (List) responseObject)
                    maskBean(obj);
            }else{
                maskBean(responseObject);
            }
     }
        return responePayLoad;
    }

    private void maskBean(Object responseObject) {
        Field[] fields = responseObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (isMaskableField(field)) {
                maskFieldForBean(responseObject, field);
            }
        }
    }

    private void maskFieldForBean(Object responseObject, Field field) {
        field.setAccessible(true);
        Object value = null;
        try {
            value = field.get(responseObject);
            if (value instanceof String) {
                String maskedValue = MaskData.maskSensitiveData(String.valueOf(value));
                field.set(responseObject, maskedValue);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean isMaskableField(Field field) {
        return field.isAnnotationPresent(MaskedField.class);
    }
}
