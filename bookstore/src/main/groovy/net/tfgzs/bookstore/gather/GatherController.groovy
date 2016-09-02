package net.tfgzs.bookstore.gather

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import net.tfgzs.common.jsoup.GatherCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@Slf4j
@CompileStatic
@RestController
@RequestMapping(value = ["/gather"], produces = ["application/json"])
class GatherController {
    @Autowired
    XinCuiWeiJu_Chapter xinCuiWeiJu_chapter
    @Autowired
    XinCuiWeiJu_Content xinCuiWeiJu_content

    @Transactional
    @RequestMapping(value = "/{site}/{category}", method = RequestMethod.POST)
    public void add(@PathVariable String site, @PathVariable String category, @RequestBody GatherCriteria criteria) {
        switch (site) {
            case "xincuiweiju":
                switch (category) {
                    case "chapter":
                        xinCuiWeiJu_chapter.gather(criteria)
                        break
                    case "content":
                        xinCuiWeiJu_content.gather(criteria)
                        break
                    default:
                        throw new RuntimeException("没有找到该类型的采集模板")
                }
                break
            case "qidian":
                break
            case "bixianwenxue":
                break
            case "xiaomiyuedu":
                break
            default:
                throw new RuntimeException("没有找到该网站的采集模板")
        }
    }

}
