package com.dwbook.phonebook.dao;
import org.skife.jdbi.v2.sqlobject.*;
import com.dwbook.phonebook.representations.Contact;
import com.dwbook.phonebook.dao.mappers.ContactMapper;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
public interface ContactDAO {
	@Mapper(ContactMapper.class)
	@SqlQuery("select * from contact where id= :id")
	Contact getContactById(@Bind("id") int id);
	
	//createContact
	@GetGeneratedKeys
  @SqlUpdate("insert into contact (id, firstName, lastName, phone, email) values (NULL, :firstName, :lastName, :phone, :email)")
  int createContact(@Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("phone") String phone, @Bind("email") String email);
	  
	//delete contact
	@SqlUpdate("delete from contact where id= :id")
	void deleteContact(@Bind("id") int id);
	
	//updateContact
    @SqlUpdate("update contact set firstName = :firstName, lastName = :lastName, phone = :phone, email = :email where id= :id")
    void updateContact(@Bind("id") int id, @Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("phone") String phone, @Bind("email") String email);

}