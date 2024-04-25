package hexlet.code.component;

/*import hexlet.code.dto.LabelCreateDTO;
import hexlet.code.dto.TaskStatusCreateDTO;
import hexlet.code.dto.UserCreateDTO;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskStatusRepository;*/
import hexlet.code.dto.UserCreateDto;
import hexlet.code.repository.UserRepository;
/*import hexlet.code.service.LabelService;
import hexlet.code.service.TaskStatusService;*/
import hexlet.code.service.UserService;
import io.sentry.Sentry;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserService userService;

    private final UserRepository userRepository;

    /*private final TaskStatusRepository statusRepository;

    private final TaskStatusService statusService;

    private final LabelRepository labelRepository;

    private final LabelService labelService;*/

    @Override
    public void run(ApplicationArguments args) throws Exception {

        var userData = new UserCreateDto();
        userData.setFirstName("Ilya");
        userData.setLastName("Markin");
        userData.setEmail("hexlet@example.com");
        userData.setPassword("123");
        userService.create(userData);

        /*Map<String, String> statuses = new HashMap<>(
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

        List<String> labels = new ArrayList<>(List.of("bug", "feature"));
        LabelCreateDTO labelData = new LabelCreateDTO();
        for (String label : labels) {
            if (labelRepository.findByName(label).isEmpty()) {
                labelData.setName(label);
                labelService.create(labelData);
            }
        }*/
    }

}
