package org.example;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Java spi 机制 Demo
 * 参考：https://www.jianshu.com/p/3a3edbcd8f24
 */
public class App 
{
    public static void main( String[] args )
    {
        ServiceLoader<ISpiService> load = ServiceLoader.load(ISpiService.class);
        Iterator<ISpiService> iterator = load.iterator();
        while (iterator.hasNext()){
            ISpiService service = iterator.next();
            service.execute();
        }
    }
}
