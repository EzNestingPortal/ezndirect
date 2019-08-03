package com.ezn.customer.portal.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezn.customer.portal.config.Constants;
import com.ezn.customer.portal.domain.Authority;
import com.ezn.customer.portal.domain.Property;
import com.ezn.customer.portal.domain.User;
import com.ezn.customer.portal.repository.AuthorityRepository;
import com.ezn.customer.portal.repository.PropertyRepository;
import com.ezn.customer.portal.repository.UserRepository;
import com.ezn.customer.portal.security.AuthoritiesConstants;
import com.ezn.customer.portal.security.SecurityUtils;
import com.ezn.customer.portal.service.dto.PropertyDTO;
import com.ezn.customer.portal.service.dto.UserDTO;
import com.ezn.customer.portal.service.util.RandomUtil;
import com.ezn.customer.portal.web.rest.errors.BadRequestAlertException;
import com.ezn.customer.portal.web.rest.errors.InvalidPasswordException;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;
    
    private final PropertyRepository propertyRepository;

    private final CacheManager cacheManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository, PropertyRepository propertyRepository, CacheManager cacheManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.propertyRepository = propertyRepository;
        this.cacheManager = cacheManager;
    }

    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
            .map(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
                user.setActivationKey(null);
                this.clearUserCaches(user);
                log.debug("Activated user: {}", user);
                return user;
            });
    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
       log.debug("Reset user password for reset key {}", key);

       return userRepository.findOneByResetKey(key)
           .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
           .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                this.clearUserCaches(user);
                return user;
           });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmailIgnoreCase(mail)
            .filter(User::getActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(Instant.now());
                this.clearUserCaches(user);
                return user;
            });
    }

    public User registerUser(UserDTO userDTO, String password)   {

        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        newUser.setLogin(userDTO.getLogin());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setMobileNo(userDTO.getMobileNo());
        newUser.setEmail(userDTO.getEmail());
        newUser.setImageUrl(userDTO.getImageUrl());
        //Defaulting to EN
        userDTO.setLangKey("en");
        newUser.setLangKey(userDTO.getLangKey());
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        
        List<Property> properties = new ArrayList<Property>();
        Property property = new Property();
        List<PropertyDTO> propertiesDTO = userDTO.getProperties();
        PropertyDTO propertyDTO = propertiesDTO.get(0);
        property.setEmail(userDTO.getEmail());
        property.setUser(newUser);
        property.setPropertySize(propertyDTO.getPropertySize());
        property.setLawnSize(propertyDTO.getLawnSize());
        property.setFloors(propertyDTO.getFloors());
        property.setPlanId(propertyDTO.getPlanId());
        property.setPlanType(propertyDTO.getPlanType());
        property.setActualPrice(propertyDTO.getActualPrice());
    	property.setDiscountPrice(propertyDTO.getDiscountPrice());
        property.setAddress1(propertyDTO.getAddress1());
        property.setAddress2(propertyDTO.getAddress2());
        property.setCity(propertyDTO.getCity());
        property.setState(propertyDTO.getState());
        property.setZip(propertyDTO.getZip());
        property.setCountry(propertyDTO.getCountry());
        property.setReferralCode(propertyDTO.getReferralCode());
        property.setNotes(propertyDTO.getNotes());
        property.setCornerLot(propertyDTO.isCornerLot());
        property.setFlowerBed(propertyDTO.isFlowerBed());
        property.setQuarterlyPestControl(propertyDTO.isQuarterlyPestControl());
    	property.setAgree(propertyDTO.isAgree());
        property.setParam1(propertyDTO.getParam1());
        property.setParam2(propertyDTO.getParam2());
        property.setParam3(propertyDTO.getParam3());
        property.setParam4(propertyDTO.getParam4());
        property.setParam5(propertyDTO.getParam5());
        property.setParam6(propertyDTO.getParam6());
        property.setParam7(propertyDTO.getParam7());
        property.setParam8(propertyDTO.getParam8());
        property.setParam9(propertyDTO.getParam9());
        property.setParam10(propertyDTO.getParam10());
        property.setParam11(propertyDTO.getParam11());
        property.setParam12(propertyDTO.getParam12());
        property.setParam13(propertyDTO.getParam13());
        property.setParam14(propertyDTO.getParam14());
        property.setParam15(propertyDTO.getParam15());
        property.setParam16(propertyDTO.getParam16());
        property.setParam17(propertyDTO.getParam17());
        property.setParam18(propertyDTO.getParam18());
        property.setParam19(propertyDTO.getParam19());
        property.setParam20(propertyDTO.getParam20());

    	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
    	String lastServiceDate = propertyDTO.getLastServiceDate();
    	if(lastServiceDate != null && !lastServiceDate.isEmpty()) {
    		try {
    			property.setLastServiceDate(format.parse(lastServiceDate));
    		} catch(ParseException pe) {
            	log.error("Invalid date format");
            	throw new BadRequestAlertException("Invalid Last Service Date Format", "lastServiceDate", "");
            }
    	}
           
        String serviceStartDate = propertyDTO.getServiceStartDate();
    	if(serviceStartDate != null && !serviceStartDate.isEmpty()) {
    		try {
    			property.setServiceStartDate(format.parse(serviceStartDate));
    		} catch(ParseException pe) {
            	log.error("Invalid date format");
            	throw new BadRequestAlertException("Invalid Service Start Date Format", "serviceStartDate", "");
            }
    	}
        properties.add(property);
        
        newUser.setProperties(properties);
        
        userRepository.save(newUser);
        this.clearUserCaches(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setImageUrl(userDTO.getImageUrl());
        if (userDTO.getLangKey() == null) {
            user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
        } else {
            user.setLangKey(userDTO.getLangKey());
        }
        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = userDTO.getAuthorities().stream()
                .map(authorityRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setActivated(true);
        userRepository.save(user);
        this.clearUserCaches(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user
     * @param lastName last name of user
     * @param email email id of user
     * @param langKey language key
     * @param imageUrl image URL of user
     */
    public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(user -> {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setLangKey(langKey);
                user.setImageUrl(imageUrl);
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
            });
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     * @return updated user
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
            .findById(userDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(user -> {
                this.clearUserCaches(user);
                user.setLogin(userDTO.getLogin());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                user.setImageUrl(userDTO.getImageUrl());
                user.setActivated(userDTO.isActivated());
                user.setLangKey(userDTO.getLangKey());
                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                userDTO.getAuthorities().stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(managedAuthorities::add);
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(UserDTO::new);
    }

/**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     * @return updated user
     */
    public User updateCustomerStripeIdForUser(UserDTO userDTO) {
        
        User user = userRepository.findUserByEmailIgnoreCase(userDTO.getEmail());
        user.setCustomerStripeId(userDTO.getCustomerStripeId());
        log.debug("Updated Customer Stripe Id for User: {}", user);
        return user;
    }


    public void deleteUser(String login) {
        userRepository.findOneByLogin(login).ifPresent(user -> {
            userRepository.delete(user);
            this.clearUserCaches(user);
            log.debug("Deleted User: {}", user);
        });
    }

    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(user -> {
                String currentEncryptedPassword = user.getPassword();
                if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                    throw new InvalidPasswordException();
                }
                String encryptedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encryptedPassword);
                this.clearUserCaches(user);
                log.debug("Changed password for User: {}", user);
            });
    }

    public void updatePassword(String emailId, String newPassword) {
        User user = userRepository.findUserByEmailIgnoreCase(emailId);
        String encryptedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);
        this.clearUserCaches(user);
        log.debug("Updated default password for User: {}", user);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(Long id) {
        return userRepository.findOneWithAuthoritiesById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
    }
    
   

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS));
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getLogin());
            userRepository.delete(user);
            this.clearUserCaches(user);
        }
    }

    /**
     * @return a list of all the authorities
     */
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }

    private void clearUserCaches(User user) {
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getLogin());
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
    }
}
