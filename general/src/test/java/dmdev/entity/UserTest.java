package dmdev.entity;

import dmdev.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @BeforeEach
    public void cleanDatabase() {
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.createSQLQuery("delete from users where id > 1").executeUpdate();
            }
        }
    }

    @Test
    void shouldInsertNewUser() {
        User expectedUser = TestUtil.getExpectedUser();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var userId = session.save(expectedUser);
                session.getTransaction().commit();
                session.detach(expectedUser);
                var actualUser = session.find(User.class, userId);

                assertEquals(actualUser, expectedUser);
            }
        }
    }

    @Test
    void shouldReturnUserFromDatabase() {
        User expectedUser = TestUtil.getExpectedUser();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var userId = session.save(expectedUser);
                session.getTransaction().commit();
                session.detach(expectedUser);
                var actualUser = session.find(User.class, userId);

                assertEquals(actualUser, expectedUser);
            }
        }
    }

    @Test
    void shouldUpdateExistentUser() {
        User expectedUser = TestUtil.getExpectedUser();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedUserId = session.save(expectedUser);
                var actualUser = session.find(User.class, expectedUserId);

                assertEquals(actualUser, expectedUser);

                expectedUser.setName("NewName");
                actualUser.setName(expectedUser.getName());
                session.getTransaction().commit();
                session.detach(expectedUser);

                assertEquals(actualUser, expectedUser);
            }
        }
    }

    @Test
    void shouldDeleteExistentUser() {
        User expectedUser = TestUtil.getExpectedUser();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedUserId = session.save(expectedUser);
                var actualUser = session.find(User.class, expectedUserId);

                assertEquals(actualUser, expectedUser);

                session.delete(actualUser);

                assertThat(session.find(User.class, expectedUserId)).isNull();
            }
        }
    }
}