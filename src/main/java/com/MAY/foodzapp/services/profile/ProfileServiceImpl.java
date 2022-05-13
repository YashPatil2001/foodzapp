package com.MAY.foodzapp.services.profile;

import com.MAY.foodzapp.entities.AccountValidationNumber;
import com.MAY.foodzapp.entities.Profile;
import com.MAY.foodzapp.models.LoginUserModel;
import com.MAY.foodzapp.repositories.ProfileRepository;
import com.MAY.foodzapp.repositories.ValidationRepository;
import com.MAY.foodzapp.utils.ResultFlags;
import com.MAY.foodzapp.utils.UserRoles;
import com.MAY.foodzapp.utils.emailValidation.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.MAY.foodzapp.utils.ResultFlags.FAILURE;
import static com.MAY.foodzapp.utils.ResultFlags.SUCCESS;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final Long CODE_EXPIRATION_MINUTE = 20L;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ValidationRepository accountValidation;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailSender emailSender;
    @Override
    @Transactional
    public ResultFlags createUser(Profile profile) {

        Boolean userExist = profileRepository.findByEmail(profile.getEmail()).isPresent();

        if(userExist){
            throw  new IllegalStateException("User with this Email already Exist");
        }



       Profile profileDB = Profile.builder()
               .firstName(profile.getFirstName())
               .lastName(profile.getLastName())
               .email(profile.getEmail())
               .role(UserRoles.USER)
               .password(passwordEncoder.encode(profile.getPassword()))
               .build();

        // TODO: 09-05-2022 add to and mail
       String validationCode = UUID.randomUUID().toString();
        System.out.println("creating validation code : " + validationCode);
       //sneding mail
       emailSender.sendMail(profile.getEmail(), buildEmail(profile.getFirstName(),validationCode));

        AccountValidationNumber validationNumber = AccountValidationNumber.builder()
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(CODE_EXPIRATION_MINUTE))
                .validationCode(validationCode)
                .user(profileDB)
                .build();
        accountValidation.save(validationNumber);
        return SUCCESS;
    }

    @Override
    public ResultFlags validateCode(String code) {
        Optional<AccountValidationNumber>  validationCode = accountValidation.getValidationByValidationCode(code);
        System.out.println("validating validation code : " + code);
        if(validationCode.isPresent() &&  validationCode.get().getExpiredAt() != LocalDateTime.now()){
            return SUCCESS;
        }
        Long profileId = validationCode.get().getUser().getProfileId();
        accountValidation.deleteById(validationCode.get().getId());
        profileRepository.deleteById(profileId);
        throw new IllegalStateException("invalid token");
    }

    @Override
    public Long loginUser(LoginUserModel user) {

        System.out.println("in loginUser");
        Optional<Profile> profile = profileRepository.findByEmail(user.getEmail());
        if(!profile.isPresent()){
            throw new IllegalStateException("Email not exist");
        }
        System.out.println("profile : " + profile.isPresent());

        System.out.println("dbpass : " +profile.get().getPassword());
        System.out.println("userpass : " +passwordEncoder.encode(user.getPassword()));
        System.out.println("results :  " + passwordEncoder.matches(user.getPassword(),profile.get().getPassword()));
        if(!passwordEncoder.matches(user.getPassword(),profile.get().getPassword())) {
            throw new IllegalStateException("incorrect password");
        }
        long id = profile.get().getProfileId();
        System.out.println("id : " + profile.get().getProfileId());
        return profile.get().getProfileId();
    }

    private String buildEmail(String name, String code) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please  use below Validation Code to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <h2> "+ code + "</h2></p></blockquote>\n Validation code will expire in "+ CODE_EXPIRATION_MINUTE + "minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
