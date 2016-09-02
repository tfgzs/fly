package net.tfgzs.common.base

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.nutz.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

import java.lang.reflect.Method

@CompileStatic
@Slf4j
@RestController
@RequestMapping(value = ["/facade"], produces = ["application/json;charset=UTF-8"])
class BaseController {
    @Autowired
    private ObjectMapper objectMapper
    @Autowired
    private ApplicationContext context;


    @RequestMapping("json/{packageName}/{interfaceName}/{methodName}")
    public Object json(@PathVariable String packageName,
                       @PathVariable String interfaceName,
                       @PathVariable String methodName,
                       @RequestParam(value = "params", required = false) String[] params,
                       @RequestHeader(required = false) Map<String, String> header) {
        Class<?> interfaceClazz = Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + interfaceName)
        Object service = context.getBean(interfaceClazz);
        if (!service || !interfaceClazz.isAnnotationPresent(Service.class)) {
            throw new RuntimeException("invoking Service Not Found");
        }
        Class<?>[] parameterTypes = Json.fromJsonAsArray(Class, header.get("parameterTypes"))//参数类型列表(数量、顺序、类型)
        Method method = interfaceClazz.getMethod(methodName, parameterTypes)
        return method.invoke(service, params)//具体的参数
    }
}
