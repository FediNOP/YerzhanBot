package ru.nop.yerzhanbot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nop.yerzhanbot.service.BotFacade;
import ru.nop.yerzhanbot.service.SelloutNotifyService;

@Slf4j
@Component
public class SelloutNotifyServiceImpl implements SelloutNotifyService {

    private final BotFacade botFacade;

    public SelloutNotifyServiceImpl(BotFacade botFacade) {
        this.botFacade = botFacade;
    }

    @Scheduled(cron = "0 0 9 * * ?")
    @Override
    public void run() {
        log.info("Checking sellout task");
        botFacade.runCheckSellout();
    }
}
