package com.dmdev.entity;

import dmdev.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GiftTest {

    @BeforeEach
    public void cleanDatabase() {
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.createSQLQuery("delete from gift where id > 1").executeUpdate();
            }
        }
    }

    @Test
    void shouldInsertNewGift() {
        Gift expectedGift = TestUtil.getExpectedGift();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var giftId = session.save(expectedGift);
                session.getTransaction().commit();
                session.detach(expectedGift);
                var actualGift = session.find(Gift.class, giftId);

                assertEquals(actualGift, expectedGift);
            }
        }
    }

    @Test
    void shouldReturnGiftFromDatabase() {
        Gift expectedGift = TestUtil.getExpectedGift();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var giftId = session.save(expectedGift);
                session.getTransaction().commit();
                session.detach(expectedGift);
                var actualGift = session.find(Gift.class, giftId);

                assertEquals(actualGift, expectedGift);
            }
        }
    }

    @Test
    void shouldUpdateExistentGift() {
        Gift expectedGift = TestUtil.getExpectedGift();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedGiftId = session.save(expectedGift);
                var actualGift = session.find(Gift.class, expectedGiftId);

                assertEquals(actualGift, expectedGift);

                expectedGift.setName("NewName");
                actualGift.setName(expectedGift.getName());
                session.getTransaction().commit();
                session.detach(expectedGift);

                assertEquals(actualGift, expectedGift);
            }
        }
    }

    @Test
    void shouldDeleteExistentGift() {
        Gift expectedGift = TestUtil.getExpectedGift();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedGiftId = session.save(expectedGift);
                var actualGift = session.find(Gift.class, expectedGiftId);

                assertEquals(actualGift, expectedGift);

                session.delete(actualGift);

                assertThat(session.find(Gift.class, expectedGiftId)).isNull();
            }
        }
    }
}