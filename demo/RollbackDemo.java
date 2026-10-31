import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.dao.DataIntegrityViolationException;

import ru.rutmiit.basketball.BasketballApplication;
import ru.rutmiit.basketball.dto.AddTeamDto;
import ru.rutmiit.basketball.repositories.TeamRepository;
import ru.rutmiit.basketball.services.TeamService;

public class RollbackDemo {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BasketballApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);

        try (var context = application.run(args)) {
            TeamRepository repository = context.getBean(TeamRepository.class);
            if (!repository.existsByNameIgnoreCase("Chicago Bulls")) {
                throw new IllegalStateException("Для демонстрации нужна команда Chicago Bulls");
            }
            if (repository.existsByNameIgnoreCase("Rollback Candidate")) {
                throw new IllegalStateException("Название Rollback Candidate уже занято. Данные не изменены");
            }

            TeamService service = context.getBean(TeamService.class);
            try {
                service.addTeams(List.of(form("Rollback Candidate"), form("Chicago Bulls")));
                throw new IllegalStateException("Ожидалось нарушение уникальности");
            } catch (DataIntegrityViolationException expected) {
                if (repository.existsByNameIgnoreCase("Rollback Candidate")) {
                    throw new IllegalStateException("Первая вставка не откатилась", expected);
                }
                System.out.println("Проверка пройдена: вторая вставка отклонена, первая тоже отменена");
            }
        }
    }

    private static AddTeamDto form(String name) {
        AddTeamDto form = new AddTeamDto();
        form.setName(name);
        form.setCity("Чикаго");
        form.setDescription("Команда для демонстрации отката транзакции");
        form.setFoundedYear(2000);
        return form;
    }
}
