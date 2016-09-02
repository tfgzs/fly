package net.tfgzs.common.jsoup

import groovy.transform.CompileStatic
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * 使用Jsoup采集并解析页面
 * @author TengFei Liu
 * @date 2016年8月25日00:25:43
 */
@CompileStatic
abstract class JsoupTemplate {
    public void gather(GatherCriteria criteria) throws IOException {
        Connection connection = Jsoup.connect(criteria.getUrl())
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")//
                .header("Accept-Encoding", "gzip, deflate, sdch")//
                .header("Accept-Language", "zh-CN,zh;q=0.8")//
                .header("Cache-Control", "max-age=0")//
                .header("Connection", "keep-alive")//
                .header("Referer", criteria.getReferer())//
                .header("Host", criteria.getHost())//
                .header("User-Agent", criteria.getUserAgent())//
                .header("Upgrade-Insecure-Requests", "1")//
        if (criteria.otherHeaders) {
            criteria.otherHeaders.each {
                connection.header(it.getKey(), it.getValue())
            }
        }
        Document doc = connection.get();
        parser(doc)
    }

    abstract void parser(Document doc);
}
