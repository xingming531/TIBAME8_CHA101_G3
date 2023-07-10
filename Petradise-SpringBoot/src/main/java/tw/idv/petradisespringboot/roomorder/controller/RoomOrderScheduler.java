package tw.idv.petradisespringboot.roomorder.controller;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tw.idv.petradisespringboot.roomorder.service.RoomOrderService;

@Component
public class RoomOrderScheduler {

    private final RoomOrderService roomOrderService;

    public RoomOrderScheduler(RoomOrderService roomOrderService) {
        this.roomOrderService = roomOrderService;
    }

    @Scheduled(cron = "0 0 0 * * *") // Runs at midnight every day
    public void updateExpiredOrderStatus() {
        roomOrderService.updateExpiredOrderStatus();
    }
}
