package xwizard.karteczki.repos;

import java.util.UUID;

import xwizard.karteczki.quiz.Box;

public interface BoxRepo {
  void save(Box box);
  Box get(UUID boxId);
  void delete(UUID boxId);
}
