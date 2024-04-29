package hexlet.code.component;

import hexlet.code.dto.TaskStatusCreateDTO;
import hexlet.code.model.User;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.service.TaskStatusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private final TaskStatusRepository statusRepository;

    private final TaskStatusService statusService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User defaultUser = userRepository.findByEmail("hexlet@example.com").orElse(null);
        if (defaultUser != null) {
            return;
        }
        User user = new User();
        user.setEmail("hexlet@example.com");
        user.setFirstName("Ilya");
        user.setLastName("Markin");
        user.setPasswordDigest(passwordEncoder.encode("123"));
        userRepository.save(user);

        Map<String, String> statuses = new HashMap<>(
                Map.of("draft", "В разработке", "to_review", "На рассмотрении",
                        "to_be_fixed", "Должно быть исправлено",
                        "to_publish", "Готово к публикации", "published", "Опубликовано")
        );

        TaskStatusCreateDTO statusData = new TaskStatusCreateDTO();
        for (Map.Entry<String, String> status : statuses.entrySet()) {
            if (statusRepository.findBySlug(status.getKey()).isEmpty()) {
                statusData.setSlug(status.getKey());
                statusData.setName(status.getValue());
                statusService.create(statusData);
            }
        }
    }

}
