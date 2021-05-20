package com.mm.jpa.jdbctojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonJdbcDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("location"),
                    resultSet.getTimestamp("birth_date")
            );
        }
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new PersonRowMapper());
    }

    public Person findById(int id) {
        return jdbcTemplate
                .queryForObject("select * from person where id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class)
                );
    }

    public int deleteById(int id) {
        return jdbcTemplate
                .update("delete from person where id=?",
                        new Object[]{id});
    }

    public int insert(Person person) {
        return jdbcTemplate
                .update("INSERT INTO person(`id`,`name`,`location`,`birth_date`) VALUES(?,?,?,?)",
                        new Object[]{person.getId(),
                                person.getName(),
                                person.getLocation(),
                                new Timestamp(person.getBirthDate().getTime())
                        });
    }

    public int update(Person person) {
        return jdbcTemplate
                .update("Update person " +
                                "set name=?, location=?, birth_date=?" +
                                "where id=?",
                        new Object[]{
                                person.getName(),
                                person.getLocation(),
                                new Timestamp(person.getBirthDate().getTime()),
                                person.getId()
                        });
    }
}
