
package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>{

	@Query("select acc.accno from Account acc where acc.emailid=:emailid")
	public int findAccountUsingEmailiId(@Param("emailid") String emailid);
	@Query("select a from Account a where a.emailid=:emailid")
	public Account findAccountFullUsingEmailiId(@Param("emailid") String emailid);
}
