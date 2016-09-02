package net.tfgzs.bookstore.book

import net.tfgzs.common.base.BaseEntity
import net.tfgzs.passport.user.User
import org.hibernate.annotations.NaturalId

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table
@Entity
@Table(name = "bookstore_book")
class Book extends BaseEntity {

    /**
     * 书名
     */
    @Column(nullable = false)
    String bookName
    /**
     * 作者
     */
    @Column
    String bookAuthor
    /**
     * 简介
     */
    @Column(length = 800)
    String bookIntro

    /**
     * 上传用户
     */
    @ManyToOne(optional = false)
    @NaturalId
    User user

    /**
     * 爬虫采集地址
     */
    @Column
    String spiderUrl
}
