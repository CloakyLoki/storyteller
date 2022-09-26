package dmdev.entity;

import dmdev.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoryTest {

    @BeforeEach
    public void cleanDatabase() {
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.createSQLQuery("delete from story where id > 1").executeUpdate();
            }
        }
    }

    @Test
    void shouldInsertNewStory() {
        Story expectedStory = TestUtil.getExpectedStory();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var storyId = session.save(expectedStory);
                session.getTransaction().commit();
                session.detach(expectedStory);
                var actualStory = session.find(Story.class, storyId);

                assertEquals(actualStory, expectedStory);
            }
        }
    }

    @Test
    void shouldReturnStoryFromDatabase() {
        Story expectedStory = TestUtil.getExpectedStory();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var storyId = session.save(expectedStory);
                session.getTransaction().commit();
                session.detach(expectedStory);
                var actualStory = session.find(Story.class, storyId);

                assertEquals(actualStory, expectedStory);
            }
        }
    }

    @Test
    void shouldUpdateExistentStory() {
        Story expectedStory = TestUtil.getExpectedStory();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedStoryId = session.save(expectedStory);
                var actualStory = session.find(Story.class, expectedStoryId);

                assertEquals(actualStory, expectedStory);

                expectedStory.setName("NewName");
                actualStory.setName(expectedStory.getName());
                session.getTransaction().commit();
                session.detach(expectedStory);

                assertEquals(actualStory, expectedStory);
            }
        }
    }

    @Test
    void shouldDeleteExistentStory() {
        Story expectedStory = TestUtil.getExpectedStory();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedStoryId = session.save(expectedStory);
                var actualStory = session.find(Story.class, expectedStoryId);

                assertEquals(actualStory, expectedStory);

                session.delete(actualStory);

                assertThat(session.find(Story.class, expectedStoryId)).isNull();
            }
        }
    }
}