package com.example.demo.entity;

import com.example.demo.config.QueryDslConfig;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //내장DB사용
@Import(QueryDslConfig.class)
public class ItemTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void testStatusCannotBeNull() {
        User owner = new User("user", "user@naver.com", "Nick", "test1234");
        User manager = new User("user", "manager@naver.com", "ma", "test1234");

        userRepository.save(owner);
        userRepository.save(manager);

        Item item = new Item("ItemName", "ItemDescription", manager, owner);

        assertThrows(ConstraintViolationException.class, () -> itemRepository.saveAndFlush(item),
                "status값이 null로 저장되는 Item은 ConstraintViolationException을 발생시킵니다.");

//        assertThrows(PersistenceException.class, () -> {
//            entityManager.createNativeQuery(
//                    "INSERT INTO item(name, description, owner_id, manager_id, status) VALUES ('ItemName', 'ItemDescription', 1, 2, NULL)"
//            ).executeUpdate();
//        });
    }
}
