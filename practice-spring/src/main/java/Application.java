import config.SpringConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;
import service.BeanA;
import service.BeanB;

/**
 * Created by dmygaenko on 06/09/2016.
 */
//@Configuration
//@Import({SpringConfig.class})
public class Application {

    @Autowired
    private ApplicationContext appContext;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(SpringConfig.class);

        ctx.refresh();

        BeanA myBeanA = ctx.getBean("myBeanA", BeanA.class);
        System.out.println(myBeanA.getBeanBName());

        BeanB beanB = ctx.getBean("BeanB1", BeanB.class);

        BeanB beanBAll = ctx.getBean("BeanBAll", BeanB.class);

        BeanB beanB2 = ctx.getBean("BeanB2", BeanB.class);

    }
}
