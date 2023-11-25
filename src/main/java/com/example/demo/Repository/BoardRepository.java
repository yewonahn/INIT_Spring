package com.example.demo.Repository;

import com.example.demo.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/*
<JpaRepository>
Spring Data JPA 에서 제공하는 인터페이스
데이터베이스와 상호 작용하기 위한 다양한 메서드들을 기본적으로 제공해 줌
이 인터페이스를 상속받으면 'Board' 엔티티와 관련된 CRUD 기능을 사용할 수 있게 됨

node 는 crud 해올 때, 직접 구현해야 하는데
Spring 은 JPA 쓰면 (extends JpaRepository 를 해주면), 쉽고 빠르게 데이터를 가져올 수 있음

<JPA 쓸 때 지켜야 하는 점>
Spring JPA 에서 제공하는 메서드 이름 규칙 잘 지켜야 함

<Board, Long>
Board 는 엔티티 타입
Long 은 엔티티의 기본 키의 타입
 */


public interface BoardRepository extends JpaRepository<Board, Long> {
    /*
    <플젝할 때 많이 쓰이는 것 예시>

    List<Board> findByTitle(String title);
    List<Board> findByTitleAndContent(String title, String Content);

    @Query("SELECT b From Board b WHERE b.title = :title")
    List<Board> findByTitle(@Param("title") String title);
    -> Board 엔티티에서 title 필드를 기준으로 검색하는 쿼리

    +)
    여기에서 @Query 어노테이션은 JPQL 쿼리를 정의하고 있음
    ': title' 은 JPQL 에서 파라미터를 나타내고, 이 파라미터는 findByTitle 메서드의 매개변수인 'title' 에 의해 전달 됨

    @Param("title") 어노테이션은 메서드 파라미터와 JPQL 쿼리의 파라미터를 매핑해 줌
    즉, ':title' 은 'title' 매개변수의 값으로 대체 됨
    이렇게 함으로써 Spring Data JPA 는 메서드 호출 시 전달된 값으로 쿼리를 실행 함

    +)
    <@Query 어노테이션>
    Spring Data JPA 에서 사용되며, JPQL (Query DSL)이나 네이티브 쿼리를 사용하여 데이터베이스에서 데이터를 검색하고 조작하기 위해 사용 됨
    @Query 어노테이션을 사용하면 복잡한 검색 조건이나 특정 비즈니스 로직에 맞게 쿼리를 작성할 수 있음
    주로 Repository 인터페이스에 선언하며, 해당 레포지토리에서 특정한 검색이나 조작을 위한 쿼리를 정의할 때 사용

    다시 말해서,
    Spring Data JPA 에서는 레포지토리 인터페이스에 정의된 메서드 이름 규칙에 따라 자동으로 쿼리를 생성해주는 기능이 있음
    그러나 이런 자동 생성된 쿼리로는 원하는 로직을 충분히 표현하기 어려운 경우가 있고, 그럴 때 이런 @Query 어노테이션을 사용하여 직접 JPQL 이나 네이티브 쿼리를 정의할 수 있음

    */
}
