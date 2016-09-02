package net.tfgzs.bookstore.timer

import net.tfgzs.bookstore.book.BookController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.PropertySource
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component

@Component
@EnableScheduling
@Configurable
@PropertySource("classpath:/timer.properties")
public class ScheduledTasks {
	@Autowired
	private BookController bookController;


//	@Scheduled(cron = "${caiji}")
	public void caiji() {
	}
}