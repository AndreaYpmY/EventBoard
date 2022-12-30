package it.sad.students.eventboard.persistenza.dao;

import it.sad.students.eventboard.persistenza.model.Position;

import java.util.List;

public interface PositionDao {
    List<Position> findAll();

    Position findByPrimaryKey(Long id);

    void saveOrUpdate(Position position);

    void delete(Position position);
}
