package net.tfgzs.bookstore.gather

import net.tfgzs.bookstore.book.BookController
import net.tfgzs.bookstore.chapter.ChapterController
import net.tfgzs.common.jsoup.JsoupTemplate
import net.tfgzs.passport.user.UserController
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class XinCuiWeiJu_Content  extends JsoupTemplate {
    @Autowired
    UserController userController
    @Autowired
    BookController bookController
    @Autowired
    ChapterController chapterController

    @Override
    void parser(Document doc) {
        String content = doc.getElementsByClass("content").get(0).html()//.text().replace(Jsoup.parse("&nbsp;").text(), " ")
        println content
    }
}
