package net.tfgzs.bookstore.chapter

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import net.tfgzs.bookstore.book.Book
import net.tfgzs.bookstore.book.BookRepository
import net.tfgzs.common.kit.BeanUtil
import net.tfgzs.passport.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@Slf4j
@RestController
@RequestMapping(value = ["/chapter"], produces = ["application/json"])
class ChapterController {
    @Autowired
    UserRepository userRepository
    @Autowired
    BookRepository bookRepository
    @Autowired
    ChapterRepository chapterRepository


    @Transactional
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Chapter add(@RequestBody ChapterCriteria criteria) {
        Book book = bookRepository.findOne(criteria.bookId)
        if (book) {
            Chapter c = new Chapter()
            BeanUtil.copyProperties(criteria, c)
            c.book = book
            Chapter newChapter = chapterRepository.save(c)
            return newChapter
        } else {
            throw new RuntimeException("图书不存在")
        }
    }

    @Transactional
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody String id) {
        chapterRepository.delete(id)
    }

    @RequestMapping(value = "/findById", method = [RequestMethod.POST, RequestMethod.GET])
    public Chapter findById(String id) {
        Chapter oldChapter = chapterRepository.findOne(id)
        if (oldChapter) {
            return oldChapter
        } else {
            throw new RuntimeException("章节不存在")
        }
    }
}
