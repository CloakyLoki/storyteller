package com.dmdev.entity;

import dmdev.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagTest {

    @BeforeEach
    public void cleanDatabase() {
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.createSQLQuery("delete from tag where id > 1").executeUpdate();
            }
        }
    }

    @Test
    void shouldInsertNewTag() {
        Tag expectedTag = TestUtil.getExpectedTag();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var tagId = session.save(expectedTag);
                session.detach(expectedTag);
                session.getTransaction().commit();
                var actualTag = session.find(Tag.class, tagId);

                assertEquals(actualTag, expectedTag);
            }
        }
    }

    @Test
    void shouldReturnTagFromDatabase() {
        Tag expectedTag = TestUtil.getExpectedTag();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var tagId = session.save(expectedTag);
                session.detach(expectedTag);
                session.getTransaction().commit();
                var actualTag = session.find(Tag.class, tagId);

                assertEquals(actualTag, expectedTag);
            }
        }
    }

    @Test
    void shouldUpdateExistentTag() {
        Tag expectedTag = TestUtil.getExpectedTag();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedTagId = session.save(expectedTag);
                var actualTag = session.find(Tag.class, expectedTagId);

                assertEquals(actualTag, expectedTag);

                expectedTag.setName("NewName");
                actualTag.setName(expectedTag.getName());
                session.detach(expectedTag);
                session.getTransaction().commit();

                assertEquals(actualTag, expectedTag);
            }
        }
    }

    @Test
    void shouldDeleteExistentTag() {
        Tag expectedTag = TestUtil.getExpectedTag();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedTagId = session.save(expectedTag);
                var actualTag = session.find(Tag.class, expectedTagId);

                assertEquals(actualTag, expectedTag);

                session.delete(actualTag);

                assertThat(session.find(Tag.class, expectedTagId)).isNull();
            }
        }
    }
}