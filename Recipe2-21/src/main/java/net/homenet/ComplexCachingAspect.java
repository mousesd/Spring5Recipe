package net.homenet;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
public class ComplexCachingAspect {
    private Map<String, Complex> cache = new ConcurrentHashMap<>();

    public void setCache(Map<String, Complex> cache) {
        this.cache.clear();
        this.cache.putAll(cache);
    }

    @Around(value = "call(public Complex.new(int, int)) && args(x, y)", argNames = "joinPoint,x,y")
    public Object cacheAround(ProceedingJoinPoint joinPoint, int x, int y) throws Throwable {
        String key = x + "," + y;
        Complex complex = cache.get(key);
        if (complex == null) {
            System.out.println("Cache miss for (" + key + ")");
            complex = (Complex) joinPoint.proceed();
            cache.put(key, complex);
        } else {
            System.out.println("Cache hit for (" + key + ")");
        }
        return complex;
    }
}
