package fr.socrates.infra.storage.repositories;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcCandidateRepository implements CandidateRepository {
    @Autowired
    private NamedParameterJdbcOperations jdbcTemplate;

    public JdbcCandidateRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Candidate> findAll() {
        return jdbcTemplate.query("SELECT * FROM cyril.candidates", new CandidateRowMapper());
    }

    @Override
    @Cacheable("candidates")
    public Optional<Candidate> findByEmail(String email) {
        String sql = "SELECT * FROM cyril.candidates WHERE email = :email";
        SqlParameterSource namedParameter = new MapSqlParameterSource("email", email);
        return Optional.of(jdbcTemplate.queryForObject(sql
            , namedParameter
            , new CandidateRowMapper()));
    }

    @Override
    @CachePut("candidates")
    public boolean save(Candidate candidate) {
        String sql = "INSERT INTO cyril.candidates(email) VALUES (:email)";
        SqlParameterSource namedParameter = new MapSqlParameterSource("email", candidate.getEmail().getEmail());
        return jdbcTemplate.update(sql, namedParameter) > 0;
    }

    @Override
    public boolean update(Candidate updatedCandidate, String oldEmail) {
        String sql = "UPDATE cyril.candidates SET email = :newEmail WHERE email = :oldEmail";
        Map<String, String> params = new HashMap<>();
        params.put("newEmail", updatedCandidate.getEmail().getEmail());
        params.put("oldEmail", oldEmail);
        return jdbcTemplate.update(sql, params) > 0;
    }

    @Override
    public boolean delete(Candidate candidate) {
        String sql = "DELETE FROM cyril.candidates WHERE email = :email";
        SqlParameterSource namedParameter = new MapSqlParameterSource("email", candidate.getEmail().getEmail());
        return jdbcTemplate.update(sql, namedParameter) > 0;
    }

    @Override
    public Optional<Candidate> findByCandidateID(CandidateId candidateId) {
        return null;
    }

    private class CandidateRowMapper implements RowMapper<Candidate> {
        @Override
        public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Candidate.withEmail(rs.getString("EMAIL"));
        }
    }
}
