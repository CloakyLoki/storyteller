package entity.profiles;

import util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrivateProfileTest {

    @BeforeEach
    public void cleanDatabase() {
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.createSQLQuery("delete from private_profile where id > 1").executeUpdate();
            }
        }
    }

    @Test
    void shouldInsertNewPrivateProfile() {
        PrivateProfile expectedPrivateProfile = new PrivateProfile(
                2, LocalDate.of(2000, 5, 8));
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var privateProfileId = session.save(expectedPrivateProfile);
                var actualPersonalProfile = session.find(PrivateProfile.class, privateProfileId);

                assertEquals(actualPersonalProfile, expectedPrivateProfile);
            }
        }
    }

    @Test
    void shouldReturnPrivateProfileFromDatabase() {
        PrivateProfile expectedPrivateProfile = TestUtil.getExpectedPrivateProfile();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var privateProfileId = session.save(expectedPrivateProfile);
                session.detach(expectedPrivateProfile);
                session.getTransaction().commit();
                var actualPrivateProfile = session.find(PrivateProfile.class, privateProfileId);

                assertEquals(actualPrivateProfile, expectedPrivateProfile);
            }
        }
    }

    @Test
    void shouldUpdateExistentPrivateProfile() {
        PrivateProfile expectedPrivateProfile = TestUtil.getExpectedPrivateProfile();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedPrivateProfileId = session.save(expectedPrivateProfile);
                var actualPrivateProfile = session.find(PrivateProfile.class, expectedPrivateProfileId);

                assertEquals(actualPrivateProfile, expectedPrivateProfile);

                session.detach(expectedPrivateProfile);
                expectedPrivateProfile.setWedding(LocalDate.of(3000, 12, 12));
                actualPrivateProfile.setWedding(expectedPrivateProfile.getWedding());
                session.getTransaction().commit();

                assertEquals(actualPrivateProfile, expectedPrivateProfile);
            }
        }
    }

    @Test
    void shouldDeleteExistentPrivateProfile() {
        PrivateProfile expectedPrivateProfile = TestUtil.getExpectedPrivateProfile();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedPrivateProfileId = session.save(expectedPrivateProfile);
                var actualPrivateProfile = session.find(PrivateProfile.class, expectedPrivateProfileId);

                assertEquals(actualPrivateProfile, expectedPrivateProfile);

                session.delete(actualPrivateProfile);

                assertThat(session.find(PrivateProfile.class, expectedPrivateProfileId)).isNull();
            }
        }
    }
}