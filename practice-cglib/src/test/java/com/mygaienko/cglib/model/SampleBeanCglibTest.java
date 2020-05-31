package com.mygaienko.cglib.model;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.objectweb.asm.Type;
import net.sf.cglib.beans.*;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 28.01.2016.
 *
 * https://dzone.com/articles/cglib-missing-manual
 *
 * do not use non-static inner classes with cglib.
 */
public class SampleBeanCglibTest {

    @Test
    public void testFixedValue() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleBean.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello cglib!";
            }
        });
        SampleBean proxy = (SampleBean) enhancer.create();
        assertEquals("Hello cglib!", proxy.test(null));
    }

    @Test(expected = RuntimeException.class)
    public void testInvocationHandler() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleBean.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if(method.getDeclaringClass() != Object.class
                        && method.getReturnType() == String.class) {
                    return "Hello cglib!";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });
        SampleBean proxy = (SampleBean) enhancer.create();
        assertEquals("Hello cglib!", proxy.test(null));
        assertNotEquals("Hello cglib!", proxy.toString());
    }

    @Test
    public void testMethodInterceptor() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleBean.class);
        enhancer.setCallback(new TestMethodInterceptor());

        SampleBean proxy = (SampleBean) enhancer.create();

        assertEquals("Hello cglib!", proxy.test(null));
        assertNotEquals("Hello cglib!", proxy.toString());
        proxy.hashCode(); // Does not throw an exception or result in an endless loop.
    }

    private static class TestMethodInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
        throws Throwable {
            if(method.getDeclaringClass() != Object.class
                    && method.getReturnType() == String.class) {
                return "Hello cglib!";
            } else {
                return proxy.invokeSuper(obj, args);
            }
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testImmutableBean() throws Exception {
        SampleBean bean = new SampleBean();
        bean.setValue("Hello world!");

        SampleBean immutableBean = (SampleBean) ImmutableBean.create(bean);
        assertEquals("Hello world!", immutableBean.getValue());
        bean.setValue("Hello world, again!");
        assertEquals("Hello world, again!", immutableBean.getValue());

        immutableBean.setValue("Hello cglib!"); // Causes exception.
    }

    @Test
    public void testBeanGenerator() throws Exception {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("value", String.class);
        Object myBean = beanGenerator.create();

        Method setter = myBean.getClass().getMethod("setValue", String.class);
        setter.invoke(myBean, "Hello cglib!");
        Method getter = myBean.getClass().getMethod("getValue");
        assertEquals("Hello cglib!", getter.invoke(myBean));
    }

    @Test
    public void testBeanCopier() throws Exception {
        BeanCopier copier = BeanCopier.create(SampleBean.class, OtherSampleBean.class, false);
        SampleBean myBean = new SampleBean();
        myBean.setValue("Hello cglib!");
        OtherSampleBean otherBean = new OtherSampleBean();
        copier.copy(myBean, otherBean, null);
        assertEquals("Hello cglib!", otherBean.getValue());
    }

    @Test
    public void testBulkBean() throws Exception {
        BulkBean bulkBean = BulkBean.create(SampleBean.class,
                new String[]{"getValue"},
                new String[]{"setValue"},
                new Class[]{String.class});
        SampleBean bean = new SampleBean();
        bean.setValue("Hello world!");
        assertEquals(1, bulkBean.getPropertyValues(bean).length);
        assertEquals("Hello world!", bulkBean.getPropertyValues(bean)[0]);
        bulkBean.setPropertyValues(bean, new Object[] {"Hello cglib!"});
        assertEquals("Hello cglib!", bean.getValue());
    }

    @Test
    public void testBeanMapGenerator() throws Exception {
        SampleBean bean = new SampleBean();
        BeanMap map = BeanMap.create(bean);
        bean.setValue("Hello cglib!");
        assertEquals("Hello cglib!", map.get("value"));
    }

    @Test
    public void testInterfaceMaker() throws Exception {
        Signature signature = new Signature("foo", Type.DOUBLE_TYPE, new Type[]{Type.INT_TYPE});

        InterfaceMaker interfaceMaker = new InterfaceMaker();
        interfaceMaker.add(signature, new Type[0]);

        Class iface = interfaceMaker.create();
        assertEquals(1, iface.getMethods().length);
        assertEquals("foo", iface.getMethods()[0].getName());
        assertEquals(double.class, iface.getMethods()[0].getReturnType());
    }

    @Test
    public void testFastClass() throws Exception {
        FastClass fastClass = FastClass.create(SampleBean.class);
        FastMethod fastMethod = fastClass.getMethod(SampleBean.class.getMethod("getValue"));
        SampleBean myBean = new SampleBean();
        myBean.setValue("Hello cglib!");
        assertEquals("Hello cglib!", fastMethod.invoke(myBean, new Object[0]));
    }

}