package net.tfgzs.bookstore

import net.dongliu.requests.Requests
import net.tfgzs.bookstore.book.BookCriteria
import net.tfgzs.common.jsoup.GatherCriteria
import net.tfgzs.common.kit.JsonFormatTool
import net.tfgzs.common.kit.Kit
import net.tfgzs.passport.user.User
import org.junit.Test

class XinCuiWeiJuTest {

    @Test
    public void addUser() {
        println JsonFormatTool.formatJson(
                Requests.post("http://localhost:8080/user/register")
                        .forms(["nickname": "system", "principals": "system@tfgzs.net", "credentials": "system@ltf@123"])
                        .send()
                        .readToText()
        )
    }

    @Test
    public void addBook() {
        User user = Requests.post("http://localhost:8080/user/findByEmail")
                .forms(["email": "system@tfgzs.net"])
                .send()
                .readAsJson(User)


        BookCriteria book = new BookCriteria()
        book.bookName = "神荒龙帝"
        book.bookAuthor = "妖月夜"
        book.bookIntro = "远古时期，神魔大战，天地崩碎！人族少年身怀龙骨，炼真龙之体，闯神荒，探帝墓，天地因他而变！ 在这里！有女帝君临天下！有古兽只手遮天！有大魔祸乱天地！也有人族先贤镇压八荒！浩瀚神荒，谁可称尊？唯我凌飞！妖月夜出品，必属精品【已有六百万字大火老书《不死武尊》】更有国漫精品《大唐玄笔录》，网文，影视等多方面联动，为您构建一个宏大的玄幻世界，敬请期待。"
        book.user = user
        book.spiderUrl = "http://www.cuiweijuxin.com/files/article/html/0/877/index.html"

        println JsonFormatTool.formatJson(
                Requests.post("http://localhost:8080/book/add")
                        .jsonBody(book)
                        .send()
                        .readToText()
        )
    }

    @Test
    public void addChapter() {
        GatherCriteria criteria = new GatherCriteria()
        criteria.url = "http://www.cuiweijuxin.com/files/article/html/0/877/index.html"
        criteria.host = "www.cuiweijuxin.com"
        criteria.referer = "http://www.cuiweijuxin.com/fulltop/allvisit/1.html"
        criteria.userAgent = Kit.uaList.get(12);

        println JsonFormatTool.formatJson(
                Requests.post("http://localhost:8080/gather/xincuiweiju/chapter")
                        .jsonBody(criteria)
                        .send()
                        .readToText())
    }

    @Test
    public void content2() {
        GatherCriteria criteria = new GatherCriteria()
        criteria.url = "http://www.cuiweijuxin.com/files/article/html/0/877/305149.html"
        criteria.host = "www.cuiweijuxin.com"
        criteria.referer = "http://www.cuiweijuxin.com/fulltop/allvisit/1.html"
        criteria.userAgent = Kit.uaList.get(13);

        println JsonFormatTool.formatJson(
                Requests.post("http://localhost:8080/gather/xincuiweiju/content")
                        .jsonBody(criteria)
                        .send()
                        .readToText())
    }

    @Test
    public void content3() {


    }
}
