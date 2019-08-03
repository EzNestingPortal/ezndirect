package com.ezn.customer.portal.web.rest.errors;

public class DuplicateCreditCardException extends ResourceAlreadyExistsException {

    private static final long serialVersionUID = 1L;

    public DuplicateCreditCardException() {
        super(ErrorConstants.CREDIT_CARD_ALREADY_USED_TYPE, "We have already added your card to your account!", "payment", "duplicateCardSubmission");
    }
}
