package hexlet.code.util;

import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ModelGenerator {

    private Model<User> userModel;
    private Model<TaskStatus> statusModel;

    @Autowired
    private Faker faker;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void init() {

       userModel = Instancio.of(User.class)
                .ignore(Select.field("id"))
                .supply(Select.field("passwordDigest"),
                        () -> {
                            String password = faker.internet().password(3, 10);
                            return passwordEncoder.encode(password);
                        })
                .supply(Select.field("firstName"), () -> faker.name().firstName())
                .supply(Select.field("lastName"), () -> faker.name().lastName())
                .supply(Select.field("email"), () -> faker.internet().emailAddress())
                .toModel();

        statusModel = Instancio.of(TaskStatus.class)
                .ignore(Select.field("id"))
                .supply(Select.field("name"), () -> faker.animal().name())
                .supply(Select.field("slug"), () -> faker.lorem().word())
                .toModel();
    }
}

