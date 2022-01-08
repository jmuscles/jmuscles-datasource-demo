/**
 * 
 */
package com.jmuscles.datasource.demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author manish goel
 *
 */
public interface UserRepository extends JpaRepository<User, String> {

}
