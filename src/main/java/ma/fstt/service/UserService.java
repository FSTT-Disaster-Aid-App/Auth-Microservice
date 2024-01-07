package ma.fstt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.fstt.repository.UserCredentialRepository;

@Service
public class UserService {
  @Autowired
  private UserCredentialRepository userCredentialRepository;

  // get user by id

}
