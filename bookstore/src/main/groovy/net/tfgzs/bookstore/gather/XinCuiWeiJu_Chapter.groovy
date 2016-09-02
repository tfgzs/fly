package net.tfgzs.bookstore.gather
import net.tfgzs.bookstore.book.Book
import net.tfgzs.bookstore.book.BookController
import net.tfgzs.bookstore.chapter.ChapterController
import net.tfgzs.bookstore.chapter.ChapterCriteria
import net.tfgzs.common.jsoup.JsoupTemplate
import net.tfgzs.passport.user.UserController
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class XinCuiWeiJu_Chapter extends JsoupTemplate {
    @Autowired
    UserController userController
    @Autowired
    BookController bookController
    @Autowired
    ChapterController chapterController
    @Autowired
    XinCuiWeiJu_Content xinCuiWeiJu_content


    @Override
    void parser(Document doc) {
        Elements eles = doc.getElementsByClass("chapters");
        if (eles.size() > 0) {
            Elements chapters = eles.get(1).getElementsByClass("chapter")
            for (int i = 0; i < chapters.size(); i++) {
                Element a = chapters.get(i).getElementsByTag("a").get(0)
                String title = a.text().trim()
                String href = a.attr("href")


                Book book=bookController.findById("")
                ChapterCriteria chapter=new ChapterCriteria()
                chapter.book=book
                chapter.title=title
                chapter.content=""
                chapter.displayIndex=i
                chapter.displayStatus=1
                chapter.spiderUrl=href  //爬虫采集地址
                chapterController.add(chapter)
            }
        }
    }
}

