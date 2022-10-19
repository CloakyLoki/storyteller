package com.dmdev.entity.profiles;

import dmdev.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfessionalProfileTest {

    @BeforeEach
    public void cleanDatabase() {
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.createSQLQuery("delete from professional_profile where id > 1").executeUpdate();
            }
        }
    }

    @Test
    void shouldInsertNewProfessionalProfile() {
        ProfessionalProfile expectedProfessionalProfile = new ProfessionalProfile(
                2, "Minister", LocalDate.of(2015, 7, 1), 1);
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var professionalProfileId = session.save(expectedProfessionalProfile);
                var actualProfessionalProfile = session.find(ProfessionalProfile.class, professionalProfileId);

                assertEquals(actualProfessionalProfile, expectedProfessionalProfile);
            }
        }
    }

    @Test
    void shouldReturnProfessionalProfileFromDatabase() {
        ProfessionalProfile expectedProfessionalProfile = TestUtil.getExpectedProfessionalProfile();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var ProfessionalProfileId = session.save(expectedProfessionalProfile);
                session.detach(expectedProfessionalProfile);
                session.getTransaction().commit();
                var actualProfessionalProfile = session.find(ProfessionalProfile.class, ProfessionalProfileId);

                assertEquals(actualProfessionalProfile, expectedProfessionalProfile);
            }
        }
    }

    @Test
    void shouldUpdateExistentProfessionalProfile() {
        ProfessionalProfile expectedProfessionalProfile = TestUtil.getExpectedProfessionalProfile();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedProfessionalProfileId = session.save(expectedProfessionalProfile);
                var actualProfessionalProfile = session.find(ProfessionalProfile.class, expectedProfessionalProfileId);

                assertEquals(actualProfessionalProfile, expectedProfessionalProfile);

                session.detach(expectedProfessionalProfile);
                expectedProfessionalProfile.setPosition("Министр");
                actualProfessionalProfile.setPosition(expectedProfessionalProfile.getPosition());
                session.getTransaction().commit();

                assertEquals(actualProfessionalProfile, expectedProfessionalProfile);
            }
        }
    }

    @Test
    void shouldDeleteExistentProfessionalProfile() {
        ProfessionalProfile expectedProfessionalProfile = TestUtil.getExpectedProfessionalProfile();
        try (var sessionFactory = TestUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                var expectedProfessionalProfileId = session.save(expectedProfessionalProfile);
                var actualProfessionalProfile = session.find(ProfessionalProfile.class, expectedProfessionalProfileId);

                assertEquals(actualProfessionalProfile, expectedProfessionalProfile);

                session.delete(actualProfessionalProfile);
                assertThat(session.find(ProfessionalProfile.class, expectedProfessionalProfileId)).isNull();
            }
        }
    }
}