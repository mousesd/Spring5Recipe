package net.homenet.batch;

import net.homenet.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("Duplicates")
public class UserValidationItemProcessor implements ItemProcessor<User, User> {
    private static final Logger logger = LoggerFactory.getLogger(UserValidationItemProcessor.class);
    private final List<String> states;

    public UserValidationItemProcessor() {
        this.states = Arrays.asList(("AL AK AS AZ AR CA CO CT DE DC FM "
            + "FL GA GU HI ID IL IN IA KS KY LA ME MH MD "
            + "MA MI MN MS MO MT NE NV NH NJ NM NY NC ND "
            + "MP OH OK OR PW PA PR RI SC SD TN TX UT "
            + "VT VI VA WA WV WI WY").split(" "));
    }

    @Override
    public User process(User item) {
        String zipCode = removeNonNumbers(item.getZip());
        String phoneNumber = removeNonNumbers(item.getPhoneNumber());
        String state = item.getState();

        if (isZipCodeValid(zipCode) && isPhoneNumberValid(phoneNumber) && isStateValid(state)) {
            logger.debug("Input user valid.");
            logger.debug("User: " + item);
            return item;
        }

        logger.debug("Input user invalid.");
        return null;
    }

    private boolean isZipCodeValid(String zipCode) {
        return StringUtils.hasText(zipCode) && (zipCode.length() == 5 || zipCode.length() == 9);
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return StringUtils.hasText(phoneNumber) && phoneNumber.length() == 10;
    }

    private boolean isStateValid(String state) {
        return StringUtils.hasText(state) && states.contains(state);
    }

    private String removeNonNumbers(String input) {
        String output = input == null ? "" : input;
        StringBuilder numbers = new StringBuilder();
        for (char digit : output.toCharArray()) {
            if (Character.isDigit(digit)) {
                numbers.append(digit);
            }
        }
        return numbers.toString();
    }
}
