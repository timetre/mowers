package com.blablacar.mowers.common;

import lombok.Data;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
@Data
public class ErrorDictionary {

    public static final ErrorDictionary ERR_UNEXPECTED_ERROR = new ErrorDictionary(0, Category.ERR, "Unexpected error happened");
    public static final ErrorDictionary ERR_WRONG_INPUT_PARAMS = new ErrorDictionary(1, Category.ERR, "Wrong input parameters");
    public static final ErrorDictionary ERR_CANNOT_READ_FILE = new ErrorDictionary(2, Category.ERR, "Cannot read provided file");
    public static final ErrorDictionary ERR_UNABLE_TO_BUILD_LAWN = new ErrorDictionary(3, Category.ERR, "Cannot build lawn");
    public static final ErrorDictionary ERR_INCORRECT_DATA = new ErrorDictionary(4, Category.ERR, "Incorrect data");

    public static final ErrorDictionary ERR_UNABLE_TO_BUILD_MOWER = new ErrorDictionary(5, Category.ERR, "Cannot build mower");

    /**
     * Code for the current error
     */
    protected Integer code = null;

    /**
     * Enum representing all error cateories
     */
    public enum Category {
        KO, ERR, WARN
    }

    /**
     * Category for the current error
     */
    protected Category category = null;

    /**
     * Description for the current error
     */
    protected String description = null;

    /**
     * Constructor
     *
     * @param code        error code
     * @param category    error category
     * @param description error description
     **/
    private ErrorDictionary(Integer code, Category category, String description) {
        this.code = code;
        this.category = category;
        this.description = description;
    }

    /**
     * Get error full description, composed of code and description
     *
     * @return error description
     */
    public String getFullDescription() {
        return code + ":" + description;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("code=[");
        strBuilder.append(code);
        strBuilder.append("], category=[");
        strBuilder.append(category);
        strBuilder.append("], description=[");
        strBuilder.append(description);
        strBuilder.append("]");

        return strBuilder.toString();
    }
}
