package main.java.Vitebsk.ATP4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.Vitebsk.ATP4.role.User;

public interface UserRepo extends JpaRepository<User, Long>
{
        User findByUsername(String username);
}
