package net.tfgzs.bookstore.chapter
import net.tfgzs.bookstore.book.Book
import net.tfgzs.common.base.BaseEntity
import org.hibernate.annotations.NaturalId

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "bookstore_chapter")
class Chapter  extends BaseEntity {

    /**
     * 所属图书
     */
    @ManyToOne(optional = false)
    @NaturalId
    Book book
    /**
     * 标题
     */
    @Column(nullable = false)
    String title
    /**
     * 内容
     */
    @Column
    String content
    /**
     * 显示的顺序
     */
    @Column
    Integer displayIndex
    /**
     * 1显示   0不显示
     */
    @Column
    Integer displayStatus
    /***
     * 爬虫采集地址
     */
    @Column
    String spiderUrl

}
