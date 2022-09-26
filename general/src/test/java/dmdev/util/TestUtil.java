package dmdev.util;

import dmdev.entity.*;
import dmdev.entity.profiles.PersonalProfile;
import dmdev.entity.profiles.PrivateProfile;
import dmdev.entity.profiles.ProfessionalProfile;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

@UtilityClass
public class TestUtil {

    public static SessionFactory buildSessionFactory() {
        var configuration = new Configuration();
        configuration.setPhysicalNamingStrategy((new CamelCaseToUnderscoresNamingStrategy()));
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Gift.class);
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(PersonalProfile.class);
        configuration.addAnnotatedClass(ProfessionalProfile.class);
        configuration.addAnnotatedClass(PrivateProfile.class);
        configuration.addAnnotatedClass(Scope.class);
        configuration.addAnnotatedClass(Story.class);
        configuration.addAnnotatedClass(Tag.class);
        configuration.configure();

        return configuration.buildSessionFactory();
    }

    public static User getExpectedUser() {
        return User.builder()
                .name("ivan")
                .patrName("ivanych")
                .surname("ivanov")
                .role(Role.USER)
                .build();
    }

    public static Gift getExpectedGift() {
        return Gift.builder()
                .name("Библия")
                .description("Подарочное издание в кожаном переплете")
                .date(LocalDate.of(2021, 2, 1))
                .personId(1)
                .build();
    }

    public static Person getExpectedPerson() {
        return Person.builder()
                .name("Petr")
                .patrName(null)
                .surname("Petrov")
                .picture(null)
                .importance(3)
                .origin(1)
                .build();
    }

    public static Scope getExpectedScope() {
        return Scope.builder()
                .id(1)
                .name("Сапог")
                .build();
    }

    public static Story getExpectedStory() {
        return Story.builder()
                .name("День рождения")
                .description("Юбилей 50 лет")
                .startDate(LocalDate.of(2022, 12, 12))
                .endDate(LocalDate.of(2022, 12, 12))
                .personId(1)
                .build();
    }

    public static Tag getExpectedTag() {
        return Tag.builder()
                .name("military_stuff")
                .description("Люксовый набор")
                .importance(5)
                .colorHash("RED")
                .build();
    }

    public static PersonalProfile getExpectedPersonalProfile() {
        return PersonalProfile.builder()
                .id(1)
                .birthday(LocalDate.of(1980, 2, 3))
                .nationality("Russian")
                .religion("Othodox")
                .build();
    }

    public static PrivateProfile getExpectedPrivateProfile() {
        return PrivateProfile.builder()
                .id(1)
                .wedding(LocalDate.of(1990, 1, 17))
                .build();
    }

    public static ProfessionalProfile getExpectedProfessionalProfile() {
        return ProfessionalProfile.builder()
                .id(1)
                .position("Deputy minister")
                .hired(LocalDate.of(1995, 5, 15))
                .scope(1)
                .build();
    }
}