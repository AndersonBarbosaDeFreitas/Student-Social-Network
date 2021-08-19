package com.dsc.student_social_network.repository;

import com.dsc.student_social_network.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentaryRepository extends JpaRepository<Comment, Integer> {

    Optional<List<Comment>> findCommentariesByCourseIdAndIsRemovedEquals(Integer id, Integer isRemoved);
    void deleteCommentariesByCourseId(Integer id);
}
