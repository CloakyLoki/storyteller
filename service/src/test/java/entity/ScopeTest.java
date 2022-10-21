package entity;

import util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScopeTest {

    @BeforeEach
    public void cleanDatabase() {
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.createSQLQuery("delete from scope where id > 1").executeUpdate();
            }
        }
    }

    @Test
    void shouldInsertNewScope() {
        Scope expectedScope = TestUtil.getExpectedScope();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var scopeId = session.save(expectedScope);
                session.detach(expectedScope);
                session.getTransaction().commit();
                var actualScope = session.find(Scope.class, scopeId);

                assertEquals(actualScope, expectedScope);
            }
        }
    }

    @Test
    void shouldReturnScopeFromDatabase() {
        Scope expectedScope = TestUtil.getExpectedScope();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var scopeId = session.save(expectedScope);
                session.detach(expectedScope);
                session.getTransaction().commit();
                var actualScope = session.find(Scope.class, scopeId);

                assertEquals(actualScope, expectedScope);
            }
        }
    }

    @Test
    void shouldUpdateExistentScope() {
        Scope expectedScope = TestUtil.getExpectedScope();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedScopeId = session.save(expectedScope);
                var actualScope = session.find(Scope.class, expectedScopeId);

                assertEquals(actualScope, expectedScope);

                expectedScope.setName("NewName");
                actualScope.setName(expectedScope.getName());
                session.detach(expectedScope);
                session.getTransaction().commit();

                assertEquals(actualScope, expectedScope);
            }
        }
    }

    @Test
    void shouldDeleteExistentScope() {
        Scope expectedScope = TestUtil.getExpectedScope();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedScopeId = session.save(expectedScope);
                var actualScope = session.find(Scope.class, expectedScopeId);

                assertEquals(actualScope, expectedScope);

                session.delete(actualScope);
                assertThat(session.find(Scope.class, expectedScopeId)).isNull();
            }
        }
    }
}