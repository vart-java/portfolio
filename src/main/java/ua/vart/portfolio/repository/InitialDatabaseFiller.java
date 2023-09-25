package ua.vart.portfolio.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ua.vart.portfolio.domain.entity.Client;
import ua.vart.portfolio.domain.entity.Code;
import ua.vart.portfolio.domain.entity.Feedback;
import ua.vart.portfolio.service.ClientService;
import ua.vart.portfolio.service.CodeService;
import ua.vart.portfolio.service.FeedbackService;

@Slf4j
@RequiredArgsConstructor
@Component
@ConditionalOnProperty(prefix = "db-initializer", name = "enable")
public class InitialDatabaseFiller implements CommandLineRunner {
    private final ClientService clientService;
    private final CodeService codeService;
    private final FeedbackService feedbackService;

    @Override
    public void run(String... args) {
        var client1 = loadClientInDb("Богдан", "Хмельницький");
        var client2 = loadClientInDb("Юрко", "Залізняк");
        var client3 = loadClientInDb("Ольга", "Хлопська");

        var code1 = loadCodeInDb(client1);
        var code2 = loadCodeInDb(client2);
        var code3 = loadCodeInDb(client3);

        var feedback1 = loadFeedbackInDb(code1, "Катруся - це не просто психолог, а справжня майстриня своєї справи! Я маю щастя працювати з нею, і кожен сеанс з нею є справжнім відкриттям і пізнанням себе.\n" +
                "\n" +
                "З початку нашої спільної роботи мене вразила її тепла, відкритість і емпатія. Катруся завжди створює атмосферу довіри і зрозуміння, де я можу відчувати себе комфортно і вільно висловлювати свої думки та почуття."

        );
        var feedback2 = loadFeedbackInDb(code2, "Її професійна компетентність також вражає. Катруся має глибокі знання в галузі психології та психотерапії і завжди допомагає мені зрозуміти ситуацію з різних ракурсів. Вона вміло використовує різні методи та техніки, які допомагають мені розвиватися і робити кроки вперед у своєму особистому рості."
        );
        var feedback3 = loadFeedbackInDb(code3, "Найважливіше, що Катруся завжди підтримує мене, навіть у найтяжчі моменти. Її підхід до роботи дуже індивідуальний і спрямований на досягнення моїх конкретних цілей і потреб.\n" +
                "\n" +
                "Я вдячний/вдячна Катрусі за її допомогу і підтримку. Це справжня професіоналка з великим серцем, і я рекомендую її всім, хто шукає якісну психологічну підтримку."
        );

        log.debug("Added three feedbacks: " + feedback1 + " " + feedback2 + " " + feedback3);
    }

    Client loadClientInDb(String name, String lastName) {
        if (clientService.findByNameAndLastName(name, lastName).isPresent()) {
            return null;
        }
        return clientService.create(Client.builder().name(name).lastName(lastName).build());

    }

    Code loadCodeInDb(Client client) {
        if (client == null) return null;
        if (!codeService.findAllByClient(client).isEmpty()) return null;
        return codeService.create(client);
    }


    Feedback loadFeedbackInDb(Code code, String text) {
        if (code == null) return null;
        return feedbackService.create(Feedback.builder().code(code).text(text).build());
    }
}
