package entity.profiles;

import util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalProfileTest {

    @BeforeEach
    public void cleanDatabase() {
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.createSQLQuery("delete from personal_profile where id > 1").executeUpdate();
            }
        }
    }

    @Test
    void shouldInsertNewPersonalProfile() {
        PersonalProfile expectedPersonalProfile = new PersonalProfile(
                2, LocalDate.of(2000, 5, 8), "English", "Catholic");
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var personalProfileId = session.save(expectedPersonalProfile);
                var actualPersonalProfile = session.find(PersonalProfile.class, personalProfileId);

                assertEquals(actualPersonalProfile, expectedPersonalProfile);
            }
        }
    }

    @Test
    void shouldReturnPersonalProfileFromDatabase() {
        PersonalProfile expectedPersonalProfile = TestUtil.getExpectedPersonalProfile();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var personalProfileId = session.save(expectedPersonalProfile);
                session.detach(expectedPersonalProfile);
                session.getTransaction().commit();
                var actualPersonalProfile = session.find(PersonalProfile.class, personalProfileId);

                assertEquals(actualPersonalProfile, expectedPersonalProfile);
            }
        }
    }

    @Test
    void shouldUpdateExistentPersonalProfile() {
        PersonalProfile expectedPersonalProfile = TestUtil.getExpectedPersonalProfile();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedPersonalProfileId = session.save(expectedPersonalProfile);
                var actualPersonalProfile = session.find(PersonalProfile.class, expectedPersonalProfileId);

                assertEquals(actualPersonalProfile, expectedPersonalProfile);

                session.detach(expectedPersonalProfile);
                expectedPersonalProfile.setNationality("Англичанин");
                actualPersonalProfile.setNationality(expectedPersonalProfile.getNationality());
                session.getTransaction().commit();

                assertEquals(actualPersonalProfile, expectedPersonalProfile);
            }
        }
    }

    @Test
    void shouldDeleteExistentPersonalProfile() {
        PersonalProfile expectedPersonalProfile = TestUtil.getExpectedPersonalProfile();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedPersonalProfileId = session.save(expectedPersonalProfile);
                var actualPersonalProfile = session.find(PersonalProfile.class, expectedPersonalProfileId);

                assertEquals(actualPersonalProfile, expectedPersonalProfile);

                session.delete(actualPersonalProfile);

                assertThat(session.find(PersonalProfile.class, expectedPersonalProfileId)).isNull();
            }
        }
    }
}