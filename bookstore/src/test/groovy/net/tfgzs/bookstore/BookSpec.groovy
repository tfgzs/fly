package net.tfgzs.bookstore

import net.dongliu.requests.Requests
import net.tfgzs.bookstore.book.BookCriteria
import net.tfgzs.common.kit.JsonFormatTool
import net.tfgzs.passport.user.User

class BookSpec {

    def setupSpec() {}

    def setup() {}

    def cleanup() {}

//    @Ignore
    def "新增图书"() {
        setup:
//        User user=ObjectMapperFactory.createObjectMapper().readValue(Requests.post("http://localhost:8080/user/findByEmail")
//                .forms(["email": "system@tfgzs.net"])
//                .send().readToText(),User)

        User user = Requests.post("http://localhost:8080/user/findByEmail")
                .forms(["email": "system@tfgzs.net"])
                .send()
                .readAsJson(User)

        BookCriteria book = new BookCriteria()
        book.bookName = "巫师之旅"
        book.bookAuthor = "佚名"
        book.bookIntro = "给我无尽的知识，我便以自身为支点，撬动无尽世界。故事讲述的是一个名为格林的巫师，依靠自己的智慧和运气，学习自己独特的巫师知识，游历异域世界，参与不同文明之间战争的故事。一直想写一本理性的风格迥异巫师类作品，所谓的理性可以理解为伪科学，因此会有很多巫术推理和伪科学实验情节"
        book.userId = user.id
        book.spiderUrl = "http://www.cuiweijuxin.com/files/article/html/0/482/index.html"

        println JsonFormatTool.formatJson(
                Requests.post("http://localhost:8080/book/add")
                        .jsonBody(book)
                        .send()
                        .readToText()
        )
    }
//
//
//    def "修改图书"() {
//        setup:
//        BookService bookService = (BookService) (new HttpJsonProxy(BookService, url).getObject())
//        Book book = bookService.findById("fb684786-646a-4177-90ae-8a8acb45c6fb")
//        book.bookIntro = book.bookIntro + '、呵呵'
//        def result = bookService.saveAndUpdate(book)
//        expect:
//        result == null
//    }
//
//    def "删除图书"() {
//        setup:
//        BookService bookService = (BookService) (new HttpJsonProxy(BookService, url).getObject())
//        def result = bookService.deleteById("1")
//        expect:
//        result == null
//    }
//
//    def "根据id查询图书"() {
//        setup:
//        BookService bookService = (BookService) (new HttpJsonProxy(BookService, url).getObject())
//        def book = bookService.findOneById("28f0f8b4-7d5f-4549-964c-6544c47035e1")
//        println ReflectionToStringBuilder.toString(book, ToStringStyle.MULTI_LINE_STYLE)
//        expect:
//        book != null
//    }
//
//    def "分页查询图书"() {
//        setup:
//        Book book = new Book()
//        book.name = "java"
//
//        BookService BookService = (BookService) (new HttpJsonProxy(BookService, url).getObject())
//        PagedList<Book> bookPage = BookService.findList(book, Paginator.page(1, 1))
//        println ReflectionToStringBuilder.toString(bookPage)
//        expect:
//        bookPage != null
//    }
//
//    def "分页查询图书2"() {
//        setup:
//        BookService BookService = (BookService) (new HttpJsonProxy(BookService, url).getObject())
//        Pageable paginator = new PageRequest(1, 1)
//        Page<Book> bookPage = BookService.pageList(paginator);
//        println ReflectionToStringBuilder.toString(bookPage)
//        expect:
//        bookPage != null
//    }
//
//    def "ddd"() {
//    }
}
