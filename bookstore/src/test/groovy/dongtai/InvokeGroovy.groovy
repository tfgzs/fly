package dongtai

import org.junit.Ignore
import org.junit.Test;

interface IFoo {
    public Object run(Object foo)
}

public class InvokeGroovy {
    @Test
    @Ignore
    public void demo() {
        ClassLoader cl = new InvokeGroovy().getClass().getClassLoader();
        GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
        try {
            // 从文件中读取
//            Class groovyClass = groovyCl.parseClass(new File("C:\\mysource2\\GroovyLearn\\groovy\\fist\\Foo.groovy"));
//            IFoo foo = (IFoo) groovyClass.newInstance();
//            System.out.println(foo.run(new Integer(2)));
            //动态拼接
            Class groovyClass2 = groovyCl.parseClass("package org.openjweb.groovy; \r\n import dongtai.IFoo;\r\n class Foo implements IFoo {public Object run(Object foo) {return ((Integer)foo).intValue()+3}}");
            IFoo foo2 = (IFoo) groovyClass2.newInstance();
            System.out.println(foo2.run(new Integer(2)));


//            GroovyObject clazzObj = (GroovyObject)groovyClass2.newInstance();
//            System.out.println(clazzObj.invokeMethod("run", 2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
