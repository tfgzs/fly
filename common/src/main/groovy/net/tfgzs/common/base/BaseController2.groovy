package net.tfgzs.common.base

import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import java.lang.reflect.Method
import java.lang.reflect.Type

@CompileStatic
@Slf4j
@RestController
@RequestMapping(value = ["/facade"], produces = ["application/json;charset=UTF-8"])
class BaseController2 {
    @Autowired
    private ObjectMapper objectMapper
    @Autowired
    private ApplicationContext context;

    /**
     * http://xxx.com/com.tfgzs.user.addUser=[{"username":"tom"}]&com.tfgzs.user.editUser=[{"id":"1","username":"jerry"}]&com.tfgzs.user.deleteUser=["id":"1"]
     */
    @RequestMapping("batch")
    public Object json(
            @RequestParam(required = false) Map params, @RequestHeader(required = false) Map<String, String> header) {

        Object val = null

        params.each {
            String fullClassPatch = it.key.toString()
            String rawParams = it.value.toString()

            String className = fullClassPatch.substring(0, fullClassPatch.lastIndexOf("."))//得到类名
            String methodName = fullClassPatch.substring(fullClassPatch.lastIndexOf(".") + 1, fullClassPatch.length())
//得到方法名

            Class<?> interfaceClazz = Thread.currentThread().getContextClassLoader().loadClass(className)
            Object service = context.getBean(interfaceClazz);
            if (!service || !interfaceClazz.isAnnotationPresent(Service.class)) {
                throw new RuntimeException("invoking Service Not Found");
            }

            Method[] methods = interfaceClazz.getDeclaredMethods()//所有的公开方法
            List methodParams = objectMapper.readValue(rawParams, List.class);



            Method method = null
            if (methodParams) {//如果有参数就需要匹配参数列表
                for (Method m : methods) {
                    if (methodName.equals(m.getName()) && m.getGenericParameterTypes().length == methodParams.size()) {
                        method = m
                        break;
                    }
                }
            } else {//如果没有参数就只匹配方法名
                for (Method m : methods) {
                    if (methodName.equals(m.getName())) {
                        method = m
                        break;
                    }
                }
            }


            if (methodParams) {
                val = ReflectionUtils.invokeMethod(method, service, deserialize(rawParams, method.getGenericParameterTypes()).toArray());
            } else {
                val = ReflectionUtils.invokeMethod(method, service);
            }
        }
        return val
    }

    private List deserialize(String rawParams, Type[] paramTypes) throws IOException {
        List methodParams = objectMapper.readValue(rawParams, List.class);
        List<Object> params = new ArrayList<>();
        for (int i = 0; i < methodParams.size(); i++) {
            log.debug("The param {}'s type name is {}", i, paramTypes[i].toString());
            JavaType javaType = objectMapper.getTypeFactory().constructType(paramTypes[i]);
            params.add(objectMapper.convertValue(methodParams.get(i), javaType));
            log.debug("The converted param {}'s type name is {}", i, params.get(i).getClass().getName());
        }
        return params;
    }
}
