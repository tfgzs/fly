package net.tfgzs.common.kit

import groovy.transform.CompileStatic

/**
 * Created by XiaoJie on 2016/5/23.
 */
@CompileStatic
class BeanUtil {
    static def copyProperties(source, target) {
        this.copyProperties(source,target,[] as String[])
    }
    static def copyProperties(source, target,String[] ex) {
        def exludeList=['class', 'metaClass']
        exludeList.addAll(ex)
        source.properties.each {key, value ->
            if (target.hasProperty((String)key) && !(key in exludeList)) {
                target[(String)key] = value
            }
        }
    }
}
