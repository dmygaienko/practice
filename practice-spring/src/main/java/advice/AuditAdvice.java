package advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by enda1n on 12.09.2016.
 */
@Aspect
public class AuditAdvice {

    @Before("execution(public void service.BeanB.serve()) && @annotation(auditAnnotation) ")
    public void beforeLogger(Audit auditAnnotation) {
        System.out.println("entered service " + auditAnnotation.value());
    }

}
