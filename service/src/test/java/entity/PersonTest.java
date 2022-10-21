package entity;

import util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {

    @BeforeEach
    public void cleanDatabase() {
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.createSQLQuery("delete from person where id > 1").executeUpdate();
            }
        }
    }

    @Test
    void shouldInsertNewPerson() {
        Person expectedPerson = TestUtil.getExpectedPerson();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var personId = session.save(expectedPerson);
                session.getTransaction().commit();
                session.detach(expectedPerson);
                var actualPerson = session.find(Person.class, personId);

                assertEquals(actualPerson, expectedPerson);
            }
        }
    }

    @Test
    void shouldReturnPersonFromDatabase() {
        Person expectedPerson = TestUtil.getExpectedPerson();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var personId = session.save(expectedPerson);
                session.getTransaction().commit();
                session.detach(expectedPerson);
                var actualPerson = session.find(Person.class, personId);

                assertEquals(actualPerson, expectedPerson);
            }
        }
    }

    @Test
    void shouldUpdateExistentPerson() {
        Person expectedPerson = TestUtil.getExpectedPerson();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedPersonId = session.save(expectedPerson);
                var actualPerson = session.find(Person.class, expectedPersonId);

                assertEquals(actualPerson, expectedPerson);

                expectedPerson.setName("NewName");
                actualPerson.setName(expectedPerson.getName());
                session.getTransaction().commit();
                session.detach(expectedPerson);

                assertEquals(actualPerson, expectedPerson);
            }
        }
    }

    @Test
    void shouldDeleteExistentPerson() {
        Person expectedPerson = TestUtil.getExpectedPerson();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedPersonId = session.save(expectedPerson);
                var actualPerson = session.find(Person.class, expectedPersonId);

                assertEquals(actualPerson, expectedPerson);

                session.delete(actualPerson);

                assertThat(session.find(Person.class, expectedPersonId)).isNull();
            }
        }
    }
}