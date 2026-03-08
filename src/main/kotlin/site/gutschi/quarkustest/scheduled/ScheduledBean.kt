package site.gutschi.quarkustest.scheduled

import io.quarkus.scheduler.Scheduled
import io.quarkus.scheduler.ScheduledExecution
import jakarta.enterprise.context.ApplicationScoped
import java.util.concurrent.atomic.AtomicInteger


@ApplicationScoped
class ScheduledBean {
    private final val counter = AtomicInteger()

    fun get() = counter.get()

    fun reset() = counter.set(0)

    @Scheduled(every = "0.1S")
    fun increment() {
        counter.incrementAndGet()
    }

    @Scheduled(cron = "0 15 10 * * ?")
    fun cronJob(execution: ScheduledExecution) {
        counter.incrementAndGet()
        System.out.println(execution.scheduledFireTime)
    }
}